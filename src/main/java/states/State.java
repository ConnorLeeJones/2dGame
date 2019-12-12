package states;

import main.Game;
import main.Handler;

import java.awt.*;
import java.io.Serializable;

public abstract class State implements Serializable {


    private static State currentState = null;



    public static void setState(State state) {
        State.currentState = state;
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
