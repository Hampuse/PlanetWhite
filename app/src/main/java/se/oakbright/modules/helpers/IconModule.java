package se.oakbright.modules.helpers;

import android.graphics.Bitmap;

import se.oakbright.icons.IconCreater;
import se.oakbright.icons.InIcon;

/**
 * Created by hampuse on 2015-07-15.
 */
public class IconModule {
    private InIcon icon;
    //public IconIdBlueprint iconIdBlueprint;
    private boolean hasBeenInitialized;

    public Bitmap getBitmap(){
        return icon.getBitmap();
    }


    //private boolean hasBeenInitialized = false;

   // protected IconModule(IconIdHolder iconIdHolder){
    //}
   // public IconModule(){//IconIdBlueprint iconIdBlueprint){
     //   this.iconIdBlueprint = iconIdBlueprint;
    //}

    /*public void initialize(){
        if( ! hasBeenInitialized) {
            if (iconIdBlueprint == null) {
                throw new NullPointerException("iconIdBlueprint is null");
            }
            BattleModel battleModel = ServiceProvider.getBattleModel();
            this.icon = battleModel.getIcon(iconIdBlueprint.getIconId());
            iconIdBlueprint = null;
            this.hasBeenInitialized = true;
        }
    }*/

    /*public void initialize() {
        BattleModel battleModel = ServiceProvider.getBattleModel();
        this.icon = battleModel.getIcon(iconIdBlueprint.getIconId());
        hasBeenInitialized = true;
    }*/



    /*
    public static class Builder extends Buildable<IconModule> {
        public IconCreater iconCreater;

        @Override
        protected IconModule buildNew(){
            IconModule iconModule = new IconModule();
            iconModule.icon = iconCreater.getBuilt();
            verifyNotNull(iconModule.icon);
            return iconModule;
        }
    }*/
    /*public void setIconIdBlueprint(IconIdBlueprint iconIdBlueprint){
        this.iconIdBlueprint = iconIdBlueprint;
    }*/


  /*  public static class IconModuleSetter extends IconModule(){
        public IconIdBlueprint iconIdBlueprint;
        public getIconModule{
            if(iconIdBlueprint == null){
                throw new NullPointerException("iconIdBlueprint is null");
            }
            BattleModel battleModel = ServiceProvider.getBattleModel();
            this.icon = battleModel.getIcon(iconIdBlueprint.getIconId());
            iconIdBlueprint = null;
        };
    }*/
   /* public abstract static class IconIdHolder implements Serializable {
        protected abstract IconId getIconId();
    }
    /*
        protected Class<? extends IconModule> objectClass;


        public IconIdHolder(Class<? extends IconModule> objectClass){
            this.objectClass = objectClass;
        }

        public IconModule build(){
            IconModule iconModule;
            try {
                Constructor<? extends IconModule> constructor = this.objectClass.getConstructor(new Class[]{});
                try {
                    iconModule = constructor.newInstance();

                    return iconModule;
                } catch (IllegalArgumentException e) {
                    Log.e("TAG", "error when using reflection constructor");
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                Log.e("TAG","The constructor seem to not exist. reflection constructor");
                e.printStackTrace();
            }
            Log.e("TAG","unable to create object of class, returning null. Possibly the class is not subclass of Ship, or it's an abstract class ");
            return null;
        }
    }


    public static class Builder implements Serializable {
        protected Class<? extends IconModule> objectClass;


        public Builder(Class<? extends IconModule> objectClass){
            this.objectClass = objectClass;
        }

        public IconModule build(){
            IconModule iconModule;
            try {
                Constructor<? extends IconModule> constructor = this.objectClass.getConstructor(new Class[]{});
                try {
                    iconModule = constructor.newInstance();

                    return iconModule;
                } catch (IllegalArgumentException e) {
                    Log.e("TAG", "error when using reflection constructor");
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    Log.e("TAG","error when using reflection constructor");
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                Log.e("TAG","The constructor seem to not exist. reflection constructor");
                e.printStackTrace();
            }
            Log.e("TAG","unable to create object of class, returning null. Possibly the class is not subclass of Ship, or it's an abstract class ");
            return null;
        }
    }*/
}
