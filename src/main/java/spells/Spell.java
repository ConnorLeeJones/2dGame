package spells;

import entities.creatures.Player;
import stats.Stats;

public abstract class Spell {

    private Element element;
    private int cost;

    public Spell(Element element, int cost){
        this.element = element;
        this.cost = cost;
    }

    public boolean canCast(Player player) {
        return player.getStat(Stats.MP) >= getCost();
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

}
