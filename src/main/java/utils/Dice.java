package utils;

public class Dice {

    private int sides;

    public Dice (){
        this.sides = 6;
    }

    public Dice (int sides){
        this.sides = sides;
    }

    public int roll(){
        return (int) (Math.random() * sides) + 1;
    }


    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }
}
