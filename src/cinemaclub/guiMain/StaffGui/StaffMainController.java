package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.LoginGui.LoginMainController;
import cinemaclub.guiMain.StageSceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * Main controller class for the entire layout.
 */
public class StaffMainController {

    static Cinema cinema = LoginMainController.getCinema();

    public void setCinema(Cinema cinema) { // Setting the cinema-object
        StaffMainController.cinema = cinema;
    }

    /** Holder of a switchable vista. */
    @FXML
    private StackPane viewHolder;

    @FXML
    public Label headerLabelEdit;

    public void pressLogOut(ActionEvent event) throws IOException {
        StageSceneNavigator.loginStage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void pressHome(ActionEvent event) throws IOException {
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_HOME);
    }

    public void pressProfile(ActionEvent event) throws IOException {
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_PROFILE);
    }

    public void pressShowings(ActionEvent event) throws IOException {
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SHOWINGS);
    }

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */

    public void setView(Node node) {
        viewHolder.getChildren().setAll(node);
    }

}