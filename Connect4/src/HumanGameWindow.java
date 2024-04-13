import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Claire Wang
 * @version 5/16/21
 * 
 * Creates a window with a board for the connect 4 class, 2- player mode.  Has buttons 
 * for each column and inputs tokens accordingly. The first player (player 1) 
 * will have red tokens, and player 2 will have black tokens. The players can go 
 * back and forward. 
 * 
 * The game ends when one person connects 4 or if the board is full and nobody has won. 
 *
 */
public class HumanGameWindow extends JFrame implements ActionListener {
    private HumanGame game;
    protected JButton button0;
    protected JButton button1;
    protected JButton button2;
    protected JButton button3;
    protected JButton button4;
    protected JButton button5;
    protected JButton button6;
    
    protected JButton forward;
    protected JButton back;
    protected Board board;
    protected boolean gameOver;
    
    /**
     * Creates a human game window by initializing all buttons, giving them the
     * right coordinates, and adding action listeners. 
     * 
     * Also initializes the human game. 
     */
    public HumanGameWindow() {
        gameOver = false;
        // column buttons
        button0 = new JButton();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();       
        
        button0.setBounds(120, 10, 30, 30);
        button1.setBounds(190, 10, 30, 30);
        button2.setBounds(260, 10, 30, 30);
        button3.setBounds(330, 10, 30, 30);
        button4.setBounds(400, 10, 30, 30);
        button5.setBounds(470, 10, 30, 30);
        button6.setBounds(540, 10, 30, 30);
        
        button0.addActionListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        
        
        
        //back and forward buttons
        forward = new JButton("forward");
        back = new JButton("back");        
        back.setBounds(600, 300, 100, 30);
        forward.setBounds(700, 300, 100, 30);
        
        forward.addActionListener(this);
        back.addActionListener(this);
        
        
        //creates the board
        board = new Board();
        board.setBackground(Color.yellow);
        board.setBounds(100, 75, 490, 420);
        
        //adds buttons and panels
        add(board);
        add(button0);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        
        add(forward);
        add(back);
        
        game = new HumanGame();
    }
    
    
    /**
     * Inserts moves when the player presses one of the top buttons or the forward button 
     * and takes back moves whenever the player presses the back button. Checks every
     * move to see if it is a win. 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        int column = -1;
        if (gameOver) {
            return;
        }
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
            }
            else if (game.getTurns() == 42) {
                endGame(p, false);
            }
            return;
        }
        
        
        if (e.getSource() == back) {
            Move takenBack = game.goBack();
            if (takenBack == null) {
                return;
            }
            board.removeToken(takenBack.getRow(), takenBack.getColumn());            
        }
        else if (e.getSource() == forward) {
            Move forwardMove = game.goForward();
            if (forwardMove == null) {
                return;
            }
            board.insertToken(forwardMove.getPlayer(), forwardMove.getRow(), forwardMove.getColumn());
        }     
        
    }
    
    /**
     * 
     * @param p is the player number who is going to win (if it is a winning move). This parameter
     * is ignored if the boolean win is false. 
     * 
     * @param win tells if it is a win. If it is false, that means it is a draw. 
     * 
     * Makes an announcement on the board telling who won (if someone won) or if it is a draw.
     * Disables the buttons and ends the game. 
     */
    protected void endGame(int p, boolean win) {
        if (win) {
            JLabel winNotif = new JLabel("Player " + p + " has won! Game over.");
            winNotif.setBounds(650, 200, 300, 30);
            winNotif.setVisible(true);
            add(winNotif);
        }
        else {
            JLabel finish = new JLabel("Game over. Nobody wins");
            finish.setBounds(650, 200, 200, 30);
            finish.setVisible(true);
            add(finish);
        }
        repaint();
        gameOver = true;
    }
    
    
    

}
