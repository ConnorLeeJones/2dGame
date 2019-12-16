package entities.creatures;

import gfx.Assets;
import main.Handler;
import states.State;
import states.TextState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NPC extends Creature {

    public String text;


    public NPC(Handler handler, float x, float y, int width, int height, String name, String text) {
        super(handler, x, y, width, height, name);
        this.text = text;
    }

    @Override
    public void tick() {
        if(Math.abs(handler.getWorld().getEntityManager().getPlayer().getX() - x) <= 50 &&
                Math.abs(handler.getWorld().getEntityManager().getPlayer().getY() - y) <= 50){
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                //TextBox textBox = new TextBox(10, 350, handler.getWidth() - 20, 125, text, handler);
                //State textState = handler.getGame().textState;
                State.setState(new TextState(handler, "Hello, welcome to my first game.> I know it's not that cool, but I hope you like it!>" +
                        "This game is made entirely by Connor Jones This game is made entirely by Connor Jones This game is made entirely by Connor Jones This game is made entirely by Connor Jones.>"));
                System.out.println(text);
                //textBox.render(g);
            }
        }

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

    }

    @Override
    public void die() {

    }
}
