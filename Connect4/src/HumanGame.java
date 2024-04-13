import java.util.Stack;

/**
 * 
 * @author Claire Wang
 * @version 5/11/21
 * 
 * A human game is a game between two human users. It can input moves into
 * the board, tell if a move is a winning move, and tell when the move is valid 
 * or not. It also keeps track of played moves by putting Move objects onto a
 * stack called playedMoves. When someone takes back a move, the Move object 
 * will be removed and put onto another stack, called takenBackMoves. 
 * 
 * The game will end when it is a stalemate or when someone plays a winning move (one
 * that connects 4). It will keep track of it is a stalemate by keeping track of
 * how many turns there are. If there are 42 and nobody has won yet, it is a draw/stalemate.
 * People cannot take their moves back once the game is over. 
 *
 */
public class HumanGame {
    
    protected int[][] board;
    protected Stack<Move> playedMoves;
    protected Stack<Move> takenBackMoves;
    protected boolean player1Turn;
    protected int turns;
    
    /**
     * Creates a human game with an empty board
     */
    public HumanGame() {
        board = new int[6][7];
        playedMoves = new Stack<Move>();
        takenBackMoves = new Stack<Move>();
        player1Turn = true;
        turns = 0;
    }
    
    /**
     * 
     * @param col tells which column the token will go into. inputMove
     * will have to calculate the row number as well
     * 
     * @return A move object of the current move, or null if it is not valid. 
     * 
     * Inputs a move by setting an entry in the array to whoever's turn it is. Col is the
     * column number it will be at, and the row number will be calculated 
     * based on how full the column is. Also creates a new Move object to
     * be put onto the playedMoves stack. Switches the turn of the player. Adds
     * 1 to turns so if it reaches 42, the game is a stalemate
     * 
     */
    public Move inputMove(int col) {       
        // move isn't valid
        if (!isValid(col)) {
            return null;
        }
        
        int player;
        if (player1Turn) {
            player = 1;
        }
        else {
            player = 2;
        }
        
        int row = 5;
        while (board[row][col] != 0 && row >= 0) {
            row--;
        }
        board[row][col] = player;
        
        Move recent = new Move(player, row, col);
        playedMoves.push(recent);
        player1Turn = !player1Turn;
        
        turns++;
        
        
        return recent;
    }
    
    /**
     * When the user presses the back button, this method will be called. 
     * The previous move will be removed from the playedMoves and added
     * to the takenBackMoves stack. The board will also remove the previous
     * move. Sets the turn to whichever player's move was taken back. 
     * 
     * @return the Move that was taken back. Returns null if there are
     * no more moves to take back. 
     * 
     */
    public Move goBack() {
        
        if (playedMoves.isEmpty()) {
            return null;
        }
        Move back = playedMoves.pop();       
        board[back.getRow()][back.getColumn()] = 0;
        int n = back.getPlayer();
        
        if (n == 1) {
            player1Turn = true;
        }
        else {
            player1Turn = false;
        }
        
        takenBackMoves.push(back);
        turns--;
        return back;
    }
    
    /**
     * When the user presses the forward button, this method will be called. 
     * The move that was just taken back will be played again on the board. The move
     * will also be added back to the playedMoves stack and removed from the 
     * takenBackMoves stack. 
     * 
     * @return the Move that was just added back to the board. Returns null if no
     * moves have been taken back. 
     */
    public Move goForward() {        
        if (takenBackMoves.isEmpty()) {
            return null;
        }
        Move forward = takenBackMoves.pop();
        
        board[forward.getRow()][forward.getColumn()] = forward.getPlayer();
        playedMoves.push(forward);
        
        turns++;
        
        int player = forward.getPlayer();
        if (player == 1) {
            player1Turn = false;
        }
        else {
            player1Turn = true;
        }
        return forward;
    }
    
    /**
     * 
     * @param col is the column number
     * @return false if the column is completely full, true if the column still has
     * space left
     */
    public boolean isValid(int col) {
        
        // move isn't valid
        if (board[0][col] != 0) {
            return false;
        }           
        return true;
    }
    
    /**
     * this method will be called before a move is entered. 
     * 
     * @param player is the number telling which player's token it is
     * @param col is the column number that the token goes in
     * @param row is the row number that the token goes in
     * @return true if putting the token there is a win, false if not
     */
    public boolean isWin(int player, int row, int col) {
        if (!isValid(col)) {
            return false;
        }
        
        int vertical = verticalConnect(row, col, player);
        int horizontal = horizontalConnect(row, col, player);
        int diagonalL = diagonalLeftConnect(row, col, player);
        int diagonalR = diagonalRightConnect(row, col, player);
        
        if (vertical >= 3 || horizontal >= 3 || 
                diagonalL >= 3 || diagonalR >= 3) {
            return true;
        }
        
        return false;
        
    }
    
    /**
     * 
     * @return the board
     */
    public int[][] getBoard() {
        return board;
    }
    
    /**
     * 
     * @return whether it is player1's turn
     */
    public boolean isPlayer1Turn() {
        return player1Turn;
    }
    
    /**
     * 
     * @param b is the new board
     * sets the current board to b. 
     * For testing purposes. 
     */
    public void setBoard(int[][] b) {
        board = b;
    }
    
    /**
     * 
     * @param player is the player who will be inputting the token. If
     * player is 1, it means it is player 1's turn. 
     * 
     * This is used for testing purposes. 
     */
    public void setTurn(int player) {
        if (player == 1) {
            player1Turn = true;
        }
        else if (player == 2) {
            player1Turn = false;
        }
    }
    
    /**
     * 
     * @return if the game is a stalemate (every column is full and the players 
     * cannot change the game anymore). The variable turns is updated everytime 
     * a player makes a move, takes back a move, or goes forward. It will equal 42
     * once the entire board is full/ if it is a stalemate. 
     */
    public boolean isStalemate() {
        if (turns == 42) {
            return true;
        }
        return false;
    }
        
    
    /**
     * 
     * @param player is the token you are trying to look for connects
     * @param col is the column number the move will be
     * @param row is the row number the move will be
     * @return the number of tokens it will connect to. For example, if 
     * the next move is a win, it will return 3. 
     */
    private int verticalConnect(int row, int col, int player) {
        int increment = 1;
        int occurences = 0;
        while (row + increment <= 5 && board[row + increment][col] == player 
                && increment <= 3) {            
            occurences++;                               
            increment++;
        }
        
        return occurences;
    }
    
    /**
     * 
     * @param col is the column number the move will be
     * @param row is the row number the move will be
     * @param player is the token you are looking for
     * @return the number of tokens you can connect horizontally
     */
    private int horizontalConnect(int row, int col, int player) {
        int increment = 1;
        boolean right = true;
        boolean left = true;
        int occurences = 0;
        
        while (increment <= 3 && (left || right)) {  
            if (col + increment <= 6 && board[row][col + increment] == player && right) {
                occurences++;
            }
            else {
                right = false;
            }
            if (col - increment >= 0 && board[row][col - increment] == player && left) {
                occurences++;
            }    
            else {
                left = false;
            }
            increment++;
        } 
        
        return occurences;
    }
    
   
    /**
     * 
     * @param row is the row number the move will be
     * @param col is the column number the move will be
     * @param player is the token you are looking for
     * @return the number of tokens you can connect in a diagonal line
     * starting from a top left corner to a bottom right corner
     */
    private int diagonalLeftConnect(int row, int col, int player) {
        int increment = 1;
        boolean right = true;
        boolean left = true;
        int occurences = 0;


        while (increment <= 3 && (left || right)) {   
            if (col - increment >= 0 && row - increment >= 0 && 
                    board[row - increment][col - increment] == player && right) {
                occurences++;
            }
            else {
                right = false;
            }
            if (col + increment <= 6 && row + increment <= 5 && 
                    board[row + increment][col + increment] == player && left) {
                occurences++;
            }    
            else {
                left = false;
            }
            increment++;
        } 
        
        return occurences;
    }
    
    /**
     * 
     * @param row is the row number the move will be
     * @param col is the column number the move will be
     * @param player is the token you are looking for
     * @return the number of tokens you can connect in a diagonal line starting
     * from a top right corner to a bottom left corner
     */
    private int diagonalRightConnect(int row, int col, int player) {
        int increment = 1;
        boolean right = true;
        boolean left = true;
        int occurences = 0;

        while (increment <= 3 && (left || right)) {   
            if (col - increment >= 0 && row + increment <= 5 && 
                    board[row + increment][col - increment] == player && right) {
                occurences++;
            }
            else {
                right = false;
            }
            if (col + increment <= 6 && row - increment >= 0 && 
                    board[row - increment][col + increment] == player && left) {
                occurences++;
            }    
            else {
                left = false;
            }
            increment++;
        } 
        
        return occurences;
    }
    
    /**
     * 
     * @return the takenBackMoves stack
     * 
     * Used for testing purposes
     */
    public Stack<Move> getBackStack() {
        return takenBackMoves;
    }
    
    /**
     * 
     * @return the playedMoves stack. 
     * Used for testing purposes
     */
    public Stack<Move> getPlayedMovesStack() {
        return playedMoves;
    }
    
    /**
     * 
     * @return turns. If turns = 1, it is player 1's turn. If it is 2, 
     * it is player 2's turn. 
     */
    public int getTurns() {
        return turns;
    }
    

}
