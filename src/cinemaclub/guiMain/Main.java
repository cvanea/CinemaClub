package cinemaclub.guiMain;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class to load the GUI.
 */
public class Main extends Application {

    /**
     * Starts the Gui loading up the login stage with login user view.
     * @param stage inputs a starting stage
     * @throws IOException if the view cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Cinema Club - Login");
        stage.setScene(StageSceneNavigator.createScene(StageSceneNavigator.loadLoginPane()));
        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the view switching StageSceneNavigator.
     * Loads the first view into the fxml layout.
     * @param args cli arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}