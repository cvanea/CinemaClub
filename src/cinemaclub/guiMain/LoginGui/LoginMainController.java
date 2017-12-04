package cinemaclub.guiMain.LoginGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.StageSceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main controller class for the entire layout.
 */
public class LoginMainController implements Initializable {

    /** Holder of a switchable vista. */
    @FXML
    private StackPane viewHolder;
    @FXML
    public Label errorMain;

    public void pressTest(ActionEvent event) throws IOException {
        System.out.println("works");

//        StageSceneNavigator.staffStage(cinema);
//        ((Node)(event.getSource())).getScene().getWindow().hide();
//        Main.setPaneCinema(1);
    }


    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */


    public void setView(Node node) {
        viewHolder.getChildren().setAll(node);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}