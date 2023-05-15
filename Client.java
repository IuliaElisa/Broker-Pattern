
public class Client
{

	public static void main(String args[])   {
		new Configuration();

		String result = ClientProxy.execute("MathServer", "do_add,9,2 ");
		System.out.println("Response: "+ result);

	}

}