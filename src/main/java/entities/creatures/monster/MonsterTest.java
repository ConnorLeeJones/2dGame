package entities.creatures.monster;

import gfx.Assets;
import main.Handler;
import spells.Element;
import stats.StatCreator;
import stats.Stats;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class MonsterTest {

        //handler

        public static MonsterTest[] monsters = new MonsterTest[256];
        public static MonsterTest goblin = new MonsterTest(Assets.player, "Goblin", 0, StatCreator.newMonsterStats(1), Element.WATER, Element.FIRE);



        protected HashMap<Stats, Integer> stats;


        //class

        public static final int ITEMWIDTH = 32, ITEMHEIGHT = 32;

        protected Handler handler;
        protected BufferedImage texture;
        protected String name;
        protected final int id;

        protected int x, y;
        protected Element weakness, strength;


        public MonsterTest(BufferedImage texture, String name, int id, HashMap<Stats, Integer> stats, Element weakness, Element strength) {
            this.texture = texture;
            this.name = name;
            this.id = id;
            this.stats = stats;
            this.weakness = weakness;
            this.strength = strength;


            monsters[id] = this;
        }
}
