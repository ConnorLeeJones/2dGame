package states;

import gfx.Assets;
import gfx.Text;
import main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class TextState extends State {

    private String[] textArray;
    private String text;
    private int textIndex = 0;

    public TextState(Handler handler, String text) {
        super(handler);
        this.text = text;
        this.textArray = text.split(">");
        this.textArray[0] = " " + this.textArray[0];
    }


    @Override
    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)){
            textIndex++;
        }

    }

    @Override
    public void render(Graphics g) {
        handler.getGame().gameState.render(g);

        if (textIndex == textArray.length){
            State.setState(handler.getGame().gameState);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(10, handler.getHeight() - 110, handler.getWidth() - 20, 100);
            g.setColor(Color.BLACK);
            g.drawRect(10, handler.getHeight() - 110, handler.getWidth() - 20, 100);
            if (textArray[textIndex].length() > 100) {
                ArrayList<String> strings = new ArrayList<>();

                int x = 0;
                StringBuilder builder = new StringBuilder();
                while (x < textArray[textIndex].length()) {
                    builder.append(textArray[textIndex].charAt(x));

                    if (Character.toString(textArray[textIndex].charAt(x)).equals(" ") && builder.length() > 55 || x == textArray[textIndex].length() - 1){
                        strings.add(builder.toString());
                        builder = new StringBuilder();
                    }
                    x++;
                }

                int y = handler.getHeight() - 80;

                for (String string : strings) {
                    Text.drawString(g, string, 20,
                            y, false, Color.BLACK, Assets.font15);
                    y += 20;
                }

            } else {
                Text.drawString(g, textArray[textIndex], 20,
                        handler.getHeight() - 80, false, Color.BLACK, Assets.font15);
            }
        }

    }



    public String[] getTextArray() {
        return textArray;
    }

    public void setTextArray(String[] textArray) {
        this.textArray = textArray;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextIndex() {
        return textIndex;
    }

    public void setTextIndex(int textIndex) {
        this.textIndex = textIndex;
    }
}
