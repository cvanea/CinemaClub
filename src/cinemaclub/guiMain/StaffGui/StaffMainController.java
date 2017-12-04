package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.StageSceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * Main controller class for the entire layout.
 */
public class StaffMainController {

    Cinema cinema;

    public void setCinema(Cinema cinema) { // Setting the cinema-object
        this.cinema = cinema;
    }

    /** Holder of a switchable vista. */
    @FXML
    private StackPane vistaHolder;

    public void pressTest(ActionEvent event) throws IOException {
        StageSceneNavigator.customerStage(cinema);
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */

    public void setView(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

}