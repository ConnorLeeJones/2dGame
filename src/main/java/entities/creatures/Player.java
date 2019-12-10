package entities.creatures;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;
import main.Handler;
import spells.Fireball;
import spells.Flamethrower;
import spells.Spell;
import states.BattleState;
import states.MenuState;
import states.State;
import stats.StatCreator;
import stats.Stats;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Creature {


    //animations
    private Animation animDown, animUp, animLeft, animRight;
    private Animation attackDown, attackUp, attackLeft, attackRight;
    private long lastAttackTimer, attackCooldown = 300, attackTimer = attackCooldown;

    private int tileX, tileY;

    private Inventory inventory;
    private ArrayList<Spell> spellBook = new ArrayList<>();

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, "Connor");

        //stats
        stats = StatCreator.newPlayerStats();

        //players bounding box, customize
        bounds.x = 16;
        bounds.y = 40;
        bounds.width = 24;
        bounds.height = 24;

        //animations
        animDown = new Animation(250, Assets.playerDown);
        animUp = new Animation(250, Assets.playerUp);
        animLeft = new Animation(250, Assets.playerLeft);
        animRight = new Animation(250, Assets.playerRight);
        attackDown = new Animation(250, Assets.playerAttackDown);
        attackUp = new Animation(250, Assets.playerAttackUp);
        attackLeft = new Animation(250, Assets.playerAttackLeft);
        attackRight = new Animation(250, Assets.playerAttackRight);

        inventory = new Inventory(handler);
        tileX = (int) Math.floor(x / 64) + 1;
        tileY = (int) Math.floor(y / 64) + 1;

        spellBook.add(new Fireball(this));
        spellBook.add(new Flamethrower(this));

    }

    public void tick() {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
        //attack
        checkAttacks();
        inventory.tick();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        if(inventory.isActive())
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize + cb.height;
        } else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }


//        if (ar.x == cb.x + 2){
//            if(ar.y > cb.y){
//                System.out.println("XXX down XXX");
//            } else {
//                System.out.println("XXX up XXX");
//            }
//        } else if (ar.y == cb.y + 2){
//            if(ar.x > cb.x){
//                System.out.println("XXX right XXX");
//            } else {
//                System.out.println("XXX left XXX");
//            }
//        }

        attackTimer = 0;

        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }
    }

    @Override
    public void die(){
        System.out.println("You lose");
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(inventory.isActive())
            return;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }


    @Override
    public void render(Graphics g) {
//        int tileX = (int) Math.floor(x / 64) + 1;
//        int tileY = (int) Math.floor(y / 64) + 1;
        tileX = (int) Math.floor(x / 64) + 1;
        tileY = (int) Math.floor(y / 64) + 1;

        if(handler.getWorld().getEnemyLocations().get(tileX) != null){
            if(handler.getWorld().getEnemyLocations().get(tileX) == tileY) {
                System.out.println("Enemy found");
                //handler.getGame().setState(new BattleState(handler, 5));
                handler.getMouseManager().setUiManager(null);
                State.setState(new BattleState(handler, 3));
            }
        }

        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);



        //show bounding box

//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }


    public void postRender(Graphics g){
        inventory.render(g);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP)){
            return attackUp.getCurrentFrame();
        } else if(handler.getKeyManager().aDown){
            return attackDown.getCurrentFrame();
        } else if(handler.getKeyManager().aLeft){
            return attackLeft.getCurrentFrame();
        } else if(handler.getKeyManager().aRight){
            return attackRight.getCurrentFrame();
        }

        if (xMove < 0){
            return  animLeft.getCurrentFrame();
        } else if (xMove > 0){
            return animRight.getCurrentFrame();
        } else if (yMove < 0){
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            return animDown.getCurrentFrame();
        } else {
            return Assets.player;
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public ArrayList<Spell> getSpellBook() {
        return spellBook;
    }

    public void setSpellBook(ArrayList<Spell> spellBook) {
        this.spellBook = spellBook;
    }
}
