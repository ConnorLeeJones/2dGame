package battle;

import entities.creatures.Creature;
import entities.creatures.Player;
import entities.creatures.monsters.Monster;
import main.Game;
import main.Handler;
import states.State;
import stats.Stats;
import utils.Dice;

import java.io.Serializable;
import java.util.ArrayList;

public class Battle {

    private Handler handler;
    private Player player;
    private ArrayList<Monster> monsters;
    private BattleUtils battleUtils;

    public Battle(Handler handler, Player player, int numberOfMonsters) {
        battleUtils = new BattleUtils(handler);
        this.handler = handler;
        this.player = player;
        this.monsters = battleUtils.makeMonsters(numberOfMonsters);
    }


    public int dealDamage(Creature attacker, Creature attacked){
        Dice d10 = new Dice(10);
        int damage = d10.roll() + attacker.getStat(Stats.STRENGTH);
        int newHP = attacked.getStat(Stats.HP) - damage;
        attacked.setStat(Stats.HP, newHP);
        return damage;
    }

    public boolean checkConditions(){
        if(player.getStat(Stats.HP) <= 0){
//            player.setStat(Stats.HP, 100);
//            handler.getWorld().placeEnemies();
//            System.out.println("You lose");
//            State.setState(handler.getGame().gameState);
            return true;
        } else if (getTotalMonsterHp() <= 0){
            System.out.println(getTotalMonsterHp());
            handler.getWorld().placeEnemies();
            State.setState(handler.getGame().gameState);
            System.out.println("You win");
            return true;
        }
        return false;
    }

    private int getTotalMonsterHp(){
        int totalHp = 0;
        for(Monster monster : monsters){
            totalHp += monster.getStat(Stats.HP);
        }
        return totalHp;
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }

    public BattleUtils getBattleUtils() {
        return battleUtils;
    }

    public void setBattleUtils(BattleUtils battleUtils) {
        this.battleUtils = battleUtils;
    }
}
