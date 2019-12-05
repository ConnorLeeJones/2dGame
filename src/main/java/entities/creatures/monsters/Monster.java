package entities.creatures.monsters;

import entities.creatures.Creature;
import gfx.Assets;
import main.Handler;
import stats.StatCreator;

import java.awt.*;


public abstract class Monster extends Creature {

    private String name;


    public Monster(Handler handler, float x, float y, int width, int height, int level, String name) {
        super(handler, x, y, width, height);
        stats = StatCreator.newMonsterStats(level);
        this.name = name;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, width, height, null);
    }

    @Override
    public void die() {

    }

}
