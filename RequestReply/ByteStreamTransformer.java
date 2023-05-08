package RequestReply;

public interface ByteStreamTransformer
{
	public byte[] transform(byte[] in, String sender, String receiver);
}