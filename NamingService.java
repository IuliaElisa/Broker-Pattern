
import Commons.Address;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import Registry.*;


public class NamingService {
    public static final int PORT = 8080;
    private NamingService() {}

    public static Address lookup(String name) {
        return Registry.instance().get(name);
    }
    public static void rebind(String name, Entry object) {
        Registry.instance().put(name, object);

    }
    public static void main(String[] args) throws IOException {

        new Configuration();


        ServerSocket serverSocket = new ServerSocket(PORT, 0, InetAddress.getLocalHost());
        System.out.println("\nNamingService started at: " + serverSocket.getInetAddress().getHostAddress()
                + ":" + PORT);


        ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());

        Address myAddr = NamingService.lookup("NamingService");

        Replyer r = new Replyer("NamingService", myAddr);

        while (true) {
            r.receive_transform_and_send_feedback(transformer);
        }
    }

}
