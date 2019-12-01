package entities.creatures;

import gfx.Assets;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Monster extends Creature {

    public Monster(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }



    private BufferedImage getCurrentAnimationFrame(){
        return null;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die() {

    }
}
