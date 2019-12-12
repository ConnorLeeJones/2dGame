package spells;

import entities.creatures.Player;
import entities.creatures.monsters.Monster;

public interface SingleTarget {

    public int cast(Monster monster, Player player);
}
