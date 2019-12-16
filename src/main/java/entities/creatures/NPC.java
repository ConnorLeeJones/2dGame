package entities.creatures;

import gfx.Assets;
import main.Handler;
import ui.TextBox;

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


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        if(Math.abs(handler.getWorld().getEntityManager().getPlayer().getX() - x) <= 50 &&
                Math.abs(handler.getWorld().getEntityManager().getPlayer().getY() - y) <= 50){
            if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
                TextBox textBox = new TextBox(10, 350, handler.getWidth() - 20, 125, text, handler);
                System.out.println(text);
                textBox.render(g);
            }
        }

    }

    @Override
    public void die() {

    }
}
