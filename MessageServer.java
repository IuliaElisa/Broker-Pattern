import Commons.Address;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.Requestor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class MessageServer {


    public Message get_answer(Message msg, String sender, Class<?> implem) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        System.out.println("receeeee " + msg.data);
        String[] splits = msg.data.split(",");

        if (splits.length > 1 && splits[0].contains("Server")) {
            String serverName = splits[0];
            System.out.println("serverName " + serverName);

            String methodName = splits[1];
            System.out.println("method is :" + methodName+":::");

            Method[] InstanceMethods  = implem.getDeclaredMethods();

            Object result = null;
            for(Method method:InstanceMethods){
                if(method.getName().contains(methodName)){
                    Constructor<?> constructor = implem.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Object instance = constructor.newInstance();
                    System.out.println("nb params @@@@@@@@@@"+method.getParameterCount());

                    if(method.getParameterCount() == 2) {
                        System.out.println("split[2] :" + splits[2]);
                        System.out.println("split[3] :" + splits[3]);

                        result = method.invoke(instance, splits[2], splits[3]);
                    }
                    if(method.getParameterCount() == 1){
                        System.out.println("split[222] :"+splits[2]);
                        result = method.invoke(instance, splits[2]);


                    }
            }
            }
            System.out.println("result is: "+result);

            assert result != null;
            Message answer = new Message(sender,result.toString());
            return answer;
        }

        else{
            System.out.println("Called "+splits[0]);
        }
        return msg;
    }
}
