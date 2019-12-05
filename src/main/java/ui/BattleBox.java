package ui;

import gfx.Assets;
import gfx.Text;
import items.Item;
import main.Handler;
import states.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BattleBox extends UIObject {


    private BufferedImage[] images;
    private Handler handler;
    private ArrayList<String> choices;
    private int selectedChoice = 0;

    public BattleBox(float x, float y, int width, int height, BufferedImage[] images, Handler handler) {
        super(x, y, width, height);
        this.images = images;
        this.handler = handler;
        choices = new ArrayList<>();
        choices.add("Attack");
        choices.add("Spell");
        choices.add("Flee");
        choices.add("Bleh");
        choices.add("Bleh");
        choices.add("Bleh");

    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            if(selectedChoice != 0) {
                selectedChoice--;
                System.out.println("W");
            }
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
            if(selectedChoice != choices.size() - 1) {
                selectedChoice++;
                System.out.println("S");
            }
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
            getChoice();
        }

    }

    public void getChoice(){
        switch (choices.get(selectedChoice)){
            case "Flee":
                State.setState(handler.getGame().gameState);
                break;
            default:
                break;
        }

    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, width, height);



        int len = choices.size();
        if(len == 0)
            return;

        int textX = (int) x + 10, textY = (int) y + 20;

        for (int i = selectedChoice; i < len; i++) {
            if (i == selectedChoice) {
                Text.drawString(g, choices.get(i) + " <", textX,
                        textY, false, Color.YELLOW, Assets.font15);
            } else if (textY > y + height) {
                break;
            } else {
                Text.drawString(g, choices.get(i), textX,
                        textY, false, Color.WHITE, Assets.font15);
            }
            textY += 20;
        }
    }

    @Override
    public void onClick() {

    }
}
