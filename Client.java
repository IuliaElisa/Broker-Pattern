
public class Client
{

	public static void main(String args[])   {
		new Configuration();

		//String result = ClientProxy.execute("MathServer", "do_add,9,2 ");
		String result = ClientProxy.execute("MathServer", "do_sqr,9 ");
		//String result = ClientProxy.execute("InfoServer", "get_temp,Paris,");

		//String result = ClientProxy.execute("InfoServer", "get_road_info,123,");
		System.out.println("Response: "+ result);
	}

}