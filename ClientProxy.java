import Commons.Address;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;
import RequestReply.Requestor;

public class ClientProxy
{
    public static void main(String args[]){
        try {
            new Configuration();

            Address dest = NamingService.lookup("NamingService");

            Message msg = new Message("ClientProxy", "I want to connect :(");

            Requestor r = new Requestor("ClientProxy");

            Marshaller m = new Marshaller();

            byte[] bytes = m.marshal(msg);

            bytes = r.deliver_and_wait_feedback(dest, bytes);

            Message answer = m.unmarshal(bytes);

            System.out.println("ClientProxy received message " + answer.data + " from " + answer.sender);

            ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());

            Address myAddr = NamingService.lookup("ClientProxy");

            Replyer r2 = new Replyer("ClientProxy", myAddr);

            while (true) {
                bytes = r2.receive_transform_and_send_feedback("ClientProxy", transformer);
                String message_from_client = m.unmarshal(bytes).data;
                System.out.println("receeeee " + message_from_client);
                String[] splits = message_from_client.split(",");

                if(splits.length > 1){
                    String serverName = splits[0];
                    System.out.println("serverName "+serverName);

                    dest = NamingService.lookup(serverName);

                    msg = new Message("ClientProxy", message_from_client);

                    r = new Requestor("ClientProxy");

                    bytes = m.marshal(msg);

                    bytes = r.deliver_and_wait_feedback(dest, bytes);

                    answer = m.unmarshal(bytes);
                    System.out.println("In if:::: "+ answer.data);
                }
            }
        }
        catch (Exception e) {
            System.out.println("Exception in ClientProxy!");
        }
    }

}



