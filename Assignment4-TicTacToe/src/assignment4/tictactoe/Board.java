/*
 * I, Zach Lucas, 000394475 state that all code here is either my own or has been properly cited. I have not provided my work to anyone.
 */
package assignment4.tictactoe;


/**
 * A Game board for Tic Tac Toe with 3 rows and 3 cols
 * @author Zach Lucas
 */
public class Board {
    private Cell[][] grid;
    
    /**
     * Used to set the size of the board if you want a different number of rows or cols
     * @param row
     * @param col 
     */
    public Board(int row, int col){
        grid = new Cell[row][col];
    }
    
    /**
     * Used to create the game board as uniform gird based on size inputted
     * @param size 
     */
    public Board(int size){
        grid = new Cell[size][size];
    }
    
    /**
     * Instantiates all cells in the board and gives them a empty value
     */
    public void populateEmptyBoard() {
        for (int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++){
                Cell temp = new Cell();
                temp.setValue("E");
                grid[a][b] = temp;
                
            }
        }
    }
    
    public Cell[][] getBoard(){
        return grid;
    }
    
    @Override
    public String toString(){
        String temp = "";
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                temp = temp + grid[i][j].getVlaue();
            }
        }
        return temp;
    }
    
    public void printBoard() {
        System.out.println("/---|---|---\\");
        System.out.println("| " + grid[0][0].getVlaue() + " | " + grid[0][1].getVlaue() + " | " + grid[0][2].getVlaue() + " |7,8,9");
        System.out.println("|-----------|");
        System.out.println("| " + grid[1][0].getVlaue() + " | " + grid[1][1].getVlaue() + " | " + grid[1][2].getVlaue() + " |4,5,6");
        System.out.println("|-----------|");
        System.out.println("| " + grid[2][0].getVlaue() + " | " + grid[2][1].getVlaue() + " | " + grid[2][2].getVlaue() + " |1,2,3");
        System.out.println("/---|---|---\\");
    }
    
    /**
     * Checks if the player inputted has 3 in a row on the board.
     * The binary board patterns were found using this site as help: 
     * https://codepen.io/labiej/post/using-bit-logic-to-keep-track-of-a-tic-tac-toe-game
     * @param thePlayer
     * @return 
     */
    public boolean hasWon(String thePlayer) {
        int[] winningPatterns = {
            0b111000000, 0b000111000, 0b000000111, // rows
            0b100100100, 0b010010010, 0b001001001, // cols
            0b100010001, 0b001010100               // diagonals
        };
        int pattern = 0b000000000;  // 9-bit pattern for the 9 cells
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (grid[row][col].getVlaue().equals(thePlayer)) {
                   pattern |= (1 << (row * 3 + col)); // Shift Bits left based on row and col
                }
            }
        }
        // Check if my board pattern is one of the winning patterns
        for (int winningPattern : winningPatterns) {
            if ((pattern & winningPattern) == winningPattern) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Check if someone won or if its a draw.
     * @return (Null, O, or X depending on game state)
     */
    public String checkWinner() {
        if (hasWon("X")) {
            return "X has Won!";
        } else if (hasWon("O")) {
            return "O has Won!";
        }
        for (int a = 0; a < 3; a++) {
            for(int b = 0; b < 3; b++){
                if ("E".equals(grid[a][b].getVlaue())) {
                    return null;
                }
            }
        }
        return "It's a draw!";
    }
}
