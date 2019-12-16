package states;

import audio.MusicPlayer;
import main.Game;
import main.Handler;

import java.awt.*;
import java.io.Serializable;

public abstract class State {


    private static State currentState = null;
    protected String music;
    protected int musicIndex;



    public static void setState(State state) {
        State.currentState = state;
        //MusicPlayer.getINSTANCE().
        MusicPlayer.getINSTANCE().setCurrentSongIndex(state.musicIndex);
        System.out.println(MusicPlayer.getINSTANCE().getCurrentSongIndex());
//        MusicPlayer musicPlayer = new MusicPlayer(state.music);
//        musicPlayer.run();
    }

    public static State getState() {
        return currentState;
    }

    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }


    public abstract void tick();

    public abstract void render(Graphics g);




}
