
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

/**
 * 
 * @author Claire Wang
 * @version 5/16/21
 * 
 * Creates a window for a computer game. The computer will always go first and is the
 * red token. ComputerGameWindow has the same capabilities as the HumanGameWindow but
 * every time a player inputs a move, the computer has to input theirs as well. When 
 * moves are taken back, 2 are taken back. When the player presses the forward button, 2
 * moves are put forward. 
 * 
 * The game ends the same way a human game ends. 
 *
 */
public class ComputerGameWindow extends HumanGameWindow implements ActionListener {
    private ComputerGame game;
    
    /**
     * Creates a comptuer game window by calling the superclass' constructor. Also
     * inputs the computer move first. 
     */
    public ComputerGameWindow() {
        super();
        game = new ComputerGame();
        Move first = game.inputComputerMove();
        board.repaint();
        board.insertToken(1, first.getRow(), first.getColumn());  
    }
    
    
    
    /**
     * Enters the moves when the user presses a column button. After a move is entered, 
     * The computer player will also decide its move and enter it in. The computer
     * checks every move to see if it is a win before it moves on. 
     * 
     * The private method putHumanAndComputerMoves() is called to make code more 
     * organized. 
     * 
     * Back/forward buttons remove and add 2 tokens at a time. 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }
        if (game.isPlayer1Turn()) {
            return;
        }
        int column = -1;
        if (e.getSource() == button0) {
            column = 0;
        }
        else if (e.getSource() == button1) {
            column = 1;
        }
        else if (e.getSource() == button2) {
            column = 2;
        }
        else if (e.getSource() == button3) {
            column = 3;
        }
        else if (e.getSource() == button4) {
            column = 4;
        }
        else if (e.getSource() == button5) {
            column = 5;
        }
        else if (e.getSource() == button6) {
            column = 6;
        }
        
        if (column >= 0) {
            putHumanAndComputerMoves(column);
            return;
        }
        
        if (e.getSource() == forward) {
            Move[] inserts = game.goForward2();
            if (inserts == null) {
                return;
            }            
            Move move1 = inserts[0];
            Move move2 = inserts[1];
            board.insertToken(move1.getPlayer(), move1.getRow(), move1.getColumn());
            board.insertToken(move2.getPlayer(), move2.getRow(), move2.getColumn());

            return;
        }
        if (e.getSource() == back) {
            Move[] removes = game.goBack2();
            if (removes == null) {
                return;
            }
            Move move1 = removes[0];
            Move move2 = removes[1];
            board.removeToken(move1.getRow(), move1.getColumn());
            board.removeToken(move2.getRow(), move2.getColumn());
            
        }
    }
    
    /**
     * 
     * @param column is the column the player wants to put it in
     * 
     * Inserts the human move and also puts in the computer move. Checks if the game is over
     * (if someone wins or if the board is full). 
     */
    private void putHumanAndComputerMoves(int column) {
        Move current = game.inputMove(column);
        if (current == null) {
            return;
        }
        int row = current.getRow();
        int p = current.getPlayer();
        int col = current.getColumn();
        board.insertToken(p, row, col);
        
        if (game.isWin(p, row, col)) {
            endGame(p, true);
            return;
        }
        else if (game.getTurns() == 42) {
            endGame(p, false);
            return;
        }
        
        Move other = game.inputComputerMove();
        board.insertToken(other.getPlayer(), other.getRow(), other.getColumn());
        
        if (game.isWin(1, other.getRow(), other.getColumn())) {
            endGame(other.getPlayer(), true);
        }
        else if (game.getTurns() == 42) {
            endGame(p, false);
        }
    }
       

}
