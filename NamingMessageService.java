//import Commons.Address;
//import MessageMarshaller.Message;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//public class NamingMessageService {
//
//    public Object get_answer(Message msg, String sender, Class<?> implem) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, InstantiationException {
//
//        System.out.println("receeeee " + msg.data);
//        String[] splits = msg.data.split(",");
//
//        if(splits[0].contains("Naming")) {
//            String serverName = splits[1];
//
//            if(splits[2].contains("rebind")){ // operation of rebinding
//                int portName = Integer.parseInt(splits[3]);
//                System.out.println("sv name:" +serverName + "|...port:"+portName+".");
//                NamingService.rebind(serverName, portName);
//                return null;
//            }
//
//            if(splits[2].contains("lookup")){ // operation of rebinding
//                String name = splits[1];
//                System.out.println("sv name:" +name + "|");
//                Address addressInfo = NamingService.lookup(name);
//                return addressInfo;
//            }
//        }
//
//        return msg;
//    }
//}
//
