package entities.creatures;

import gfx.Animation;
import gfx.Assets;
import main.Game;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {


    //animations
    private Animation animDown, animUp, animLeft, animRight;


    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);


        //players bounding box, customize
        bounds.x = 16;
        bounds.y = 40;
        bounds.width = 24;
        bounds.height = 24;

        //animations
        animDown = new Animation(250, Assets.playerDown);
        animUp = new Animation(250, Assets.playerUp);
        animLeft = new Animation(250, Assets.playerLeft);
        animRight = new Animation(250, Assets.playerRight);

    }

    public void tick() {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }


    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);


        //show bounding box

//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }


    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return  animLeft.getCurrentFrame();
        } else if (xMove > 0){
            return animRight.getCurrentFrame();
        } else if (yMove < 0){
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            return animDown.getCurrentFrame();
        } else {
            return Assets.player;
        }
    }
}
