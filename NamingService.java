import Commons.Address;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class NamingService {
    public  static Hashtable<String, Entry> hTable = new Hashtable<String, Entry>();
    private static NamingService _instance = null;

    public static NamingService instance()
    {
        if (_instance == null)
            _instance = new NamingService();
        return _instance;
    }

    public void put(String theKey, Entry theEntry)
    {
        hTable.put(theKey, theEntry);
    }
    public Entry get(String aKey) { return hTable.get(aKey);}

    public static String lookup(String name) {
        String key = null;
        for (Map.Entry<String, Entry> entry : NamingService.hTable.entrySet()){
            if (name.equals(entry.getKey())) {
                key = entry.getKey();
                break;
            }
        }
        return key+","+ NamingService.instance().get(name).port();
    }

    public static void rebind(String name, int port) {
        Entry entry = new Entry("127.0.0.1", port);
        NamingService.instance().put(name, entry);
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        new Configuration();
        ByteStreamTransformer transformer = new ServerTransformer( new MessageServer(), "0, nothing", null);

        String myAddr = NamingService.lookup("NamingService");

        Replyer r = new Replyer("NamingService", myAddr);

        while (true) {
            r.receive_transform_and_send_feedback("NamingService", transformer);
        }
    }

}
