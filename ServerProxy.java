import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.*;
import Commons.Address;


public class ServerProxy
{
	public static void execute(String serverName, Class<?> implem)   {

try {
	new Configuration();

//	do that !!
//	Address dest = NamingService.lookup("NamingService");
//	Message msg = new Message("ServerProxy","ServerProxy" + ":"+"connect");
//	Requestor r = new Requestor("ServerProxy");
//	Marshaller m = new Marshaller();
//	byte[] bytes = m.marshal(msg);
//	bytes = r.deliver_and_wait_feedback(dest, bytes);
//
//	Message answer = m.unmarshal(bytes);
//	System.out.println("ServerProxy received message " + answer.data + " from " + answer.sender);

	//ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());
	ByteStreamTransformer transformer = new ServerTransformer(new MessageServer(), "NamingService", implem);

	Address myAddr = NamingService.lookup(serverName);

	Replyer r2 = new Replyer(serverName, myAddr);

	while (true) {
		System.out.println("Math server is listening.\n");
		r2.receive_transform_and_send_feedback(serverName, transformer);
	}
}
catch (Exception e) {
	System.out.println("Exception in ServerProxy!");
}
	}
}