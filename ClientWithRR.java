import RequestReply.*;
import MessageMarshaller.*;
import Commons.Address;
import java.rmi.RemoteException;

public class ClientWithRR
{
	public static void main(String args[]) throws RemoteException {

		new Configuration();

		Address dest = NamingService.lookup("Server");

		Message msg= new Message("Client","get_price, ABC SRL");

		Requestor r = new Requestor("Client");

		Marshaller m = new Marshaller();

		byte[] bytes = m.marshal(msg);

		bytes = r.deliver_and_wait_feedback(dest, bytes);

		Message answer = m.unmarshal(bytes);

		System.out.println("Client received message "+answer.data+" from "+answer.sender);
	}

}