import Commons.Address;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;
import RequestReply.Requestor;

public class ClientProxy
{

    public static String execute(String serverName, String method) {
        try {
            new Configuration();

            Address dest = NamingService.lookup(serverName);
            Message msg = new Message("ClientProxy",serverName + ","+method);
            Requestor r = new Requestor("ClientProxy");
            Marshaller m = new Marshaller();
            byte[] bytes = m.marshal(msg);
            bytes = r.deliver_and_wait_feedback(dest, bytes);

            Message answer = m.unmarshal(bytes);
            return answer.data;
        }

        catch (Exception e) {
            System.out.println("Exception in ClientProxy!");
        }
        return null;
    }

}



