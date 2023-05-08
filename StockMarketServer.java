import java.rmi.*;

public class StockMarketServer  {

    private String company;
    public StockMarketServer( ){
        this.company = "ABC SRL";
    }

    public static float get_price(String company) {
        float price=12345;
        System.out.println("get_price method executing." );
        return price;
    }
  public static void main(String[] args) {
   try {
       get_price("ABC SRL");
     } catch (Exception e) {
       System.out.println("Exception in StockMArketServer");
   }
  }
}