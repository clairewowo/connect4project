/**
 * 
 * @author Claire Wang
 * @version 5/10/21
 *
 * A computer player decides what moves the computer makes. It has an 
 * array of possible moves, with each index of the array being a move
 * from each column. 
 * 
 */
public class ComputerPlayer {
    
    private ComputerMove[] moves;    
    private int[][] board;
    
    /**
     * 
     * @param b is the board
     * 
     * Creates a new computer player. Sets board to b and initializes moves, the
     * array that holds possible moves. The index numbers of the array 
     * correspond to the column that the ComputerMove will go to. 
     */
    public ComputerPlayer(int[][] b) {  
        board = b;
        moves = new ComputerMove[7];             
    }
    
    /**
     * Creates a new move object for every column. Then, it iterates through
     * the array to find the move with the largest effectiveness number. 
     * 
     * @return the column number of the best move
     */
    public int findMove() {
        moves[0] = new ComputerMove(0, board);
        moves[1] = new ComputerMove(1, board);
        moves[2] = new ComputerMove(2, board);
        moves[3] = new ComputerMove(3, board);
        moves[4] = new ComputerMove(4, board);
        moves[5] = new ComputerMove(5, board);
        moves[6] = new ComputerMove(6, board);
        
        int max = -1;
        int index = 0;
        for (int i = 0; i < moves.length; i++) {
            if (moves[i].getENumber() >= max) {
                max = moves[i].getENumber();
                index = i;
            }
        }
        
        return index;
    }
    
    /**
     * 
     * @param b is the new board. 
     * Sets board to b. 
     * This method is called every time it is computer player's turn
     */
    public void updateBoard(int[][] b) {
        board = b;
    }
    
    /**
     * For testing purposes
     * @return moves
     */
    public ComputerMove[] getMoves() {
        return moves;
    }
    
}
