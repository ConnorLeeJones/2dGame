package states;

import entities.creatures.Player;
import gfx.Assets;
import main.Game;
import main.Handler;
import save.ResourceManager;
import save.SaveData;
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

        uiManager.addObject(new UIImageButton(250, 150, 128, 64, Assets.startButton, () -> {
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);
        }));

        uiManager.addObject(new UIImageButton(250, 250, 128, 64, Assets.startButton, () -> {
            handler.getMouseManager().setUiManager(null);


            try {
                SaveData data = (SaveData) ResourceManager.load("1.save");
                Player player = handler.getWorld().getEntityManager().getPlayer();
                player.setX(data.getX());
                player.setY(data.getY());
                player.setName(data.getName());
                player.setStats(data.getStats());
                player.setSpellBook(data.getSpellBook());
                player.getInventory().loadInventoryData(data.getInventorySaveData());
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
    }
}
