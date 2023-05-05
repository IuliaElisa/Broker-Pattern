
import java.rmi.*;

public interface StockMarket
     extends java.rmi.Remote {
  
     float get_price (String Company) throws RemoteException;
}