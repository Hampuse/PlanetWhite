package se.oakbright.icons;

import se.oakbright.Blueprint;
import se.oakbright.RuntimeTests;
import se.oakbright.planetwhite.BattleModel;
import se.oakbright.planetwhite.ServiceProvider;

/**
 * Created by hampuse on 2015-07-18.
 */
public abstract class IconCreater extends Blueprint<InIcon> {      //TODO SHOULD BE NAMED IconBuilder
   // public Icon icon;

    @Override
    protected InIcon buildNew() {
        BattleModel battleModel = ServiceProvider.getBattleModel();
        InIcon icon = battleModel.getIcon(getIconId());
        RuntimeTests.testForNull(icon, "icon");
        return icon;
    }

    protected abstract IconId getIconId();

}
