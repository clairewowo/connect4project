import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Connect4Test {
    /*
     * {{0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0}, 
        {0, 0, 0, 0, 0, 0, 0}};
     */
    
    /*
    @Test
    public void testVerticalConnect4() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}};
        ComputerMove move = new ComputerMove(3, board);
        int[] number = new int[4];
        move.checkVertical(1, number);     
    }
    
    @Test
    public void testVerticalBrokenConnect4() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 2, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}};
        ComputerMove move = new ComputerMove(3, board);
        int[] number = new int[4];
        move.checkVertical(1, number);
        assertEquals(number[0], 1);        
    }
    */
    
    @Test
    public void testMoveConstructor1() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}};
        ComputerMove move = new ComputerMove(3, board);
        assertEquals(2, move.getRow());
    }
    
    @Test
    public void testMoveConstructor2() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}};
        ComputerMove move = new ComputerMove(0, board);
        assertEquals(5, move.getRow());
    }
    
    @Test
    public void testBug() {
        int[][] board2 =                 
            {{0, 0, 0, 0, 1, 0, 0}, 
             {0, 0, 0, 0, 2, 0, 0}, 
             {0, 0, 0, 0, 2, 0, 0}, 
             {0, 0, 0, 0, 1, 0, 0}, 
             {0, 0, 0, 0, 2, 0, 0}, 
             {0, 0, 0, 0, 1, 1, 0}};
        ComputerMove move = new ComputerMove(4, board2);
        assertFalse(move.isValid());
    }

    /*

    @Test
    public void testHorizontalConnects() {
        int[][] board2 =                 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 2, 2, 0, 1, 1, 1}};
        ComputerMove move3 = new ComputerMove(3, board2);
        int[] number = new int[4];
        move3.checkHorizontal(2, number);
        assertEquals(2, number[1]);
        
        move3.checkHorizontal(1, number);
        assertEquals(3, number[1]);
    }
    
    @Test
    public void testDiagonalLeftConnect1() {
        int[][] board = 
           {{0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0}, 
            {1, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0}, 
            {0, 2, 1, 0, 0, 0, 0}, 
            {0, 2, 1, 0, 0, 0, 0}};
        ComputerMove move = new ComputerMove(1, board);
        int[] number = new int[4];
        move.checkDiagonalLeft(1, number);
        assertEquals(2, number[2]);
    }
    
    @Test
    public void testDiagonalLeftConnect2() {
        int[][] board = 
           {{0, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0}, 
            {2, 0, 0, 0, 0, 0, 0}, 
            {0, 0, 0, 0, 0, 0, 0}, 
            {0, 1, 2, 0, 0, 0, 0}, 
            {0, 2, 1, 2, 0, 0, 0}};
        ComputerMove move = new ComputerMove(1, board);
        int[] number = new int[4];
        move.checkDiagonalLeft(2, number);
        assertEquals(3, number[2]);
    }
    
    @Test
    public void testDiagonalLeftConnect3() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 1, 0, 0, 0, 0, 0}, 
             {0, 2, 1, 0, 0, 0, 0}, 
             {0, 1, 0, 0, 0, 0, 0}, 
             {0, 2, 0, 1, 2, 0, 0}};
         ComputerMove move = new ComputerMove(3, board);
         int[] number = new int[4];
         move.checkDiagonalLeft(1, number);
         assertEquals(2, number[2]);
         
         move.checkDiagonalLeft(2, number);
         assertEquals(1, number[2]);
         
         ComputerMove corner = new ComputerMove(0, board);
         corner.checkDiagonalLeft(2, number);
         assertEquals(0, number[2]);
    }
    
    @Test
    public void testDiagonalRightConnect1() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 1, 0}, 
             {0, 0, 0, 0, 1, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}};
         ComputerMove move = new ComputerMove(2, board);
         int[] number = new int[4];
         move.checkDiagonalRight(1, number);
         assertEquals(3, number[3]);
         
    }
    
    @Test
    public void testDiagonalRightConnect2() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 1, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 1, 2, 0, 0}, 
             {0, 0, 1, 0, 2, 0, 0}};
         ComputerMove move = new ComputerMove(4, board);
         int[] number = new int[4];
         move.checkDiagonalRight(1, number);
         assertEquals(3, number[3]);
         
         ComputerMove corner = new ComputerMove(6, board);
         corner.checkDiagonalRight(2, number);
         assertEquals(0, number[3]);
    }
    */
    
    @Test
    public void testGetENumber1() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 1, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 1, 2, 0, 0}, 
             {0, 0, 1, 0, 2, 0, 0}};
         ComputerMove move = new ComputerMove(4, board);
         assertEquals(6, move.getENumber());
    }
    
    @Test
    public void testGetENumber2() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 2, 2, 0, 0, 0}};
        ComputerMove move = new ComputerMove(2, board);
        //move.checkVertical(1, number);        
        assertEquals(1, move.getENumber());
    }
    
    @Test
    public void testGetENumber3() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 1, 0, 0, 0, 0}, 
             {2, 0, 2, 2, 0, 0, 0}};
        ComputerMove move = new ComputerMove(1, board);    
        assertEquals(5, move.getENumber());
    }
    
    @Test
    public void testGetENumber4() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}};
        ComputerMove move = new ComputerMove(3, board);    
        assertEquals(4, move.getENumber());
    }
    
    @Test
    public void testGetENumber5 () {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 2, 0, 2}, 
             {0, 0, 0, 0, 2, 1, 0}, 
             {0, 0, 0, 0, 1, 2, 0}, 
             {0, 0, 0, 1, 0, 1, 0}};
        ComputerMove move = new ComputerMove(5, board);
        //int[] number = new int[4];
        //move.checkHorizontal(2, number);
        assertEquals(3, move.getENumber());        
    }
    
    @Test
    public void testFindMove1() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 0, 0}, 
             {0, 0, 0, 1, 0, 1, 0}};
        ComputerPlayer computer = new ComputerPlayer(board);
        assertEquals(3, computer.findMove());
    }
    
    @Test
    public void testFindMove2() {
        int[][] board1 = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 1, 0}};
        ComputerPlayer computer = new ComputerPlayer(board1);
        assertEquals(computer.findMove(), 3);
    }
    
    @Test
    public void testFindMove3() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 2, 2, 0, 0}, 
             {0, 0, 2, 1, 1, 0, 0}, 
             {0, 2, 1, 1, 1, 0, 0}};
        ComputerPlayer computer = new ComputerPlayer(board);
        assertEquals(computer.findMove(), 5);
    }
    /*
    @Test
    public void testHumanGIsWin() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 2, 2, 0, 0}, 
             {0, 0, 2, 1, 1, 0, 0}, 
             {0, 2, 1, 1, 1, 0, 0}};
        HumanGame game = new HumanGame();
        game.setBoard(board);
        game.setTurn(2);
        assertTrue(game.isWin(4));
    }
    
    @Test
    public void testHumanGIsWin2() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 2, 2, 0, 0}, 
             {0, 0, 0, 2, 1, 0, 0}, 
             {0, 0, 1, 1, 2, 0, 0}};
        HumanGame game = new HumanGame();
        game.setBoard(board);
        game.setTurn(1);
        assertFalse(game.isWin(2));
        
    }
    */
    
    @Test
    public void testHumanGInputMove1() {
        HumanGame game = new HumanGame();
        game.inputMove(2);
        game.inputMove(1);
        int[][] board = game.getBoard();
        assertEquals(board[5][2], 1);
        assertEquals(board[5][1], 2);
    }
    
    @Test
    public void testHumanGInputMove2() {
        HumanGame game = new HumanGame();
        game.inputMove(1);
        game.inputMove(1);
        game.inputMove(1);
        game.inputMove(1);
        game.inputMove(1);
        game.inputMove(1);
        int[][] board = game.getBoard();
        assertEquals(board[0][1], 2);
        assertFalse(game.isValid(1));
        
    }
    
    @Test
    public void testComputerGisWin1() {
        ComputerGame game = new ComputerGame();
        //assertTrue(game.isPlayer1Turn());
        game.inputMove(1);
        
        /*
        int[][] board = game.getBoard();
        System.out.println(board[5][1]);
        assertEquals(0, board[5][1]);
        
        assertFalse(game.isWin(2, 1));
        */
    }
    
    @Test
    public void testComputerGComputerMove() {
        ComputerGame game = new ComputerGame();
        Move move = game.inputComputerMove();
        assertEquals(1, move.getPlayer());
        assertEquals(3, move.getColumn());
        assertEquals(5, move.getRow());
    }
    
    @Test
    public void testComputerGameInputMove() {
        ComputerGame game = new ComputerGame();
        game.inputComputerMove();
        game.inputMove(2);
        game.inputComputerMove();
        
        assertEquals(2, game.getBoard()[5][2]);
        /*
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                System.out.print(game.getBoard()[r][c] + " ");
            }
            System.out.println();
        }
        */
    }
    
    @Test
    public void humanGameGoBackAndForward() {
        HumanGame game = new HumanGame();
        game.inputMove(3);
        game.inputMove(3);
        game.inputMove(2);
        game.goBack();
        assertEquals(0, game.getBoard()[5][2]);
        /*
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                System.out.print(game.getBoard()[r][c] + " ");
            }
            System.out.println();
        }
        */
        game.goForward();
        assertEquals(1, game.getBoard()[5][2]);
        assertTrue(game.getBackStack().isEmpty());
    }
    
    @Test
    public void computerGameGoBackAndForward() {
        ComputerGame game = new ComputerGame();
        game.inputComputerMove();
        game.inputMove(2);
        game.inputComputerMove();
        game.inputMove(3);
        game.inputComputerMove();
        game.goBack2();
        
        assertFalse(game.getBackStack().isEmpty());
        assertEquals(3, game.getTurns());
        
        game.goForward2();
        /*
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 7; c++) {
                System.out.print(game.getBoard()[r][c] + " ");
            }
            System.out.println();
        }
        */
        assertTrue(game.getBackStack().isEmpty());
        assertEquals(2, game.getBoard()[4][3]);        
    }
    
    @Test
    public void testGameTurns() {
        HumanGame game = new HumanGame();
        game.inputMove(3);
        assertFalse(game.isPlayer1Turn());
        game.inputMove(3);
        assertTrue(game.isPlayer1Turn());
        game.goBack();
        assertFalse(game.isPlayer1Turn());
    }
    
    @Test
    public void testHumanGame() {
        int[][] board = 
            {{0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 0, 0, 0, 0, 0, 0}, 
             {0, 1, 2, 1, 2, 0, 0}, 
             {0, 1, 1, 2, 1, 0, 0}, 
             {0, 1, 2, 1, 2, 0, 0}};
        ComputerGame game = new ComputerGame();
        game.setBoard(board);
        assertTrue(game.isWin(1, 2, 4));
        assertTrue(game.isWin(2, 2, 1));
    }
        
        
        
    
}
