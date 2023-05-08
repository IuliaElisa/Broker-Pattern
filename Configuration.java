import Registry.*;
public class Configuration
{
	public Configuration( )
	{

		Entry entryNaming = new Entry("127.0.0.1", 1110);
		NamingService.rebind("NamingService", entryNaming);

		Entry entryc1 = new Entry("127.0.0.1", 1112);
		NamingService.rebind("ClientProxy", entryc1);

		Entry entryc2 = new Entry("127.0.0.1", 1113);
		NamingService.rebind("ServerProxy", entryc2);

	}
}