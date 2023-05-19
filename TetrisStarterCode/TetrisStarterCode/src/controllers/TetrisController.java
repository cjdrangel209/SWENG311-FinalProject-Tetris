package controllers;

import models.*;
import views.TetrisBoard;
import wheelsunh.users.Rectangle;

import java.awt.*;

/**
 * TetrisController.java:
 * Class to hold all of the game logic for tetris
 *
 * @author Professor Rossi, Cory Drangel
 * @version 1.0 July 24, 2020, 2.0 December 16, 2020
 */
public class TetrisController
{
    private final TetrisBoard TETRIS_BOARD;
    public static Rectangle[][] Board_Array;

    /**
     * Constructor to take in a tetris board so the controller and the board can communciate
     *
     * @param tetrisBoard A tetris board instance
     */
    public TetrisController( TetrisBoard tetrisBoard )
    {
        this.TETRIS_BOARD = tetrisBoard;
        Board_Array = this.TETRIS_BOARD.getPlayingField();
    }

    /**
     * Randomly chooses the next tetronimo and returns it
     *
     * @return The next tetronimo to be played
     */
    public Tetronimo getNextTetromino()
    {
        Tetronimo tetronimo;
        int randomNum = (int)(Math.random() * 7);   //random number between 0 and 6 to represent each tetronimo

        //switch statement for displaying the corresponding tetronimo based on the random number
        switch (randomNum){
            case 0:
                tetronimo = new StraightLine();
                break;
            case 1:
                tetronimo = new Square();
                break;
            case 2:
                tetronimo = new TShape();
                break;
            case 3:
                tetronimo = new LeftL();
                break;
            case 4:
                tetronimo = new RightL();
                break;
            case 5:
                tetronimo = new LeftZ();
                break;
            default:
                tetronimo = new RightZ();
                break;
        }

        tetronimo.setLocation( 40 + (5 * Tetronimo.SIZE), 0 );

        return tetronimo;
    }

    /**
     * Method to determine if the tetronimo has landed (INCOMPLETE)
     *
     * @param tetronimo The tetronimo to evaluate
     * @return True if the tetronimo has landed (on the bottom of the board or another tetronimo), false if it has not
     */
    public boolean tetronimoLanded( Tetronimo tetronimo )
    {
        int nextY = tetronimo.getYLocation() + tetronimo.getHeight() + Tetronimo.SIZE;
        return nextY<=480;
    }

    /**
     * Method to determine if the game is over
     *
     * @param tetronimo
     * @param nextTetronimo
     * @return True if the top of the current tetronimo is at or above the height of the next tetronimo
     */

    public boolean gameOver(Tetronimo tetronimo, Tetronimo nextTetronimo)
    {
        int tetronimoLocation = tetronimo.getYLocation();
        int nextTetronimoHeight = nextTetronimo.getYLocation() + nextTetronimo.getHeight() + Tetronimo.SIZE;

        //if statement to determine if the current piece is at or above the next piece
        if(tetronimoLocation <= nextTetronimoHeight){
            return true;
        }
        else{
            return false;
        }
    }
}
