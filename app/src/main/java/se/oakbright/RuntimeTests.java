package se.oakbright;

/**
 * Created by hampuse on 2015-07-23.
 */
public class RuntimeTests {
    public static void testForNull(Object object,String identifier){
        if(object == null){
            throw new NullPointerException("Failed testForNull: "+ identifier);
        }
    }
}
