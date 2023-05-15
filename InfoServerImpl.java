public class InfoServerImpl implements  InfoServerOperations{
    public float get_temp(Object city) {
        float temp = 23.3f;
        System.out.println("get_temp method executing." );
        return temp;
    }

    public String get_road_info(Object roadID) {
        System.out.println("get_road_info method executing.");
        return "Good road.";
    }


}
