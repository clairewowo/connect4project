/**
 * 
 * @author Claire Wang
 * @version 5/4/21
 *
 *
 * ComputerMove are the moves that the computer will make. A computer will have 7
 * of these objects, one for each column. Effectiveness numbers will be calculated
 * for each one, and the one with the highest will be played. 
 */
public class ComputerMove {
    private int column;
    private int row;
    private int[][] board;
    private int eNumber;
    private boolean valid;
    
    
    /**
     *
     * @param col is the column number the move will got to
     * @param b is the array of the board
     * 
     * Row will be solved for by finding the lowest row (highest number) 
     * that is not already filled with a token
     * 
     * eNumber, effectiveness number is also determined using the 
     * connectOrPreventNumber() method
     * 
     * eNumber will equal: 
     * 6 if it connects 4 (wins the game), (connect number = 3)
     * 5 if it prevents the opponent from connecting 4
     * 4 if it connects 3 (connect number = 2)
     * 3 if it prevents the opponent from connecting 3
     * 2 if it connects 2 (connect number = 1)
     * 1 if it prevents the opponent from connecting 2 or it won't connect 
     * anything
     * 
     * If the game is in a position where there is nothing to connect or prevent
     * from connecting (like when it is the first move), the number will be
     * how many columns it is away from the center. 
     * 
     *  4 if it is in the center column, 3 if it is 1 column away, 2 if it is 2
     *  columns away, and so on. 
     *
     */
    public ComputerMove (int col, int[][] b) {
        column = col;
        board = b;       
        row = 5;
        while (row > 0) {
            if (board[row][column] != 0) {
                row --;
            }
            else {
                break;
            }
        }
        
        // check if the move is valid based on row and if the board is occupied
        // there
        valid = true;
        
        if (row == 0 && board[row][column] != 0) {
            valid = false;
            eNumber = -1;
        }
        
        if (valid) {
            // find eNumber
            int connect = connectOrPreventNumber(1);
            int prevent = connectOrPreventNumber(2);
            
            if (connect == 0 && prevent == 0) {
                eNumber = 3 - Math.abs(column - 3);
            }
            else {
                if (connect >= prevent) {
                    eNumber = 2 * connect;
                }
                else {
                    eNumber = 2 * prevent - 1;
                }
            }
        }
               
        
    }
    
    /**
     * 
     * @return the column the move will go to once it is played
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * 
     * @return the row number the move will go to
     */
    public int getRow() {
        return row;
    }
    
    /**
     * 
     * @return whether the move is valid. If the column it is in is already
     * full, then it is not a valid move. 
     */
    public boolean isValid() {
        return valid;
    }
    
    /**
     * 
     * @return the efficacy number, which indicates how good the move is
     * 
     * 
     */
    public int getENumber() {
        
        return eNumber;
    }
    
    
    /**
     * 
     * @param token is the player's number you want to search for- token will be 1
     * if you are trying to look how many computer tokens it will connect and it 
     * will be 2 if you are trying to look for how many of user's tokens will be blocked. 
     * 
     * The method uses an array called connectNumbers. The number of pieces connecting
     * vertically will be in connectNumbers[0], the number of pieces connecting horizontally
     * will be in connectNumbers[1], the number of pieces connect diagonally left will be
     * in connectNumbers[2], and the number of pieces going diagonally right will be
     * connectNumbers[3]. At the end, the largest number will be picked out of the array. 
     * 
     * @return 
     * If token = 1, it will return how many tokens this move will connect in a line. 
     * For example, if this is a winning move, it will return 4. 
     * 
     * If token = 2, it will return the amount of the opponent's tokens in a line that 
     * it will block. For example, if the opponent is about to connect 4 and this move
     * would prevent it, then it would return 3. 
     */
    public int connectOrPreventNumber(int token) {
        int[] connectNumbers = new int[4];
        
        checkVertical(token, connectNumbers);
        checkHorizontal(token, connectNumbers);
        checkDiagonalLeft(token, connectNumbers);
        checkDiagonalRight(token, connectNumbers);
            
        int max = 0;
        for (int i = 0; i < connectNumbers.length; i++) {
            if (connectNumbers[i] > max) {
                max = connectNumbers[i];
            }
        }
        
        return max;
        
    }
        
    /**
     * 
     * @param token is the piece you are looking for. If it is 1, it is your own piece,
     * if it is 2, it is your opponent's piece and you are trying to prevent your 
     * opponent's move. 
     * 
     * @param connectNumbers is the array that you put your number to. Vertical connects
     * will be put in connectNumbers[0]. 
     * 
     * For example, if token = 1 and the next move would win the game in 
     * a vertical line, 4 would be put into connectNumbers[0]. If token = 2 and your
     * opponent is about to win in the vertical line, 3 would be put into connectNumbers[0]. 
     * 
     * Checks how many pieces would connect vertically after the piece is dropped. 
     */
    private void checkVertical(int token, int[] connectNumbers) {
        int increment = 1;
        int occurences = 0;
        
        if (row + increment > 5) {
            connectNumbers[0] = 0;
            return;
        }
        
        while (row + increment <= 5 && board[row + increment][column] == token 
                && increment <= 3) {            
            occurences++;                               
            increment++;
        }
        connectNumbers[0] = occurences;
    }
    
    /**
     * 
     * @param token is the token you are checking for
     * @param connectNumbers is the array to put your number in. For horizontal connects,
     * the number will be put into connectNumber[1]. 
     * 
     * The logic is the same as checkVertical, except it checks horizontally
     * instead. 
     */
    private void checkHorizontal(int token, int[] connectNumbers) {
        
        //check horizontally    
        int increment = 1;
        boolean right = true;
        boolean left = true;
        int occurences = 0;
        
        
        
        while (increment <= 3 && (left || right)) {  
            if (column + increment <= 6 && board[row][column + increment] == token && right) {
                occurences++;
            }
            else {
                right = false;
            }
            if (column - increment >= 0 && board[row][column - increment] == token && left) {
                occurences++;
            }    
            else {
                left = false;
            }
            increment++;
        }    
        
        connectNumbers[1] = occurences;
        
    }
    
    /**
     * 
     * @param token is the token you are checking for
     * @param connectNumbers is the array to put your number in. For diagonal left 
     * connects, the number will be put into connectNumber[2]. 
     * 
     * The logic is the same as checkVertical, except it checks in a diagonal line
     * going from the top left to bottom right instead. 
     * 
     */
    private void checkDiagonalLeft(int token, int[] connectNumbers) {
        int increment = 1;
        boolean right = true;
        boolean left = true;
        int occurences = 0;


        while (increment <= 3 && (left || right)) {   
            if (column - increment >= 0 && row - increment >= 0 && 
                    board[row - increment][column - increment] == token && right) {
                occurences++;
            }
            else {
                right = false;
            }
            if (column + increment <= 6 && row + increment <= 5 && 
                    board[row + increment][column + increment] == token && left) {
                occurences++;
            }    
            else {
                left = false;
            }
            increment++;
        } 
        connectNumbers[2] = occurences;
    }
    
    
    /**
     * 
     * @param token is the token you are checking for
     * @param connectNumbers is the array to put your number in. For diagonal right connects,
     * the number will be put into connectNumber[3]. 
     * 
     * The logic is the same as checkVertical, except it checks in a diagonal line
     * going from the bottom left to top right instead.  
     * 
     */
    private void checkDiagonalRight(int token, int[] connectNumbers) {
        int increment = 1;
        boolean right = true;
        boolean left = true;
        int occurences = 0;

        while (increment <= 3 && (left || right)) {   
            if (column - increment >= 0 && row + increment <= 5 && 
                    board[row + increment][column - increment] == token && right) {
                occurences++;
            }
            else {
                right = false;
            }
            if (column + increment <= 6 && row - increment >= 0 && 
                    board[row - increment][column + increment] == token && left) {
                occurences++;
            }    
            else {
                left = false;
            }
            increment++;
        } 
        connectNumbers[3] = occurences;
    }

}
