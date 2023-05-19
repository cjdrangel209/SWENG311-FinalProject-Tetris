package models;

import java.awt.*;

/**
        * LeftZ.java
        * Creates a tetronimo in the shape of a left Z
        *
        * @author Cory Drangel
        * @version 1.0 December 16, 2020
        */

public class LeftZ extends Tetronimo{
   //constructor for the tetronimo
    public LeftZ(){
        super.r1.setLocation(0,0);
        super.r2.setLocation(Tetronimo.SIZE, 0);
        super.r3.setLocation(Tetronimo.SIZE, Tetronimo.SIZE);
        super.r4.setLocation(Tetronimo.SIZE * 2, Tetronimo.SIZE);

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

        Point curLoc = super.getLocation();
        super.setLocation(0,0);

        if(super.curRotation % 2 == 0){
            super.r1.setLocation(Tetronimo.SIZE,0);
            super.r2.setLocation(Tetronimo.SIZE, Tetronimo.SIZE);
            super.r3.setLocation(0, Tetronimo.SIZE);
            super.r4.setLocation(0, Tetronimo.SIZE * 2);
        }
        else{
            super.r1.setLocation(0,0);
            super.r2.setLocation(Tetronimo.SIZE, 0);
            super.r3.setLocation(Tetronimo.SIZE, Tetronimo.SIZE);
            super.r4.setLocation(Tetronimo.SIZE * 2, Tetronimo.SIZE);
        }

        super.setLocation(curLoc);
    }

    /**
     *
     * @return the height of the tetronimo
     */
    @Override
    public int getHeight(){
        if(this.curRotation % 2 == 0){
            return Tetronimo.SIZE * 3;
        }
        else{
            return Tetronimo.SIZE * 2;
        }
    }

    /**
     *
     * @return the width of the tetronimo
     */
    @Override
    public int getWidth(){
        if(this.curRotation % 2 == 0){
            return Tetronimo.SIZE * 2;
        }
        else{
            return Tetronimo.SIZE * 3;
        }
    }
}
