import RequestReply.*;
import MessageMarshaller.*;
import Registry.*;
import Commons.Address;


public class ServerWithRR
{
	public static void main(String args[])   {

try {
	new Configuration();

	System.out.println("\nStockMarketServer main registered to NAMING SERVICE");
	Address dest = NamingService.lookup("NamingService");

	Message msg = new Message("Server", "I want to connect :(");

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
		byte[] req = r2.receive_transform_and_send_feedback(transformer);

		Message answer2 = m.unmarshal(req);

		if(answer2.data.equals("get_price, ABC SRL")){
			StockMarketImpl stockMarket = new StockMarketImpl();
			stockMarket.get_price("ABC SRL");
		}
	}

}
catch (Exception e) {
	System.out.println("Exception in SERVER RR!");
}

	}

}