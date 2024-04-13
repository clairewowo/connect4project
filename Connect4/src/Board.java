import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * 
 * @author Claire Wang
 * @version 5/18/21
 * 
 * A board panel is the connect 4 board. The board inserts tokens and deletes
 * them. It also draws lines. 
 * 
 * Board has an array of JLabel that it keeps track of. When someone inserts a piece,
 * the array will be able to get the JPanel and change it's image to a token. When
 * someone takes back a move, the array will get the JPanel and set it to a blank
 * token. 
 *
 */
public class Board extends JPanel {
    private JLabel[][] blanks;

    /**
     * Creates a new board. 
     * 
     * Inserts blank JPanels for every entry in the array. Puts the JPanels for 
     * every block in the board. 
     */
    public Board() {
        blanks = new JLabel[6][7];   
        for (int r = 0; r <= 5; r++) {
            for (int c = 0; c <= 6; c++) {
                
                JLabel current = new JLabel();
                try {            
                    Image image = ImageIO.read(new File("Images/nothing.png")).getScaledInstance(65, 65, Image.SCALE_DEFAULT);
                    current.setIcon(new ImageIcon(image));            
                } 
                catch (Exception e) {
                }
                current.setVisible(true);
                current.setBounds(c * 70 + 5, r * 70 + 5, 60, 60);
                blanks[r][c] = current;
                add(current);
                repaint();
            }
        }
    }
    /**
     * Draws the lines for the board: each square is 70 by 70. There are 7 columns 
     * and 6 rows. 
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        for (int i = 1; i <= 6; i++) {
            g.drawLine(i * 70, 0, i * 70, 420);
        }
        
        for (int i = 1; i <= 5; i++) {
            g.drawLine(0, i * 70, 490, i * 70);
        }                        
    }
    
    
    /**
     * 
     * @param p is the player number. If it is 1, a red token is inserted, if it is 2,
     * a black token is inserted
     * @param r is the row it should go to
     * @param c is the column it should go to. 
     * 
     * Inserts a token by setting the JPanel at blanks[r][c] to have either a red or 
     * black token as its image. 
     */
    public void insertToken(int p, int r, int c) {       
        
        try {
            if (p == 1) {
                Image image = ImageIO.read(new File("Images/red.png")).getScaledInstance(65, 65, Image.SCALE_DEFAULT);
                blanks[r][c].setIcon(new ImageIcon(image));
            }
            else {
                Image image = ImageIO.read(new File("Images/black.png")).getScaledInstance(65, 65, Image.SCALE_DEFAULT);
                blanks[r][c].setIcon(new ImageIcon(image));
            }
        }
        catch (Exception e) {
        }
        repaint();
    }
    
    /**
     * 
     * @param r is the row number of the token they want to delete
     * @param c is the column number of the token they want to delete. 
     * 
     * Removes a token by setting the JPanel at blanks[r][c] to have a blank image. 
     */
    public void removeToken(int r, int c) {
        
        try {            
            Image image = ImageIO.read(new File("Images/nothing.png")).getScaledInstance(65, 65, Image.SCALE_DEFAULT);
            blanks[r][c].setIcon(new ImageIcon(image));            
        } 
        catch (Exception e) {
        }
        repaint();
        
    }
}
