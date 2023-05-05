import java.rmi.*;

public class StockMarketClient  {
  public static void main(String[] args) {
  try {
      StockMarket market=  (StockMarket) Naming.lookup("rmi://localhost/NASDAQ");
      
      float price=market.get_price("ABC SRL");
      
      System.out.println("Price is "+price); 
      } 
       catch (Exception e) {
       	System.out.println("Exception !");
        } 
  }
}