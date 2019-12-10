package battle;

import entities.creatures.Creature;
import entities.creatures.Player;
import entities.creatures.monsters.*;
import main.Handler;
import stats.Stats;
import utils.Dice;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class BattleUtils {


    private Handler handler;

    public BattleUtils(Handler handler) {
        this.handler = handler;
    }


    public ArrayList<Monster> makeMonsters(int numberOfMonsters){
        int y = 10, x = handler.getWidth() / numberOfMonsters;
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < numberOfMonsters; i++) {
            monsters.add(monsterFactory(x * i, y));
        }
        return monsters;
    }

    public Monster monsterFactory(int x, int y){
        Dice d100 = new Dice(100);
        Player player = this.handler.getWorld().getEntityManager().getPlayer();

        int width = 64, height = 64;

        int roll = d100.roll();
        int level = player.getStat(Stats.LEVEL);
        if (roll <= 25) {
            Goblin g = new Goblin(handler, x, y, width, height, level, "Gobo");
            System.out.println(g);
            return g;
        } else if (roll <= 50) {
            return new Wolf(handler, x, y, width, height, level, "Wolf");
        } else if (roll <= 75) {
            return new Bear(handler, x, y, width, height, level, "Bear");
        } else {
            return new Hobgoblin(handler, x, y, width, height, level, "Hobgoblin");
        }
    }

    public static void damageCreature(Creature creature, int damage){
        creature.setStat(Stats.HP, creature.getStat(Stats.HP) - damage);
    }


}
