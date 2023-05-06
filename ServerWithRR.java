
import RequestReply.*;
import MessageMarshaller.*;
import Registry.*;
import Commons.Address;


class ServerTransformer implements ByteStreamTransformer
{
	private MessageServer originalServer;

	public ServerTransformer(MessageServer s)
	{
		originalServer = s;
	}

	public byte[] transform(byte[] in)
	{
		Message msg;
		Marshaller m = new Marshaller();
		msg = m.unmarshal(in);

		Message answer = originalServer.get_answer(msg);

		byte[] bytes = m.marshal(answer);
		return bytes;

	}
}


class MessageServer 
{
	public Message get_answer(Message msg)
	{
		System.out.println("Server received " + msg.data + " from " + msg.sender);
		Message answer = new Message("Server", "I am alive");
		return answer;
	}
}

public class ServerWithRR
{
	public static void main(String args[])   {

try {
	new Configuration();

	NamingService.rebind("Server", new Entry("127.0.0.1", 1111));
	System.out.println("StockMarketServer main registered to NAMING SERVICE");

	Address dest = NamingService.lookup("NamingService");

	Message msg = new Message("Server", "I want to connect :(");

	System.out.println("I want to connect ^^");
	Requestor r = new Requestor("Server");

	Marshaller m = new Marshaller();

	byte[] bytes = m.marshal(msg);

	bytes = r.deliver_and_wait_feedback(dest, bytes);

	Message answer = m.unmarshal(bytes);

	System.out.println("Naming received messageee " + answer.data + " from " + answer.sender);

	ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());

	Address myAddr = NamingService.lookup("Server");

	Replyer r2 = new Replyer("Server", myAddr);

	while (true) {
		r2.receive_transform_and_send_feedback(transformer);
	}

}
catch (Exception e) {
	System.out.println("Exception in SERVER RR!");
}

	}

}