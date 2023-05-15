import Registry.*;
public class Configuration
{
	public Configuration( )
	{

		Entry entryNaming = new Entry("127.0.0.1", 1110);
		NamingService.rebind("NamingService", "127.0.0.1", 1110);

//		Entry entrys = new Entry("127.0.0.1", 1114);
		NamingService.rebind("InfoServer", "127.0.0.1", 1114);

//		Entry entrym = new Entry("127.0.0.1", 1115);
		NamingService.rebind("MathServer", "127.0.0.1", 1115);

//		Entry entryc1 = new Entry("127.0.0.1", 1112);
		NamingService.rebind("ClientProxy", "127.0.0.1", 1112);

//		Entry entryc2 = new Entry("127.0.0.1", 1113);
		NamingService.rebind("ServerProxy", "127.0.0.1", 1113);

	}
}