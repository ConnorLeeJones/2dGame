package entities.statics;

import gfx.Assets;
import items.Item;
import main.Handler;
import tiles.Tile;

import java.awt.*;

public abstract class Jar extends StaticEntity {

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
    public void render(Graphics g) {
        g.drawImage(Assets.jar, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
