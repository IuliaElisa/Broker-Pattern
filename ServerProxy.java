import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.*;
import Commons.Address;

public class ServerProxy
{
	public static void execute(String serverName, Class<?> implem, int port)   {

try {
	new Configuration();

	String[] splits = NamingService.lookup("NamingService").split(",");
	String dest =splits[0] + ","+Integer.parseInt(splits[1]);

	String rawData = "NamingService" + ","+serverName + ","+"rebind," + port; //connect to Naming Service
	Message msg = new Message(serverName, rawData);
	Requestor r = new Requestor(serverName);
	Marshaller m = new Marshaller();
	byte[] bytes = m.marshal(msg);

	bytes = r.deliver_and_wait_feedback(dest, bytes);

	Message answer = m.unmarshal(bytes);
	System.out.println("ServerProxy received message " + answer.data + " from " + answer.sender);

	msg = new Message(serverName, "NamingService" + ","+serverName + ","+"lookup"); // take own address from Naming Service
	bytes = m.marshal(msg);
	bytes = r.deliver_and_wait_feedback(dest, bytes);
	answer = m.unmarshal(bytes);
	System.out.println("ServerProxy received SECOND message: " + answer.data + " from " + answer.sender);

	String myAddr = answer.data;
	ByteStreamTransformer transformer = new ServerTransformer(new MessageServer(), "NamingService", implem);

	Replyer r2 = new Replyer(serverName, myAddr); //start a Server (Replyer) with that address

	while (true) {
		System.out.println("Math server is listening.\n");
		r2.receive_transform_and_send_feedback(serverName, transformer);
	}
}
catch (Exception e) {
	System.out.println("Exception in ServerProxy!");
}
	}


	public static Object getObjectFromReference(String reference) {
		String className = reference.split("@")[0];

		try {
			Class<?> clazz = Class.forName(className);
			return clazz.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: " + className);
		} catch (Exception e) {
			System.out.println("Failed to retrieve object: " + e.getMessage());
		}
		return null;
	}
}