package spells;

import entities.creatures.Player;
import entities.creatures.monsters.Monster;

import java.util.ArrayList;

public interface MultiTarget {

    public int cast(ArrayList<Monster> monster, Player player);
}
