/*
 * I, Zach Lucas, 000394475 state that all code here is either my own or has been properly cited. I have not provided my work to anyone.
 */
package assignment4.tictactoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Zach Lucas
 */
public class Assignment4TicTacToe extends Application {
    
    /**
     * Gets scene from FXML Document and shows scene
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
