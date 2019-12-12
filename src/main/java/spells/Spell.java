package spells;

import entities.creatures.Player;
import stats.Stats;

import java.io.Serializable;

public abstract class Spell implements Serializable {

    private Element element;
    private int cost;
    private Player player;

    public Spell(){}

    public Spell(Element element, int cost, Player player){
        this.element = element;
        this.cost = cost;
        this.player = player;
    }





    public boolean canCast() {
        return getPlayer().getStat(Stats.MP) >= getCost();
    }


    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
