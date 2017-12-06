package cinemaclub.guiMain;
//package cinemaclub.guiMain.LoginGui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Cinema Club - Login");
        stage.setScene(StageSceneNavigator.createScene(StageSceneNavigator.loadLoginPane()));
        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the vista switching StageSceneNavigator.
     * Loads the first vista into the fxml layout.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */


    public static void main(String[] args) {
        launch(args);
    }
}