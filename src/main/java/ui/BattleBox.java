package ui;

import battle.Battle;
import entities.creatures.Player;
import entities.creatures.monsters.Monster;
import gfx.Assets;
import gfx.Text;
import inventory.Inventory;
import items.Item;
import main.Handler;
import spells.MultiTarget;
import spells.SingleTarget;
import spells.Spell;
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
    private int selectedChoice = 0 , xp = 0;
    private Battle battle;
    private String menu;
    private Player player;
    private int currentMonster = 0;
    private boolean playerTurn = true;
    private ArrayList<String> battleText;
    private Spell selectedSpell;


    public BattleBox(float x, float y, int width, int height, BufferedImage[] images, Handler handler, Battle battle) {
        super(x, y, width, height);
        this.images = images;
        this.handler = handler;
        choices = setUpChoices();
        this.battle = battle;
        menu = "choices";
        player = handler.getWorld().getEntityManager().getPlayer();
        battleText = new ArrayList<>();
        for (Monster monster : battle.getMonsters()){
            xp += monster.getStat(Stats.XP);
        }
    }

    @Override
    public void tick() {
        if(!battle.checkConditions()) {
            if (playerTurn)
                playerTurn();
            else
                monsterTurn();
        } else {
            finishBattle();
        }
    }

    public void finishBattle(){
        if(player.getStat(Stats.HP) <= 0){
            player.setStat(Stats.HP, 100);
            handler.getWorld().placeEnemies();
            System.out.println("You lose");
            State.setState(handler.getGame().gameState);
        } else {
            System.out.println(xp + " xp gained.");
            if (player.getStat(Stats.XP) + xp > player.getStat(Stats.MAX_XP)){
                player.setStat(Stats.XP, player.getStat(Stats.XP) + xp - player.getStat(Stats.MAX_XP));
                player.setStat(Stats.MAX_XP, player.getStat(Stats.MAX_XP) * 2);
                System.out.println("Level up");
            } else {
                player.setStat(Stats.XP, player.getStat(Stats.XP) + xp);
            }
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
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_Q)) {
            choices = setUpChoices();
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
                case "Spell":
                    spell();
                    break;
                case "Item":
                    choices = setUpItems();
                default:
                    break;
            }
        } else if (menu.equalsIgnoreCase("monsters")) {
            //battle.dealDamage(player, battle.getMonsters().get(selectedChoice));
            battleText.add(player.getName() + " dealt " + battle.dealDamage(player, battle.getMonsters().get(selectedChoice))
            + " to " +battle.getMonsters().get(selectedChoice).getName() + ".");
            playerTurn = false;
            choices = setUpChoices();
        } else if (menu.equalsIgnoreCase("spells")){
            selectedSpell = player.getSpellBook().get(selectedChoice);
            if (selectedSpell.getCost() <= player.getStat(Stats.MP)) {
                if(selectedSpell instanceof SingleTarget) {
                    choices = setUpMonsterChoices();
                    menu = "monster spells";
                } else {
                    menu = "monster spells";
                    selectedChoice = 0;
                    getChoice();
                }

            } else {
                battleText.add("Not enough MP");
                //choices = setUpChoices();
            }
        } else if (menu.equalsIgnoreCase("monster spells")) {
            //selectedChoice = 0;
            choices = setUpMonsterChoices();
            //selectedSpell.c
            if(selectedSpell instanceof SingleTarget){
                battleText.add(player.getName() + " dealt " +
                        ((SingleTarget) selectedSpell).cast(battle.getMonsters().get(selectedChoice))
                + " to " + battle.getMonsters().get(selectedChoice).getName() + ".");
            } else if (selectedSpell instanceof MultiTarget){
                battleText.add(player.getName() + " dealt " +
                        ((MultiTarget) selectedSpell).cast(battle.getMonsters())
                        + " total damage.");
            }
            playerTurn = false;
            choices = setUpChoices();
        } else if (menu.equalsIgnoreCase("items")) {
            if (selectedChoice == 0){
                if (player.getInventory().getItemCount(Item.healthPotion) > 0) {
                    player.getInventory().removeItem(Item.healthPotion);
                    player.setStat(Stats.HP, player.getStat(Stats.MAX_HP));
                    playerTurn = false;
                    choices = setUpChoices();
                } else {
                    battleText.add("Not enough potions.");
                }
            } else if (selectedChoice == 1) {
                if (player.getInventory().getItemCount(Item.magicPotion) > 0) {
                    player.getInventory().removeItem(Item.magicPotion);
                    player.setStat(Stats.MP, player.getStat(Stats.MAX_MP));
                    playerTurn = false;
                    choices = setUpChoices();
                } else {
                    battleText.add("Not enough potions.");
                }
            }
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

    public ArrayList<String> setUpItems(){
        selectedChoice = 0;
        menu = "items";
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Health Potion: (" + player.getInventory().getItemCount(Item.healthPotion) + ")");
        choices.add("Magic Potion: (" + player.getInventory().getItemCount(Item.magicPotion) + ")");
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

    public ArrayList<String> setUpSpellChoices(){
        selectedChoice = 0;
        menu = "spells";
        ArrayList<String> choices = new ArrayList<>();
        for (Spell spell : player.getSpellBook()){
            choices.add(spell.getClass().getSimpleName());
        }
        return choices;
    }



    protected void attack(){
        choices = setUpMonsterChoices();
    }

    protected void spell(){
        choices = setUpSpellChoices();
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
