package tiles;

import gfx.Assets;


public class GrassTile extends Tile {


    public GrassTile(int id) {
        super(Assets.grass, id);
        int rand = (int) (Math.random() * 10) + 1;
        if (rand >= 5)
            enemy = true;
    }

}
