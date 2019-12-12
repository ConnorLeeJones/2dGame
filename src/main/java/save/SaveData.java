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


    private float x, y;

    //public Inventory inventory;
    private ArrayList<Spell> spellBook;
    private String name;
    private HashMap<Stats, Integer> stats;

    public SaveData(Player player) {
        this.x = player.getX();
        this.y = player.getY();
        //this.spellBook = player.getSpellBook();
        this.name = player.getName();
        this.stats = player.getStats();
        //this.inventory = player.getInventory();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public ArrayList<Spell> getSpellBook() {
        return spellBook;
    }

    public void setSpellBook(ArrayList<Spell> spellBook) {
        this.spellBook = spellBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Stats, Integer> getStats() {
        return stats;
    }

    public void setStats(HashMap<Stats, Integer> stats) {
        this.stats = stats;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
