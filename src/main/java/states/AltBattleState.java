package states;

import battle.Battle;
import entities.creatures.monsters.Monster;
import gfx.Assets;
import main.Handler;
import stats.Stats;
import ui.BattleBox;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;
import java.util.Iterator;

public class AltBattleState extends State {

    //private UIManager uiManager;
    private Battle battle;
    private BattleBox battleBox;

    public AltBattleState(Handler handler, int numberOfMonsters) {
        super(handler);
        //uiManager = new UIManager(handler);
        //handler.getMouseManager().setUiManager(uiManager);
        this.musicIndex = 1;
        battle = new Battle(handler, handler.getWorld().getEntityManager().getPlayer(), numberOfMonsters);

    }

    @Override
    public void tick() {
        //battleBox.tick();

    }


    @Override
    public void render(Graphics g) {
        //uiManager.render(g);

        removeDeadCreatures();

        for (Monster monster : battle.getMonsters()){
            monster.render(g);
        }


        Rectangle rectangle = new Rectangle(0, 0, 48, 48);
        //g.drawRect(0, 0, 48, 48);

        for (int i = 0; i <= 640; i+= 32) {
            for (int j = 0; j <= 480; j+= 32) {
                g.drawRect(i, j, 32, 32);

            }
        }
    }


    public void removeDeadCreatures(){
        battle.getMonsters().removeIf(mon -> mon.getStat(Stats.HP) <= 0);
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
