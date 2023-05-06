import Commons.Address;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;
import RequestReply.Requestor;


import RequestReply.*;
        import MessageMarshaller.*;
        import Registry.*;
        import Commons.Address;

        import java.io.InputStream;
        import java.io.OutputStream;
        import java.net.ServerSocket;
        import java.net.Socket;


public class ClientProxy
{
    public static void main(String args[])   {

        try {
            new Configuration();

            System.out.println("\nClientProxy main registered to NAMING SERVICE");
            Address dest = NamingService.lookup("NamingService");

            Message msg = new Message("ClientProxy", "I want to connect:(");

            Requestor r = new Requestor("Server");

            Marshaller m = new Marshaller();

            byte[] bytes = m.marshal(msg);

            bytes = r.deliver_and_wait_feedback(dest, bytes);

            Message answer = m.unmarshal(bytes);

            System.out.println("ClientProxy received message " + answer.data + " from " + answer.sender);

            ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());

            Address myAddr = NamingService.lookup("ClientProxy");

            Replyer r2 = new Replyer("ClientProxy", myAddr);

            while (true) {

                 r2.receive_transform_and_send_feedback(transformer);

            }
        }
        catch (Exception e) {
            System.out.println("Exception in SERVER RR!");
        }
    }
}