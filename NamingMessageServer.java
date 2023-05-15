import Commons.Address;
import MessageMarshaller.Message;
import  Registry.*;

import java.lang.reflect.Array;
import java.util.Arrays;

public class NamingMessageServer {
    public Object get_answer(Message msg, String sender, String receiver) {


        String[] splits = msg.data.split(",");
        Address answer;

        if(splits[0].contains("lookup")){
            System.out.println(receiver + " lookup " + msg.data + " from " + msg.sender);

            answer= NamingService.lookup(splits[1]);

        }

        if(splits[0].contains("rebind")){
            System.out.println(receiver + " rebind " + msg.data + " from " + msg.sender);

            NamingService.rebind(splits[1], splits[2], Integer.parseInt(splits[3]));

        }


      //  System.out.println(receiver + " received " + msg.data + " from " + msg.sender);

        Message answer2 = new Message(sender, "Did operation: " + Arrays.toString(splits));
        return answer2;
    }
}

