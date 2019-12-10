package spells;

import battle.BattleUtils;
import entities.creatures.Player;
import entities.creatures.monsters.Monster;
import stats.Stats;
import utils.Dice;

import java.util.ArrayList;

public class Flamethrower extends Spell implements MultiTarget {


    public Flamethrower(Player player) {
        super(Element.FIRE, 20, player);
    }

    @Override
    public int cast(ArrayList<Monster> monsters) {
        if(canCast()) {
            int totalDamage = 0;
            Dice dice = new Dice(10);
            getPlayer().setStat(Stats.MP, getPlayer().getStat(Stats.MP) - getCost());
            for (Monster monster : monsters) {
                int damage = dice.roll() + 5 + getPlayer().getStat(Stats.MANA);
                BattleUtils.damageCreature(monster, damage);
                totalDamage += damage;
            }
            return totalDamage;
        } else {
            System.out.println("Not enough MP");
        }
        return -1;
    }
}
