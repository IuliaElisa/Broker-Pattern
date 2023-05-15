
public class InfoServer {

    public InfoServer(){
    }

  public static void main(String[] args) {

try {
    new Configuration();
    InfoServerImpl infoServerImpl = new InfoServerImpl();
    ServerProxy.execute("InfoServer", InfoServerImpl.class);

    }
catch (Exception e) {
                  System.out.println("Exception in InfoServer!");
              }
          }
}


