package main;

import audio.MusicPlayer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Game game = new Game("Game", 640, 480);

        //MusicPlayer musicPlayer = new MusicPlayer("GameMusic1");
        //musicPlayer.run();
        MusicPlayer.getINSTANCE().run();

        game.start();



//        pool.execute(game);
//        pool.execute(musicPlayer);
//        pool.shutdown();
        //game.start();
    }
}
