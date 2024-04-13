/**
 * 
 * @author Claire Wang
 * @version 5/7/21
 * 
 * Move is an object that is stored in stacks every time a move is played. It is just
 * a data storage advice. When a player takes back a move, the stacks
 * in the game classes will pop out one move and using the information on it, and it 
 * will be able to clear that token. 
 * 
 *
 */
public class Move {
    
    private int playerNumber;
    private int row;
    private int col;
    
    
    /**
     * 
     * @param n is the player number. 1 means player 1 or the computer, and
     * 2 means it is for player 2. 
     * @param r is the row where the token is located
     * @param c is the column where the token is located
     * 
     * Creates a move by initializing the fields playerNumber, row, and col. 
     */
    public Move(int n, int r, int c) {
        playerNumber = n;
        row = r;
        col = c;
    }
    
    /**
     * 
     * @return the column the token is located
     */
    public int getColumn() {
        return col;
    }
    
    /**
     * 
     * @return the row the token is located
     */
    public int getRow() {
        return row;
    }
    
    /**
     * 
     * @return playerNumber, which tells whose token it is. 
     */
    public int getPlayer() {
        return playerNumber;
    }
}
