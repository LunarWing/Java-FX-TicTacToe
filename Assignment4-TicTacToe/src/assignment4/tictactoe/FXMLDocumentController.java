/*
 * I, Zach Lucas, 000394475 state that all code here is either my own or has been properly cited. I have not provided my work to anyone.
 */
package assignment4.tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

/**
 * @author Zach Lucas
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML public Button upperLeft;@FXML public Button upperCenter;@FXML public Button upperRight;
    @FXML public Button middleLeft;@FXML public Button middleCenter;@FXML public Button middleRight;
    @FXML public Button lowerLeft;@FXML public Button lowerCenter;@FXML public Button lowerRight;
    @FXML private Label winLabel;
    @FXML public Pane startPane;
    @FXML public Pane blockerPane;
    
    private int player;
    private static Engine engine;
    private static MinMaxBot aiBot;

    /**
     * Removes starting menu when clicked and starts the game as either player 1 or player 2 depending on your choice.
     * If player 2 is chosen, the bot will pick first before the player can.
     * @param event 
     */
    @FXML private void startAsPlayer1(ActionEvent event) {
        System.out.println("Starting games as player 1");
        startPane.setVisible(false);
        player = 1;
        engine = new Engine(player);
        aiBot = new MinMaxBot(engine.hPlayer, engine.aiPlayer, engine.gameBoard);
    }
    @FXML private void startAsPlayer2(ActionEvent event){
        System.out.println("Starting games as player 2");
        //Hide starting menu
        startPane.setVisible(false);
        player = 2;
        engine = new Engine(player);
        aiBot = new MinMaxBot(engine.hPlayer, engine.aiPlayer, engine.gameBoard);
        blockerPane.setVisible(true);
        fireBotsChoice();
        blockerPane.setVisible(false);
        //Make bot go first
    }
    
    /**
     * Gets the AI's choice and fires the corresponding button
     */
    private void fireBotsChoice(){
        int botsChoice = aiBot.makeMove(engine.gameBoard);
        switch(botsChoice){
            case 1:upperLeft.fire(); break;
            case 2:upperCenter.fire(); break;
            case 3:upperRight.fire(); break;
            case 4:middleLeft.fire(); break;
            case 5:middleCenter.fire(); break;
            case 6:middleRight.fire(); break;
            case 7:lowerLeft.fire(); break;
            case 8:lowerCenter.fire(); break;
            case 9:lowerRight.fire(); break;
        }
    }

    /**
     * All buttons used for Tic Tac Toe board.
     * When a button is clicked, the button will show X or O depending on the turn, then the button is disabled.
     * Afterwards, we check if the game is over and if not, the bots turn will be called. Then it checks again if the game is over.
     * If the game is still not over after the bot picks a node, the player will be allowed to pick another button.
     * Afterwards 
     * @param event 
     */
    @FXML private void pickCellUL(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(7);
        upperLeft.textProperty().setValue(move);
        upperLeft.setDisable(true);
        
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellUC(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(8);
        upperCenter.textProperty().setValue(move);
        upperCenter.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellUR(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(9);
        upperRight.textProperty().setValue(move);
        upperRight.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellML(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(4);
        middleLeft.textProperty().setValue(move);
        middleLeft.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellMC(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(5);
        middleCenter.textProperty().setValue(move);
        middleCenter.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellMR(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(6);
        middleRight.textProperty().setValue(move);
        middleRight.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellLL(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(1);
        lowerLeft.textProperty().setValue(move);
        lowerLeft.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellLC(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(2);
        lowerCenter.textProperty().setValue(move);
        lowerCenter.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    @FXML private void pickCellLR(ActionEvent event){
        blockerPane.setVisible(true);
        String move = engine.selectNode(3);
        lowerRight.textProperty().setValue(move);
        lowerRight.setDisable(true);
        if(engine.getWinner() != null){
            winLabel.setText(engine.getWinner());
        }else{
            if(engine.turn.equals(engine.aiPlayer)){
                fireBotsChoice();
            }
            if(engine.getWinner() != null){
                winLabel.setText(engine.getWinner());
            }else{
                blockerPane.setVisible(false);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { }    
    
}
