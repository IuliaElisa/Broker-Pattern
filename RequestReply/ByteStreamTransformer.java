package RequestReply;

import java.lang.reflect.InvocationTargetException;

public interface ByteStreamTransformer
{
	public byte[] transform(byte[] in, String sender, String receiver) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InstantiationException;
}