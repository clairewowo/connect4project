/**
 * 
 * @author Claire Wang
 * @version 5/12/21
 * 
 * 
 * A ComputerGame is like a HumanGame, except it inputs computer moves. It also
 * has to update the computer player's board every time someone makes a move. When people
 * take back a move, they take back their own move AND the computer move. When they go forward, 
 * they also move forward 2 steps. 
 * 
 * Has a field called computer, which is the computer player. 
 * 
 * Like HumanGame, people won't be able to take back their moves once the game is over. 
 */
public class ComputerGame extends HumanGame {

    private ComputerPlayer computer;
    
    /**
     * Creates a new computer game. Initializes the computer player, called computer. 
     */
    public ComputerGame() {
        super();
        computer = new ComputerPlayer(board);
    }
    
    /**
     * Inputs the computer's turn if it is their turn. 
     * 
     * @return the move that the computer wants to input, or null if it is not their turn. 
     */
    public Move inputComputerMove() {
        if (player1Turn) {
            int column = computer.findMove();              
                        
            Move recent = super.inputMove(column);
            computer.updateBoard(board);
            return recent;
        }
        return null;
    }
    
    /**
     * 
     * @param col is the column the move will go to
     * @return the move that the user wants to input. It will return null if it
     * is not the player's turn yet. 
     * 
     * Also empties the taken back moves stack and updates turns by adding 1 to it. 
     * Updates the computer player's board. 
     */
    @Override
    public Move inputMove(int col) {
        if (!isValid(col)) {
            return null;
        }
        
        if (player1Turn) {
            return null;
        }
        
        int row = 5;
        while (board[row][col] != 0 && row >= 0) {
            row--;
        }
        board[row][col] = 2;
        
        Move recent = new Move(2, row, col);
        playedMoves.push(recent);
        player1Turn = true;
        turns++;
        
        while(!takenBackMoves.isEmpty()) {
            takenBackMoves.pop();
        }
        
        computer.updateBoard(board);
        
        return recent;
    }
    
    /**
     * 
     * @return the 2 moves that were taken back, in an array. The first move taken
     * back will be in moves[0] and the second move taken back will be moves[1].
     * Returns null if 2 moves have not been played yet. 
     * 
     * This is different from the HumanGame taking back moves because it take back
     * the computer move as well as your move. 
     */
    public Move[] goBack2() {
        if (!player1Turn) {
            if (playedMoves.isEmpty()) {
                return null;
            }
            Move move1 = playedMoves.pop();
            
            if (playedMoves.isEmpty()) {
                return null;
            }
            Move move2 = playedMoves.pop();
            
            takenBackMoves.push(move1);
            takenBackMoves.push(move2);
            
            board[move1.getRow()][move1.getColumn()] = 0;
            board[move2.getRow()][move2.getColumn()] = 0;
            
            Move[] moves = {move1, move2};
            player1Turn = false;
            
            turns -= 2;
            return moves;
        }
        return null;
    }
    
    /**
     * 
     * @return the moves that were just taken back. Move[0] will be the computer move, 
     * Move[1] will be the player's previous move. Returns null if 2 moves have not taken
     * back yet. 
     * 
     * Updates turns by adding 2, also updates whose turn it will be. 
     * This method will be called when the user wants to go forward. The user can
     * only go forward if they have gone back and it is currently their turn in the game. 
     */
    public Move[] goForward2() {        
        if (!player1Turn) {
            if (takenBackMoves.isEmpty()) {
                return null;
            }           
            Move move1 = takenBackMoves.pop();
            
            if (takenBackMoves.isEmpty()) {
                return null;
            }
            Move move2 = takenBackMoves.pop();
            
            playedMoves.push(move2);
            playedMoves.push(move1);
            
            board[move1.getRow()][move1.getColumn()] = move1.getPlayer();
            board[move2.getRow()][move2.getColumn()] = move2.getPlayer();
            
            turns += 2;
            player1Turn = false;
            
            Move[] moves = {move1, move2};
            return moves;
        }
        
        return null;
        
               
    }
    
   

}
