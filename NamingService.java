
import Commons.Address;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;

import java.io.IOException;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import Registry.Registry;
import Registry.Entry;

public class NamingService {
    public static final int PORT = 8080;
    //public static final Map<String, Object> nameToAddressMap = new HashMap<>();

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

    public static Address lookup(String name) {

        return Registry.instance().get(name);
    }
    public static void rebind(String name, Entry object) {
        Registry.instance().put(name, object);

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
