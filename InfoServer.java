import Commons.Address;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;
import RequestReply.Requestor;


public class InfoServer {

    private String company;
    public InfoServer( ){

        this.company = "ABC SRL";
        get_price("ABC SRL");
    }

    public static float get_price(String company) {
        float price=12345;
        System.out.println("get_price method executing." );
        return price;
    }
  public static void main(String[] args) {

try {
    new Configuration();

    Address dest = NamingService.lookup("NamingService");

    Message msg = new Message("InfoServer", "I want to connect :(");

    Requestor r = new Requestor("InfoServer");

    Marshaller m = new Marshaller();

    byte[] bytes = m.marshal(msg);

    bytes = r.deliver_and_wait_feedback(dest, bytes);

    Message answer = m.unmarshal(bytes);

    System.out.println("InfoServer received message " + answer.data + " from " + answer.sender);

    ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());

    Address myAddr = NamingService.lookup("InfoServer");

    Replyer r2 = new Replyer("InfoServer", myAddr);

    while (true) {
        bytes = r2.receive_transform_and_send_feedback("InfoServer", transformer);
        String message_from_client = m.unmarshal(bytes).data;
        System.out.println("receeeee " + message_from_client);
        String[] splits = message_from_client.split(",");
        System.out.println("splits 1:: "+splits[1] + splits.length);
        if (splits.length >2 && splits[1].contains("get_price")) {
            System.out.println("splits 1:: "+splits[2]);
            float result = get_price(splits[2]);


        }
    }
}
              catch (Exception e) {
                  System.out.println("Exception in InfoServer!");
              }
          }
}


