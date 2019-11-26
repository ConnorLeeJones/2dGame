package states;

import entities.creatures.Player;
import entities.statics.Tree;
import gfx.Assets;
import main.Game;
import main.Handler;
import tiles.Tile;
import worlds.World;

import java.awt.*;

public class GameState extends State {

    private World world;


    public GameState(Handler handler) {
        super(handler);
        world = new World(handler, "src/res/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
}
