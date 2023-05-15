import java.rmi.*;

public class StockMarketClient  {
  public static void main(String[] args) {
  try {
      InfoServerOperations market =  (InfoServerOperations) Naming.lookup("rmi://localhost/NASDAQ");
      
      float price=market.get_temp("ABC SRL");
      
      System.out.println("Price is "+price); 
      } 
       catch (Exception e) {
       	System.out.println("Exception !");
        } 
  }
}