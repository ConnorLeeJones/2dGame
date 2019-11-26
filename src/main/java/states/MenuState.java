package states;

import gfx.Assets;
import main.Game;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

public class MenuState extends State{


    private UIManager uiManager;


    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.startButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUiManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        //System.out.println("X: " + handler.getMouseManager().getMouseX() + "   Y: "  + handler.getMouseManager().getMouseY());
        uiManager.tick();
    }

    @Override

    public void render(Graphics g) {
        uiManager.render(g);
    }
}
