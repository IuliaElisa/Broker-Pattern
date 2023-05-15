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

            String[] splits = NamingService.lookup("NamingService").split(",");

            String dest = splits[0] + ","+Integer.parseInt(splits[1]);
            Message msg = new Message(serverName, "NamingService" + ","+serverName + ","+"lookup");
            Marshaller m = new Marshaller();
            Requestor r = new Requestor(serverName);
            byte[] bytes = m.marshal(msg);
            bytes = r.deliver_and_wait_feedback(dest, bytes);
            Message answer = m.unmarshal(bytes);

            dest = answer.data;
            msg = new Message("ClientProxy",serverName + ","+method);
            r = new Requestor("ClientProxy");
            m = new Marshaller();
            bytes = m.marshal(msg);
            bytes = r.deliver_and_wait_feedback(dest, bytes);

             answer = m.unmarshal(bytes);
            return answer.data;
        }

        catch (Exception e) {
            System.out.println("Exception in ClientProxy!");
        }
        return null;
    }

}



