package entities.statics;

import items.Item;
import main.Handler;

public class MagicJar extends Jar {

    public MagicJar(Handler handler, float x, float y) {
        super(handler, x, y);
    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.magicPotion.createNew((int) x, (int) y));
    }
}
