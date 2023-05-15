//import Registry.*;
public class Configuration
{
	public Configuration( )
	{

		NamingService.rebind("NamingService",1110);

//		NamingService.rebind("InfoServer", 1114);
//		NamingService.rebind("MathServer", 1115);
//		NamingService.rebind("ClientProxy",  1112);
//		NamingService.rebind("ServerProxy", 1113);

	}
}