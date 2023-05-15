
package RequestReply;

import Commons.Address;

import java.net.*;
import java.io.*;


public class Requestor
{

	private Socket s;
	private OutputStream oStr;
	private InputStream iStr;
	private String myName;
	public Requestor(String theName) { myName = theName; }


	public byte[] deliver_and_wait_feedback(String theDest, byte[] data)
	{

		byte[] buffer = null;
		int val;
		try
		{
			int port =  Integer.parseInt(theDest.split(",")[1]);
			s = new Socket("127.0.0.1", port);
System.out.println("Requestor: Socket"+s);
			oStr = s.getOutputStream();
			oStr.write(data);
			oStr.flush();

			iStr = s.getInputStream();

			val = iStr.read();
			buffer = new byte[val];
			iStr.read(buffer);
			iStr.close();
			oStr.close();
			s.close();
			}
		catch (IOException e) {
                       System.out.println("IOException in deliver_and_wait_feedback"); }
		return buffer;
	}

}

