//import java.util.Hashtable;
//
//import Registry.*;
//public class Registry
//{
//	public  static Hashtable<String, Entry> hTable = new Hashtable();
//	private static Registry _instance = null;
//
//	private Registry() { }
//
//	public static Registry instance()
//	{
//		if (_instance == null)
//			_instance = new Registry();
//		return _instance;
//	}
//
//	public static void printHashTable(){
//			System.out.println(instance().hTable);
//
//	}
//
//	public  Hashtable returnHashTable(){
//		return hTable;
//	}
//
//	public void put(String theKey, Entry theEntry)
//	{
//		hTable.put(theKey, theEntry);
//	}
//	public Entry get(String aKey)
//	{
//		return (Entry)hTable.get(aKey);
//	}
//}
//
//
//
//
