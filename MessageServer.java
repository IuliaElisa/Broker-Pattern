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


    public Object get_answer(Message msg, String sender, Class<?> implem) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {

        String[] splits = msg.data.split(",");

        if (splits[0].contains("Server")) {
            String methodName = splits[1];

            Method[] InstanceMethods  = implem.getDeclaredMethods();

            Object result = null;
            for(Method method:InstanceMethods){
                if(method.getName().contains(methodName)){
                    Constructor<?> constructor = implem.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    Object instance = constructor.newInstance();

                    if(method.getParameterCount() == 2) {
                        result = method.invoke(instance, splits[2], splits[3]);
                    }
                    if(method.getParameterCount() == 1){
                        result = method.invoke(instance, splits[2]);
                    }
            }
            }
            System.out.println("result is: "+result);
            assert result != null;
            Message answer = new Message(sender,result.toString());
            return answer;
        }

        else
            if(splits[0].contains("Naming")) {
                String serverName = splits[1];

                if(splits[2].contains("rebind")){ // operation of rebinding
                    int portName = Integer.parseInt(splits[3]);
                    NamingService.rebind(serverName, portName);
                    String addressInfo = NamingService.lookup(serverName);
                    return new Message("NamingService", addressInfo.toString());
                }

                if(splits[2].contains("lookup")){ // operation of lookup
                    String name = splits[1];
//                    System.out.println("sv name:" +name + "..");
                    String addressInfo = NamingService.lookup(name);
                    return new Message("NamingService", addressInfo.toString());
                }
            }
        return msg;
    }
}




