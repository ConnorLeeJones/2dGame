package spells;

import battle.BattleUtils;
import entities.creatures.Player;
import entities.creatures.monsters.Monster;
import stats.Stats;
import utils.Dice;

import java.util.ArrayList;

public class Flamethrower extends Spell implements MultiTarget {


    public Flamethrower() {
        super(Element.FIRE, 20);
    }

    @Override
    public int cast(ArrayList<Monster> monsters, Player player) {
        if(canCast(player)) {
            int totalDamage = 0;
            Dice dice = new Dice(10);
            player.setStat(Stats.MP, player.getStat(Stats.MP) - getCost());
            for (Monster monster : monsters) {
                int damage = dice.roll() + 5 + player.getStat(Stats.MANA);
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
