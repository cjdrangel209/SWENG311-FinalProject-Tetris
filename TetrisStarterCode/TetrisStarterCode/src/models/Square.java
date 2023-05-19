package models;

/**
 * Square.java
 * Creates a tetronimo in the shape of a square
 *
 * @author Cory Drangel
 * @version 1.0 December 16, 2020
 */


public class Square extends Tetronimo{
    //constructor for the tetronimo
    public Square(){
        super.r1.setLocation(0,0);
        super.r2.setLocation(Tetronimo.SIZE,0);
        super.r3.setLocation(0, Tetronimo.SIZE);
        super.r4.setLocation(Tetronimo.SIZE,Tetronimo.SIZE);

        super.add(r1);
        super.add(r2);
        super.add(r3);
        super.add(r4);
    }

    /**
     *
     * @return the height of the tetronimo
     */
    @Override
    public int getHeight(){
        return Tetronimo.SIZE * 2;
    }

    /**
     *
     * @return the width of the tetronimo
     */
    @Override
    public int getWidth(){
        return Tetronimo.SIZE * 2;
    }
}
