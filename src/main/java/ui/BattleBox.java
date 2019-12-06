package ui;

import battle.Battle;
import entities.creatures.Player;
import entities.creatures.monsters.Monster;
import gfx.Assets;
import gfx.Text;
import items.Item;
import main.Handler;
import states.State;
import stats.Stats;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BattleBox extends UIObject {


    private BufferedImage[] images;
    private Handler handler;
    private ArrayList<String> choices;
    private int selectedChoice = 0;
    private Battle battle;
    private String menu;
    private Player player;
    private int currentMonster = 0;
    private boolean playerTurn = true;
    private ArrayList<String> battleText;

    public BattleBox(float x, float y, int width, int height, BufferedImage[] images, Handler handler, Battle battle) {
        super(x, y, width, height);
        this.images = images;
        this.handler = handler;
        choices = setUpChoices();
        this.battle = battle;
        menu = "choices";
        player = handler.getWorld().getEntityManager().getPlayer();
        battleText = new ArrayList<>();
    }

    @Override
    public void tick() {
        if(!battle.checkConditions()) {
            if (playerTurn)
                playerTurn();
            else
                monsterTurn();
        }
    }

    public void monsterTurn(){
        System.out.println(battle.getMonsters().size());
        if(currentMonster >= battle.getMonsters().size()){
            currentMonster = 0;
        } else if (battle.getMonsters().size() == 0){
            battle.checkConditions();
        }
        Monster monster = battle.getMonsters().get(currentMonster);
        battleText.add(monster.getName() + " dealt " + battle.dealDamage(monster, player) + " to " + player.getName() + ".");
        currentMonster++;
        playerTurn = true;
    }

    public void playerTurn(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {
            if(selectedChoice != 0) {
                selectedChoice--;
            }
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {
            if(selectedChoice != choices.size() - 1) {
                selectedChoice++;
            }
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
            getChoice();
        }
    }

    public void getChoice() {
        if (menu.equalsIgnoreCase("choices")) {
            switch (choices.get(selectedChoice)) {
                case "Flee":
                    handler.getWorld().placeEnemies();
                    State.setState(handler.getGame().gameState);
                    break;
                case "Attack":
                    attack();
                    break;
                default:
                    break;
            }
        } else if (menu.equalsIgnoreCase("monsters")) {
            //battle.dealDamage(player, battle.getMonsters().get(selectedChoice));
            battleText.add(player.getName() + " dealt " + battle.dealDamage(player, battle.getMonsters().get(selectedChoice))
            + " to " +battle.getMonsters().get(selectedChoice).getName() + ".");
            playerTurn = false;
            choices = setUpChoices();
        }
    }





    public ArrayList<String> setUpChoices(){
        selectedChoice = 0;
        menu = "choices";
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Attack");
        choices.add("Item");
        choices.add("Spell");
        choices.add("Flee");
        return choices;
    }

    public ArrayList<String> setUpMonsterChoices(){
        selectedChoice = 0;
        menu = "monsters";
        ArrayList<String> choices = new ArrayList<>();
        for (Monster monster : battle.getMonsters()){
            choices.add(monster.getName());
        }
        return choices;
    }

    protected void attack(){
        choices = setUpMonsterChoices();
    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y - 50, width, 48);
        Text.drawString(g, player.getName() + ":   HP: " + player.getStat(Stats.HP) + "/" +
                        player.getStat(Stats.MAX_HP) + "   MP: " + player.getStat(Stats.MP) + "/" +
                        player.getStat(Stats.MAX_MP), (int) x + 10,
                (int) y - 20, false, Color.WHITE, Assets.font15);

        g.setColor(Color.BLACK);
        g.fillRect((int) x, (int) y, width, height);



        int len = choices.size();
        if(len == 0)
            return;

        int textX = (int) x + 10, textY = (int) y + 20;

        for (int i = selectedChoice; i < len; i++) {
            if (i == selectedChoice) {
                Text.drawString(g, choices.get(i) + " <", textX,
                        textY, false, Color.YELLOW, Assets.font15);
            } else if (textY > y + height) {
                break;
            } else {
                Text.drawString(g, choices.get(i), textX,
                        textY, false, Color.WHITE, Assets.font15);
            }
            textY += 20;
        }



        if (battleText.size() > 6){
            for (int i = battleText.size() - 6; i < battleText.size(); i++) {
                System.out.println(i - battleText.size() + 6);
                Text.drawString(g, battleText.get(i), (int) x + 300,
                        (int) y + 20 + ((i - battleText.size() + 6) * 20), false, Color.WHITE, Assets.font15);
            }
        } else {
            for (int i = 0; i < battleText.size(); i++) {
                Text.drawString(g, battleText.get(i), (int) x + 300,
                        (int) y + 20 + i * 20, false, Color.WHITE, Assets.font15);
            }
        }


    }

    @Override
    public void onClick() {

    }

}
