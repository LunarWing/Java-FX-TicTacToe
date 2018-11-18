/*
 * I, Zach Lucas, 000394475 state that all code here is either my own or has been properly cited. I have not provided my work to anyone.
 */
package assignment4.tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * An AI Bot that used the MinMax Search Algorithm to play tic tac toe.
 * @author Zach Lucas
 */
public class MinMaxBot extends FXMLDocumentController{
    
    Board CurrentBoard; // current board to check 
    String myTurn; 
    String oppTurn; 
    int count = 0; // Number of times the minmax function is called each turn
    
    /**
     * Constructor that set who is X or O on the board for the AI and gives the current board to the AI
     * @param oppTurn
     * @param myTurn
     * @param board 
     */
    public MinMaxBot(String oppTurn, String myTurn, Board board) {
        this.myTurn = myTurn;
        this.oppTurn = oppTurn;
        CurrentBoard = board;
    }

    /**
     * When the current board is passed it, this will start the MINMAX function and return the best cell on the board as its choice
     * @param board
     * @return bestPossibleChoice
     */
    public int makeMove(Board board) {
        this.CurrentBoard = board;
        int[] bestPossibleChoice;
        System.out.println("ai is thinking...");
        bestPossibleChoice = minimax(2, myTurn);
        System.out.printf("Minimax was called %d times\n", count);
        int choice = 0;
        if(bestPossibleChoice[1] == 2 && bestPossibleChoice[2] == 0){ choice = 7; }
        else if(bestPossibleChoice[1] == 2 && bestPossibleChoice[2] == 1){ choice = 8; }
        else if(bestPossibleChoice[1] == 2 && bestPossibleChoice[2] == 2){ choice = 9; }
        else if(bestPossibleChoice[1] == 1 && bestPossibleChoice[2] == 0){ choice = 4; }
        else if(bestPossibleChoice[1] == 1 && bestPossibleChoice[2] == 1){ choice = 5; }
        else if(bestPossibleChoice[1] == 1 && bestPossibleChoice[2] == 2){ choice = 6; }
        else if(bestPossibleChoice[1] == 0 && bestPossibleChoice[2] == 0){ choice = 1; }
        else if(bestPossibleChoice[1] == 0 && bestPossibleChoice[2] == 1){ choice = 2; }
        else if(bestPossibleChoice[1] == 0 && bestPossibleChoice[2] == 2){ choice = 3; }
        System.out.println(choice);
        return choice;
    }

    /**
     * Recursively called minimax at level of depth for either maximizing or minimizing the players score.
     * @param depth
     * @param Turn
     * @return int[3] of {score, row, col} 
     */
    public int[] minimax(int depth, String Turn) {
        count++;
        List<int[]> possibleChoice = generateMoves(); // Get all possible moves in a list of row's and cols
        Cell[][] grid = CurrentBoard.getBoard();
        // If Turn is the AI's turn the best Score will be Maximized and if it's not my turn, bestScore will be Minimized 
        int bestScore = (Turn.equals(myTurn)) ? Integer.MIN_VALUE : Integer.MAX_VALUE; 
        int currentScore;
        int bestRow = -1;
        int bestCol = -1;
        if (possibleChoice.isEmpty() || depth == 0) {
            // Gameover or depth reached, evaluate score
            bestScore = sumScore();
        }else{
            for (int[] move : possibleChoice) {
                // Try this move for the current Turn
                grid[move[0]][move[1]].setValue(Turn);
                if (Turn.equals(myTurn)) {  // Computer is maximizing player (Turn)
                    currentScore = minimax(depth - 1, oppTurn)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else {
                    currentScore = minimax(depth - 1, myTurn)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                // Undo move as we only made the move to check its value
                grid[move[0]][move[1]].setValue("E");
            }
        }
        return new int[] {bestScore, bestRow, bestCol};
        
    }

    /**
     * The evaluation function for the current board
     * @return score
     */
    private int sumScore() {
        int score = 0;
        // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
        score += checkLine(0, 0, 0, 1, 0, 2);  // row 0
        score += checkLine(1, 0, 1, 1, 1, 2);  // row 1
        score += checkLine(2, 0, 2, 1, 2, 2);  // row 2
        score += checkLine(0, 0, 1, 0, 2, 0);  // col 0
        score += checkLine(0, 1, 1, 1, 2, 1);  // col 1
        score += checkLine(0, 2, 1, 2, 2, 2);  // col 2
        score += checkLine(0, 0, 1, 1, 2, 2);  // diagonal
        score += checkLine(0, 2, 1, 1, 2, 0);  // alternate diagonal
        return score;
    }

    /**
     * The evaluation function for the given line of 3 cells
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @param row3
     * @param col3
     * @return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
     *         -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
     *         0 otherwise 
     */
    private int checkLine(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;
        Cell[][] grid = CurrentBoard.getBoard();
        // First cell
        if (grid[row1][col1].getVlaue().equals(myTurn)) {
            score = 1;
        } else if (grid[row1][col1].getVlaue().equals(oppTurn)) {
            score = -1;
        }
        // Second cell
        if (grid[row2][col2].getVlaue().equals(myTurn)) {
            switch (score) {
                case 1:score = 10;break;// cell1 is mySeed
                case -1:return 0;// cell1 is oppSeed
                default:score = 1;break;// cell1 is empty
            }
        } else if (grid[row2][col2].getVlaue().equals(oppTurn)) {
            switch (score) {
                case -1:score = -10;break;// cell1 is oppSeed
                case 1:return 0;// cell1 is mySeed
                default:score = -1;break;// cell1 is empty
            }
        }
        // Third cell
        if (grid[row3][col3].getVlaue().equals(myTurn)) {
            if (score > 0) { score *= 10; }// cell1 and/or cell2 is mySeed
            else if (score < 0) { return 0; }// cell1 and/or cell2 is oppSeed  
            else { score = 1; } // cell1 and cell2 are empty
        } else if (grid[row3][col3].getVlaue().equals(oppTurn)) {
            if (score < 0) { score *= 10; } // cell1 and/or cell2 is oppSeed  
            else if (score > 1) { return 0; } // cell1 and/or cell2 is mySeed
            else { score = -1; } // cell1 and cell2 are empty
        }
        return score;
    }
    
    /**
     * Find all valid next moves.
     * @return List of moves in int[2] of {row, col} or empty list if gameover 
     */
    public List<int[]> generateMoves(){
        List<int[]> nextMoves = new ArrayList<>();
        Cell[][] grid = CurrentBoard.getBoard();
        if (CurrentBoard.hasWon("X") || CurrentBoard.hasWon("O")) {
           return nextMoves;   // If gameover, i.e., no next move, return empty list
        }
        // Search for empty cells and add to the List
        for (int row = 0; row < 3; ++row) {
           for (int col = 0; col < 3; ++col) {
              if ("E".equals(grid[row][col].getVlaue())) {
                 nextMoves.add(new int[] {row, col});
              }
           }
        }
        return nextMoves;
     }
}