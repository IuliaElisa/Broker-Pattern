public class MathServerImpl implements MathServerOperations{
    public float do_add(Object a, Object b) {

        float val1 = checkForType(a);
        float val2 = checkForType(b);
        System.out.println("do_add method executing.");
        return val1+val2;
    }

    public double do_sqr(Object a) {

        float val1 = checkForType(a);
        System.out.println("do_sqr method executing." );
        return Math.sqrt(val1);
    }

    public float checkForType(Object a){
        float floatValue = 0;
        if (a instanceof Float) {
             floatValue = ((Float) a).floatValue();
        }
        else
        if (a instanceof String) {
            try {
                floatValue = Float.parseFloat((String) a);
            } catch (NumberFormatException e) {
            }
        }
        else {
            System.out.println("Invalid parameters type.");
        }
        return floatValue;
    }
}
