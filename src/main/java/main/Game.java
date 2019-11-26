package main;

import display.Display;
import entities.creatures.Player;
import gfx.Assets;
import gfx.GameCamera;
import input.KeyManager;
import states.GameState;
import states.MenuState;
import states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;


    //states
    private State gameState;
    private State menuState;

    //input
    private KeyManager keyManager;

    //camera
    private GameCamera gameCamera;

    //handler
    private Handler handler;



    public Game(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
    }



    private void init(){
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        handler = new Handler(this);

        gameCamera = new GameCamera(handler,0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setState(gameState);
    }



    private void tick(){
        keyManager.tick();

        if(State.getState() != null){
            State.getState().tick();
        }
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //clear screen
        g.clearRect(0, 0, width, height);
        //draw



        if(State.getState() != null){
            State.getState().render(g);
        }


        //draw to screen
        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60, ticks = 0;
        double timePerTick = 1000000000 / fps, delta = 0;
        long now, lastTime = System.nanoTime(), timer = 0;

        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;

            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000){
                System.out.println("Ticks + frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }


    public KeyManager getKeyManager() {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }


    public synchronized void start(){
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
