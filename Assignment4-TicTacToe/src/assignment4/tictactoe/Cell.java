/*
 * I, Zach Lucas, 000394475 state that all code here is either my own or has been properly cited. I have not provided my work to anyone.
 */
package assignment4.tictactoe;

/**
 * A class of cells to be used for Tic Tac Toe
 * @author Zach Lucas
 */
public class Cell {
    private String value;
    
    public Cell(){
        value = "E";
    }
            
    public String getVlaue(){
        return value;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
}
