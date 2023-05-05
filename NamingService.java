
import java.io.IOException;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class NamingService {
    public static final int PORT = 8080;
    public static final Map<String, Object> nameToAddressMap = new HashMap<>();

    private static NamingService _instance = null;

    private NamingService() {
        System.out.println("New oneee");
    }

    public static NamingService instance()
    {
        if (_instance == null)
            _instance = new NamingService();
        return _instance;
    }

    public static Object lookup(String name) {

        System.out.println("naa "+nameToAddressMap);
        return nameToAddressMap.get(name);
    }
    public static void rebind(String name, Object object) {

        nameToAddressMap.put(name, object);

        for( Map.Entry<String, Object> entry : nameToAddressMap.entrySet() ){
            System.out.println( entry.getKey() + " = " + entry.getValue() );
        }

        //System.out.println("naa "+nameToAddressMap);

    }

    public static void unregisterServer(String name) {

        nameToAddressMap.remove(name);
    }


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket(PORT, 0, InetAddress.getLocalHost());
        System.out.println("\nNamingService started at: " + serverSocket.getInetAddress().getHostAddress()
                + ":" + PORT);

        new Configuration();

        ByteStreamTransformer transformer = new ServerTransformer(new MessageServer());

        Address myAddr = Registry.instance().get("NamingService");

        Replyer r = new Replyer("NamingService", myAddr);

        while (true) {
            r.receive_transform_and_send_feedback(transformer);
        }



		/*while (true) {
			Socket clientSocket = serverSocket.accept();


			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			String message = in.readLine();
			System.out.println(message);

			String[] parts = message.split(",");
			String serverName = parts[0];
			String serverAddress = nameToAddressMap.get(serverName).toString();
			System.out.println("Server address");
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println(serverAddress);

			in.close();
			out.close();
			clientSocket.close();
		}*/
    }

}
