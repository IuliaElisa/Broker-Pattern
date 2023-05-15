import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StockMarketImpl 
        extends UnicastRemoteObject
        implements StockMarket {

    public StockMarketImpl() throws RemoteException {}

    public float get_price(String company) {
         float price=12345;
         System.out.println("get_price method executing");
         return price;
  }
}
