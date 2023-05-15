import Commons.Address;
import Registry.*;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class NamingService {
    public static final int PORT = 8080;

    private NamingService() {}
    public static Address lookup(String name) {
        return Registry.instance().get(name);
    }

    public static void rebind(String name, String dest, int port ) {
        Entry entry = new Entry(dest, port);
        Registry.instance().put(name, entry);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        new Configuration();
        ByteStreamTransformer transformer = new ServerTransformer( new MessageServer(), "NamingService, nothing", null);

        Address myAddr = NamingService.lookup("NamingService");

        Replyer r = new Replyer("NamingService", myAddr);

        while (true) {
            r.receive_transform_and_send_feedback("NamingService", transformer);
        }
    }

}
