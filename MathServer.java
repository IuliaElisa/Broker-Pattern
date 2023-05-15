public class MathServer  {


    public MathServer( ){}


    public static void main(String[] args) {

        try {
      new Configuration();
            MathServerImpl mathServerImpl = new MathServerImpl();
            ServerProxy.execute("MathServer", MathServerImpl.class, 1115);

        }
        catch (Exception e) {
            System.out.println("Exception in MathServer!");
        }
    }
}


