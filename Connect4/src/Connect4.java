import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * @author Claire Wang
 * @version 5/20/21
 * 
 * Creates a connect 4 game. First asks if the user wants a two player game
 * or a computer game, then creates the board accordingly. 
 *
 */
public class Connect4 extends JFrame implements ActionListener{
    private JButton humanButton;
    private JButton compButton;
    
    /**
     * Creates the buttons and will open windows accordingly
     */
    public Connect4() {
        JLabel gameMode = new JLabel("Select your game mode: ");       
        gameMode.setBounds(300, 100, 200, 20);
        
                
        humanButton = new JButton("2 player game");
        humanButton.setBounds(300, 200, 150, 25);
        humanButton.addActionListener(this);
        
        compButton = new JButton("Computer Game");
        compButton.setBounds(50, 200, 150, 25);
        compButton.addActionListener(this);  
        
                
        setTitle("Start");                 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(500, 300);
        
        add(humanButton);
        add(compButton);        
        add(gameMode);
    }
        
    /**
     * If the 2 player game button is clicked, creates a HumanGameWindow. 
     * If the computer game button is clicked, creates a ComputerGameWindow. 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == humanButton) {
            HumanGameWindow window = new HumanGameWindow();
            window.setTitle("Connect 4 Board");
            window.setLayout(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(900, 600);        
            window.setVisible(true);                    
        }
        else if (e.getSource() == compButton) {
            ComputerGameWindow window = new ComputerGameWindow();
            window.setTitle("Connect 4 Board");
            window.setLayout(null);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setSize(900, 600);        
            window.setVisible(true);
        }
        
    }
    
    /**
     * Creates a new connect 4 object which starts the game
     * @param args nothing
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Connect4 connect4 = new Connect4();   
        connect4.setVisible(true);
       
    }



    

}
