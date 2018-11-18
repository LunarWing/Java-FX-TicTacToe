/*
 * I, Zach Lucas, 000394475 state that all code here is either my own or has been properly cited. I have not provided my work to anyone.
 */
package assignment4.tictactoe;

/**
 * @author Zach Lucas
 */
public class Engine {

    private static Cell[][] grid;
    public String turn;
    public String aiPlayer; // Need to set AI here
    public static String hPlayer;
    private static String winner = null;
    public static Board gameBoard;

    /**
     * Constructor for engine class.
     * Set's Human and AI's player turn.
     * @param player 
     */
    public Engine(int player){
        turn = "X";
        gameBoard = new Board(3);
        gameBoard.populateEmptyBoard();
        if(player == 1){
            hPlayer = "X";
            aiPlayer = "O";
        }else{
            hPlayer = "O";
            aiPlayer = "X";
        }
    }
    
    /**
     * Takes an index which is used to select a node on the board. 
     * If the node is empty, the node will be selected and turned X or O depending on who's turn it is.
     * After a node is selected, the turn is changed.
     * @param index
     * @return String "X", "O" or Null
     */
    public String selectNode(int index){
        int[] choices = new int[2];
        switch(index){
            case 1:choices[0]=2;choices[1]=0;break;
            case 2:choices[0]=2;choices[1]=1;break;
            case 3:choices[0]=2;choices[1]=2;break;
            case 4:choices[0]=1;choices[1]=0;break;
            case 5:choices[0]=1;choices[1]=1;break;
            case 6:choices[0]=1;choices[1]=2;break;
            case 7:choices[0]=0;choices[1]=0;break;
            case 8:choices[0]=0;choices[1]=1;break;
            case 9:choices[0]=0;choices[1]=2;break;
        }
        if(checkEmpty(choices)){
            grid[choices[0]][choices[1]].setValue(turn);
            winner = gameBoard.checkWinner();
            if (turn.equals("X")) {
                turn = "O";
                return "X";
            } else {
                turn = "X";
                return "O";
            }
        }
        return null;
    }
    
    /**
     * Check if the current board position is empty or not
     * @param index
     * @return Boolean Ture or False
     */
    private boolean checkEmpty(int[] index){
        grid = gameBoard.getBoard();
        return "E".equals(grid[index[0]][index[1]].getVlaue());
    }

    /**
     * Gets the current winner variable
     * @return winner
     */
    public static String getWinner() {
        return winner;
    }
    
}