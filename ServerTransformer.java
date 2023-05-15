import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;
import RequestReply.ByteStreamTransformer;

import java.lang.reflect.InvocationTargetException;

public class ServerTransformer implements ByteStreamTransformer {
    private final MessageServer originalServer;
    private String message;
    private Class<?> implem;

    public ServerTransformer(MessageServer s, String message_, Class<?> implem_)
    {   message = message_;
        implem = implem_;
        originalServer = s;
    }

    public byte[] transform(byte[] in, String sender, String receiver) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        Message msg;
        Marshaller m = new Marshaller();
        msg = m.unmarshal(in);

        Message answer = originalServer.get_answer(msg, sender, implem);

        byte[] bytes = m.marshal(answer);
        return bytes;
    }
}