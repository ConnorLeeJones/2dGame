package save;

import entities.creatures.Player;
import inventory.Inventory;
import main.Game;
import spells.Spell;
import stats.Stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveData implements Serializable {

    private static final long serialVersionUID = 1L;


    public int tileX, tileY;

    //public Inventory inventory;
    public ArrayList<Spell> spellBook;
    public String name;
    public HashMap<Stats, Integer> stats;

    public SaveData(Player player) {
        this.tileX = player.getTileX();
        this.tileY = player.getTileY();
        //this.spellBook = player.getSpellBook();
        this.name = player.getName();
        this.stats = player.getStats();
        //this.inventory = player.getInventory();
    }
}
