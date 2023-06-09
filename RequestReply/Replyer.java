package RequestReply;

import Commons.Address;
import MessageMarshaller.Marshaller;
import MessageMarshaller.Message;

import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.io.*;

public class Replyer
{
	private ServerSocket srvS;
	private Socket s;
	private InputStream iStr;
	private OutputStream oStr;
	private String myName;
	private String myAddr;
	
	public Replyer(String theName, String theAddr) {
              myName = theName; 
              myAddr = theAddr;
              try {
              	srvS = new ServerSocket(Integer.parseInt(myAddr.split(",")[1]));
				System.out.println("Replyer Serversocket:"+srvS);
			  }
			  catch (Exception e) {
                 System.out.println("Error opening server socket");
			  }
	}


	public byte[] receive_transform_and_send_feedback(String sender, ByteStreamTransformer t)
	{
		int val;
		byte buffer[] = null;
		try
		{
			s = srvS.accept();
System.out.println("Replyer accept: Socket"+s);
			iStr = s.getInputStream();
			val = iStr.read();
			buffer = new byte[val];
			iStr.read(buffer);
			byte[] data = t.transform(buffer, myName, sender);

			oStr = s.getOutputStream();
			oStr.write(data);
			oStr.flush();
			oStr.close();
			iStr.close();
			s.close();
			return data;

		}
		catch (IOException | InvocationTargetException | IllegalAccessException e) {
                      System.out.println("IOException in receive_transform_and_feedback"); } catch (
				NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException | InstantiationException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	protected void finalize() throws Throwable {
		super.finalize();
		srvS.close();
	}
}

