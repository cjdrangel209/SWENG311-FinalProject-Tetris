package views;

import controllers.TetrisController;
import models.Tetronimo;
import wheelsunh.users.*;
import wheelsunh.users.Frame;
import wheelsunh.users.Rectangle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * TetrisBoard.java:
 * Class to model the tetris board
 *
 * @author Professor Rossi, Cory Drangel
 * @version 1.0 July 24, 2020, 2.0 December 16, 2020
 *
 * @see java.awt.Color
 * @see java.awt.event.KeyListener
 * @see java.awt.event.KeyEvent
 */
public class TetrisBoard implements KeyListener
{
    /**
     * Constant to represent the width of the board
     */
    public static final int WIDTH = 10;

    /**
     * Constant to represnet the height of the board
     */
    public static final int HEIGHT = 24;

    private final TetrisController CONTROLLER;
    private Tetronimo tetronimo;
    private Tetronimo nextTetronimoPiece;
    private Rectangle[][] playingField;
    public TextBox scoreField;
    private Rectangle nextTetronimo;
    private TextBox scoreLabel;
    private TextBox nextTetronimoLabel;
    private TextBox gameOver;


    /**
     * Constructor to initialize the board
     *
     * @param frame The wheelsunh frame (so we can add this class as a key listener for the frame)
     */
    public TetrisBoard( Frame frame )
    {
        frame.addKeyListener( this );
        this.CONTROLLER = new TetrisController( this );

        this.buildBoard();

        this.run();
    }

    /**
     * Builds the playing field for tetris
     */
    private void buildBoard()
    {
        this.playingField = new Rectangle[ WIDTH ][ HEIGHT ];

        for ( int i = 0; i < TetrisBoard.WIDTH; i++ )
        {
            for ( int j = 0; j < TetrisBoard.HEIGHT; j++ )
            {
                this.playingField[ i ][ j ] = new Rectangle();
                this.playingField[ i ][ j ].setLocation( i * 20 + 40, j * 20 );
                this.playingField[ i ][ j ].setSize( Tetronimo.SIZE, Tetronimo.SIZE );
                this.playingField[ i ][ j ].setColor( Color.WHITE );
                this.playingField[ i ][ j ].setFrameColor( Color.BLACK );
            }
        }

        this.scoreLabel = new TextBox();
        this.scoreLabel.setLocation(TetrisBoard.WIDTH * 20 + 60, 0);
        this.scoreLabel.setSize(100, 50);
        this.scoreLabel.setColor(Color.WHITE);
        this.scoreLabel.setFrameColor(Color.WHITE);
        this.scoreLabel.setText("SCORE");

        //displays box that contains the score of the user
        this.scoreField = new TextBox();
        this.scoreField.setLocation(TetrisBoard.WIDTH * 20 + 60,50 );
        this.scoreField.setSize(100, 100);
        this.scoreField.setColor(Color.WHITE);
        this.scoreField.setFrameColor(Color.BLACK);

        this.nextTetronimoLabel = new TextBox();
        this.nextTetronimoLabel.setLocation(TetrisBoard.WIDTH * 20 + 60, 175);
        this.nextTetronimoLabel.setSize(100, 50);
        this.nextTetronimoLabel.setColor(Color.WHITE);
        this.nextTetronimoLabel.setFrameColor(Color.WHITE);
        this.nextTetronimoLabel.setText("NEXT PIECE");

        //displays box of user's next piece
        this.nextTetronimo = new TextBox();
        this.nextTetronimo.setLocation(TetrisBoard.WIDTH * 20 + 60,225);
        this.nextTetronimo.setSize(100, 200);
        this.nextTetronimo.setColor(Color.WHITE);
        this.nextTetronimo.setFrameColor(Color.BLACK);

        //displays text box when the game is over
        this.gameOver = new TextBox();
        this.gameOver.setLocation(TetrisBoard.WIDTH * 20 + 60, 430);
        this.gameOver.setSize(100, 50);
        this.gameOver.setColor(Color.WHITE);
        this.gameOver.setFrameColor(Color.WHITE);
    }

    /**
     * Starts gameplay and is responsible for keeping the game going (INCOMPLETE)
     */
    public void run()
    {
        //if statement to determine if it is the first piece of the game or not
        if (this.nextTetronimoPiece == null){
            this.tetronimo = this.CONTROLLER.getNextTetromino();
        }
        else{
            //assigning the next tetronimo to the current one and displaying it on the game board
            this.tetronimo = this.nextTetronimoPiece;
            this.tetronimo.setLocation(TetrisBoard.WIDTH * 10,0);
        }

        //sets and displays the next piece of the game
        this.nextTetronimoPiece = this.CONTROLLER.getNextTetromino();
        this.nextTetronimoPiece.setLocation(TetrisBoard.WIDTH * 20 + 70,240);


        while( this.CONTROLLER.tetronimoLanded( this.tetronimo ) )
        {
            this.tetronimo.setLocation( this.tetronimo.getXLocation(), this.tetronimo.getYLocation() + Tetronimo.SIZE );
            Utilities.sleep( 500 );
        }

        /*
         * This next line is a placeholder for now, you need to change this code so when a piece lands
         * the right squares on the board are painted the color of the tetronimo and the teetronimo itself gets hidden
         */
        int[][] rLocation = this.tetronimo.getRLocation();

        //for loop that would've gotten the location of each individual block and painted the corresponding
        //area on the playing field BLUE (INCOMPLETE)

/*
        for (int i = 0; i < 4; i++){
            this.playingField[(rLocation[i][0] - 40)/20 ][rLocation[i][1]/20].setColor(Color.BLUE);
        }

 */
        //if statement to determine if the game is over
        if (this.CONTROLLER.gameOver(tetronimo, nextTetronimoPiece)){
            this.gameOver.setText("GAME OVER");
        }
        else{
            this.tetronimo = null;
            run();
        }
    }

    /**
     * Getter method for the array representing the playing field, not used yet but will be needed by the controller later
     *
     * @return The playing field
     */
    public Rectangle[][] getPlayingField()
    {
        return playingField;
    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
        //not in use
    }

    /**
     * Handles the key events by the user (INCOMPLETE)
     *
     * @param e The key event
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        int key = e.getKeyCode();

        if( this.tetronimo == null )
        {
            return;
        }

        switch( key )
        {
            case 38:
                this.tetronimo.rotate();
                break;
            case 37:
                if( this.tetronimo.getXLocation() - Tetronimo.SIZE >= 40 )
                {
                    this.tetronimo.shiftLeft();
                }
                break;
            case 39:
                if( (this.tetronimo.getXLocation() + this.tetronimo.getWidth()) <
                        ((TetrisBoard.WIDTH * Tetronimo.SIZE) + 40))
                {
                    this.tetronimo.shiftRight();
                }
                break;
        }

    }

    /**
     * This method is not used in this program
     *
     * @param e The key event
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
        //not in use
    }
}