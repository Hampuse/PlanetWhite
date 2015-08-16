package se.oakbright.resourcesketch;

/**
 * Created by hampuse on 2015-08-16.
 */
public class Res implements BasicRes{
    public BasicRes basicRes;
    public Res(){
    }

    @Override
    public Object getBasic1() {
        return basicRes.getBasic1();
    }

    @Override
    public Object getBasic2() {
        return basicRes.getBasic2();
    }
}
