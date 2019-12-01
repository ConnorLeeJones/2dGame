package stats;

import utils.Dice;

import java.util.HashMap;

public class StatCreator {

    public StatCreator() {}

    public static HashMap<Stats, Integer> newPlayerStats(){
        HashMap<Stats, Integer> newStats = new HashMap<>();
        newStats.put(Stats.STRENGTH, 10);
        newStats.put(Stats.DEFENSE, 10);
        newStats.put(Stats.MANA, 10);
        newStats.put(Stats.SPEED, 10);
        newStats.put(Stats.ACCURACY, 10);

        newStats.put(Stats.HP, 100);
        newStats.put(Stats.MP, 100);
        newStats.put(Stats.XP, 0);
        newStats.put(Stats.MAX_HP, 100);
        newStats.put(Stats.MAX_MP, 100);
        newStats.put(Stats.MAX_XP, 100);
        newStats.put(Stats.LEVEL, 1);
        return newStats;
    }


    public static HashMap<Stats, Integer> newMonsterStats(Integer level){
        Dice d10 = new Dice(10);
        Dice dice = new Dice(10 * level);
        HashMap<Stats, Integer> newStats = new HashMap<>();
        newStats.put(Stats.STRENGTH, d10.roll() * level / 2);
        newStats.put(Stats.DEFENSE, d10.roll() * level / 2);
        newStats.put(Stats.MANA, d10.roll() + d10.roll() * level);
        newStats.put(Stats.SPEED, dice.roll() + dice.roll());
        newStats.put(Stats.ACCURACY, d10.roll() * level / 2);

        newStats.put(Stats.HP, 100 * level / 2);
        newStats.put(Stats.MP, 100 * level / 2);
        newStats.put(Stats.XP, d10.roll() * level / 2);
        newStats.put(Stats.MAX_HP, 100 * level / 2);
        newStats.put(Stats.MAX_MP, 100 * level / 2);
        newStats.put(Stats.LEVEL, level);
        return newStats;
    }



}
