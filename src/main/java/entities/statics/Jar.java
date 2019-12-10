package entities.statics;

import gfx.Assets;
import items.Item;
import main.Handler;
import tiles.Tile;

import java.awt.*;

public class Jar extends StaticEntity {

    public Jar(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        //tree bounding box, customize
        bounds.x = 16;
        bounds.y = 40;
        bounds.width = 24;
        bounds.height = 24;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die(){
        int rand = (int) (Math.random() * 3 + 1);
        System.out.println(rand);
        if (rand == 1){
            handler.getWorld().getItemManager().addItem(Item.magicPotion.createNew((int) x, (int) y));
        } else if (rand == 2){
            handler.getWorld().getItemManager().addItem(Item.healthPotion.createNew((int) x, (int) y));
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.jar, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
