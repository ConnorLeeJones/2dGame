package spells;

import battle.Battle;
import battle.BattleUtils;
import entities.creatures.Player;
import entities.creatures.monsters.Monster;
import stats.Stats;
import utils.Dice;

public class Fireball extends Spell implements SingleTarget {

    public Fireball(Player player) {
        super(Element.FIRE, 10, player);
    }


    @Override
    public int cast(Monster monster) {

        if(getPlayer().getStat(Stats.MP) >= getCost()) {
            Dice dice = new Dice(20);
            int damage = dice.roll() + 10 + getPlayer().getStat(Stats.MANA);
            getPlayer().setStat(Stats.MP, getPlayer().getStat(Stats.MP) - getCost());
            BattleUtils.damageCreature(monster, damage);
            return damage;
        } else {
            System.out.println("Not enough MP");
        }
        return -1;
    }
}
