package main;

import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import worlds.World;

import java.io.Serializable;

public class Handler implements Serializable {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera(){
        return game.getGameCamera();
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }


    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }


    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
