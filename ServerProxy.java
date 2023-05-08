import RequestReply.*;
import MessageMarshaller.*;
import Commons.Address;


public class ServerProxy
{
	public static void main(String args[])   {

try {
	new Configuration();

	Address dest = NamingService.lookup("NamingService");

	Message msg = new Message("ServerProxy", "I want to connect :(");

	Requestor r = new Requestor("ServerProxy");

	Marshaller m = new Marshaller();

	byte[] bytes = m.marshal(msg);

	bytes = r.deliver_and_wait_feedback(dest, bytes);

	Message answer = m.unmarshal(bytes);

	System.out.println("ServerProxy received message " + answer.data + " from " + answer.sender);

	ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());

	Address myAddr = NamingService.lookup("ServerProxy");

	Replyer r2 = new Replyer("ServerProxy", myAddr);

	while (true) {

		r2.receive_transform_and_send_feedback("ServerProxy", transformer);
//
//		Message answer2 = m.unmarshal(req);
//
//		if(answer2.data.equals("get_price, ABC SRL")){
//			float price = StockMarketServer.get_price(answer2.data);
//		}
	}
}
catch (Exception e) {
	System.out.println("Exception in ServerProxy!");
}
	}
}