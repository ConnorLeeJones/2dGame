package entities.creatures;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;
import main.Game;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {


    //animations
    private Animation animDown, animUp, animLeft, animRight;
    private Animation attackDown, attackUp, attackLeft, attackRight;
    private String attackString = null;
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;

    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);


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
        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
            attackString = "up";
        } else if(handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize + cb.height;
            attackString = "down";
        } else if(handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            attackString = "left";
        } else if(handler.getKeyManager().aRight){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
            attackString = "right";
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
        if (attackString != null){
            switch(attackString){
                case "up":
                    attackString = null;
                    return attackUp.getCurrentFrame();
                case "down":
                    attackString = null;
                    return attackDown.getCurrentFrame();
                case "left":
                    attackString = null;
                    return attackLeft.getCurrentFrame();
                default:
                case "right":
                    attackString = null;
                    return attackRight.getCurrentFrame();
            }
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
}
