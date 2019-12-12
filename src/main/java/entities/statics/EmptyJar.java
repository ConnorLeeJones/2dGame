package entities.statics;

import main.Handler;

public class EmptyJar extends Jar {
    public EmptyJar(Handler handler, float x, float y) {
        super(handler, x, y);
    }

    @Override
    public void die(){}

}
