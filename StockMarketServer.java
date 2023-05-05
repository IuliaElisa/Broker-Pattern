import java.rmi.*;

public class StockMarketServer  {
  public static void main(String[] args) {
   System.out.println("StockMarketServer main started");
   try {
     StockMarketImpl stockMarketImpl = new StockMarketImpl();
     Naming.rebind("NASDAQ", stockMarketImpl );
     System.out.println("StockMarketServer main registered NASDAQ object");

     } catch (Exception e) {} 
  }
}