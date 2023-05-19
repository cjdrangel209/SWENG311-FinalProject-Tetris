package models;

import java.awt.*;

/**
 * LeftL.java
 * Creates a tetronimo in the shape of a left L
 *
 * @author Cory Drangel
 * @version 1.0 December 16, 2020
 */

public class LeftL extends Tetronimo{

    //constructor for setting the initial position of the tetronimo
    public LeftL(){
        super.r1.setLocation(0,0);
        super.r2.setLocation(0,Tetronimo.SIZE);
        super.r3.setLocation(0,Tetronimo.SIZE * 2);
        super.r4.setLocation(Tetronimo.SIZE * -1, Tetronimo.SIZE * 2);

        super.add(r1);
        super.add(r2);
        super.add(r3);
        super.add(r4);
    }

    /**
     * rotates the tetronimo
     */
    @Override
    public void rotate(){
        super.rotate();

        Point currLoc = super.getLocation();
        super.setLocation(0,0);

        if(super.curRotation % 4 == 0){
            super.r1.setLocation(0,0);
            super.r2.setLocation(Tetronimo.SIZE, 0);
            super.r3.setLocation(Tetronimo.SIZE * 2, 0);
            super.r4.setLocation(Tetronimo.SIZE * 2, Tetronimo.SIZE);
        }
        else if(super.curRotation % 4 == 1){
            super.r1.setLocation(Tetronimo.SIZE,0);
            super.r2.setLocation(Tetronimo.SIZE,Tetronimo.SIZE);
            super.r3.setLocation(Tetronimo.SIZE, Tetronimo.SIZE * 2);
            super.r4.setLocation(0, Tetronimo.SIZE * 2);
        }
        else if(super.curRotation % 4 == 2){
            super.r1.setLocation(0,0);
            super.r2.setLocation(0, Tetronimo.SIZE);
            super.r3.setLocation(Tetronimo.SIZE,Tetronimo.SIZE);
            super.r4.setLocation(Tetronimo.SIZE * 2, Tetronimo.SIZE);
        }
        else{
            super.r1.setLocation(0,0);
            super.r2.setLocation(0, Tetronimo.SIZE);
            super.r3.setLocation(0, Tetronimo.SIZE * 2);
            super.r4.setLocation(Tetronimo.SIZE, 0);
        }

        super.setLocation(currLoc);
    }

    /**
     * Gets the height of the tetronimo
     * @return the height of the tetronimo
     */
    @Override
    public int getHeight(){
        if(this.curRotation % 4 == 0 || this.curRotation % 4 == 2){
            return  Tetronimo.SIZE * 2;
        }
        else{
            return Tetronimo.SIZE * 3;
        }
    }

    /**
     * Gets the width of tetronimo
     * @return width of tetronimo
     */
    @Override
    public int getWidth(){
        if(this.curRotation % 4 == 0 || this.curRotation % 4 == 2){
            return Tetronimo.SIZE * 3;
        }
        else{
            return Tetronimo.SIZE * 2;
        }
    }
}
