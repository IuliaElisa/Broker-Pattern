import RequestReply.*;
import MessageMarshaller.*;
import Commons.Address;
import java.rmi.RemoteException;
import Registry.*;

public class ClientWithRR
{
	public static void main(String args[]) throws RemoteException {


		new Configuration();

        //Address dest=Registry.instance().get("Server");
		Address dest= NamingService.lookup("Server");

//		StockMarket market =  (StockMarket) NamingService.lookup("Server");
//
//		float price=market.get_price("ABC SRL");
//
//		System.out.println("Price is "+price);

	Message msg= new Message("Client","How are you");

		Requestor r = new Requestor("Client");

		Marshaller m = new Marshaller();

		byte[] bytes = m.marshal(msg);

		bytes = r.deliver_and_wait_feedback(dest, bytes);

		Message answer = m.unmarshal(bytes);

		System.out.println("Client received message "+answer.data+" from "+answer.sender);
	}

}