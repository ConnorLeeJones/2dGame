package states;

import main.Handler;
import ui.UIManager;

import java.awt.*;

public class BattleState extends State {

    private UIManager uiManager;

    public BattleState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
