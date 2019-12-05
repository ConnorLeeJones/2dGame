package states;

import battle.Battle;
import entities.creatures.monsters.Monster;
import gfx.Assets;
import main.Handler;
import ui.BattleBox;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

public class BattleState extends State {

    //private UIManager uiManager;
    private Battle battle;
    private BattleBox battleBox;

    public BattleState(Handler handler, int numberOfMonsters) {
        super(handler);
        //uiManager = new UIManager(handler);
        //handler.getMouseManager().setUiManager(uiManager);
        battle = new Battle(handler, handler.getWorld().getEntityManager().getPlayer(), numberOfMonsters);
        battleBox = new BattleBox(10, 350, handler.getWidth() - 20, 120, null, handler);

    }

    @Override
    public void tick() {
        battleBox.tick();

    }

    @Override
    public void render(Graphics g) {
        //uiManager.render(g);
        for (Monster monster : battle.getMonsters()){
            monster.render(g);
        }
        battleBox.render(g);
    }

//    public UIManager getUiManager() {
//        return uiManager;
//    }
//
//    public void setUiManager(UIManager uiManager) {
//        this.uiManager = uiManager;
//    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }
}
