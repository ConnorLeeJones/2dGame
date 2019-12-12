package states;

import gfx.Assets;
import gfx.Text;
import main.Game;
import main.Handler;
import save.ResourceManager;
import save.SaveData;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

public class SaveState extends State{


    private UIManager uiManager;


    public SaveState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIImageButton(250, 150, 128, 64, Assets.startButton, () -> {
            handler.getMouseManager().setUiManager(null);
            SaveData saveData = new SaveData(handler.getWorld().getEntityManager().getPlayer());

            try {
                ResourceManager.save(saveData, "1.save");
                State.setState(handler.getGame().gameState);
            } catch (Exception e) {
                e.printStackTrace();
            }


            State.setState(handler.getGame().gameState);
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
        Text.drawString(g, "Save game?", 100, 100, true, Color.BLACK, Assets.font28);

    }
}
