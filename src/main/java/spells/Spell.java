package spells;

public abstract class Spell {

    private Element element;
    private int cost;

    public Spell(){}

    public Spell(Element element, int cost){
        this.element = element;
        this.cost = cost;
    }



}
