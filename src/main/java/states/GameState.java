package states;

import audio.MusicPlayer;
import main.Handler;
import worlds.World;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State {

    private World world;


    public GameState(Handler handler) {
        super(handler);
        //this.music = "overworld theme 1";
        this.musicIndex = 3;
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
