package entities.creatures.monsters;

import entities.creatures.Creature;
import gfx.Assets;
import gfx.Text;
import main.Handler;
import stats.StatCreator;
import stats.Stats;

import java.awt.*;


public abstract class Monster extends Creature {

    //private String name;


    public Monster(Handler handler, float x, float y, int width, int height, int level, String name) {
        super(handler, x, y, width, height, name);
        stats = StatCreator.newMonsterStats(level);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
        Text.drawString(g, "HP: " + this.getStat(Stats.HP) + "/" + this.getStat(Stats.MAX_HP), (int) x,
                (int) y + height + 20, false, Color.BLACK, Assets.font15);
    }

    @Override
    public void die() {

    }

}
