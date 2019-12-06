package spells;

import entities.creatures.monsters.Monster;

public class Fireball extends Spell implements SingleTarget {

    public Fireball() {
        super(Element.FIRE, 10);
    }


    @Override
    public void cast(Monster monster) {

    }
}
