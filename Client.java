import Commons.Address;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.Requestor;


public class Client
{
	public static void main(String args[])   {
		new Configuration();

		Address dest = NamingService.lookup("ClientProxy");

		Message msg = new Message("Client", "Server, get_price, ABC SRL");

		Requestor r = new Requestor("Client");

		Marshaller m = new Marshaller();

		byte[] bytes = m.marshal(msg);

		bytes = r.deliver_and_wait_feedback(dest, bytes);

		Message answer = m.unmarshal(bytes);

		System.out.println("Client received message " + answer.data + " from " + answer.sender);


	}

}