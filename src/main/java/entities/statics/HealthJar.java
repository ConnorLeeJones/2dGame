package entities.statics;

import items.Item;
import main.Handler;

public class HealthJar extends Jar {
    public HealthJar(Handler handler, float x, float y) {
        super(handler, x, y);
    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.healthPotion.createNew((int) x, (int) y));
    }
}
