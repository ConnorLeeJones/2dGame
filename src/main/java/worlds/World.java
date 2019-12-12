package worlds;

import entities.Entity;
import entities.EntityManager;
import entities.creatures.Player;
import entities.statics.*;
import gfx.Assets;
import gfx.Text;
import items.ItemManager;
import main.Handler;
import stats.Stats;
import tiles.Tile;
import utils.Utils;

import java.awt.*;
import java.util.HashMap;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;


    //entities
    private EntityManager entityManager;
    //items
    private ItemManager itemManager;

    //enemies
    private HashMap<Integer, Integer> enemyLocations;


    public World(Handler handler, String path){
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 100, 100));
        itemManager = new ItemManager(handler);
        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Tree(handler, 400, 500));

        entityManager.addEntity(new Rock(handler, 200, 350));
        entityManager.addEntity(new Rock(handler, 500, 150));

        entityManager.addEntity(new EmptyJar(handler, 600, 600));
        entityManager.addEntity(new HealthJar(handler, 700, 700));
        entityManager.addEntity(new HealthJar(handler, 800, 800));
        entityManager.addEntity(new MagicJar(handler, 900, 900));
        entityManager.addEntity(new MagicJar(handler, 1000, 1000));
        entityManager.addEntity(new EmptyJar(handler, 1100, 1100));


        entityManager.addEntity(new HealthJar(handler, 800, 700));
        entityManager.addEntity(new MagicJar(handler, 900, 700));

        entityManager.addEntity(new HealthJar(handler, 800, 600));
        entityManager.addEntity(new MagicJar(handler, 900, 600));



        loadWorld(path);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);

        placeEnemies();
    }

    public void placeEnemies(){
        enemyLocations = new HashMap<>();
        while(enemyLocations.size() < 20){
            int randX = (int) Math.floor(Math.random() * width);
            int randY = (int) Math.floor(Math.random() * height);
            System.out.println("XXX:" + entityManager.getPlayer().getTileX());
            System.out.println("XXX:" + entityManager.getPlayer().getTileY());

            if (!getTile(randX, randY).isSolid()){
                if(randX == entityManager.getPlayer().getTileX() && randY == entityManager.getPlayer().getTileY()) {
                    continue;
                } else {
                    enemyLocations.put(randX, randY);
                }
            }
        }
        System.out.println(enemyLocations.toString());
    }


    public void tick(){
        itemManager.tick();
        entityManager.tick();
    }

    public void render(Graphics g){
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);


        for(int y = yStart; y < yEnd; y++){
            for(int x = xStart; x < xEnd; x++){
                getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        Text.drawString(g, entityManager.getPlayer().getStat(Stats.HP) + "/" +
                        entityManager.getPlayer().getStat(Stats.MAX_HP), 10,
                30, false, Color.WHITE, Assets.font28);



        //items
        itemManager.render(g);
        //entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y){
        if (x < 0 || y < 0 || x >= width || y >= height){
            return Tile.grassTile;
        }

        Tile t = Tile.tiles[tiles[x][y]];
        if (t == null)
            return Tile.dirtTile;
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);


        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public HashMap<Integer, Integer> getEnemyLocations() {
        return enemyLocations;
    }
}
