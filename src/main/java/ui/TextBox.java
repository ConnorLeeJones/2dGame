package ui;

import gfx.Assets;
import gfx.Text;
import main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TextBox extends UIObject {

    private String text;
    private Handler handler;

    public TextBox(float x, float y, int width, int height, String text, Handler handler) {
        super(x, y, width, height);
        this.text = text;
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        while (!handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
            g.setColor(Color.WHITE);
            g.fillRect((int) x, (int) y - 50, width, 48);
            Text.drawString(g, text, (int) x + 10,
                    (int) y + 20, false, Color.BLACK, Assets.font15);
        }

    }

    @Override
    public void onClick() {

    }
}
