package battle;

import entities.creatures.Player;
import entities.creatures.monsters.Monster;
import main.Handler;

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
