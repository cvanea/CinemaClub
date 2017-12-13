package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.GuiData;
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
public class MainController {

    static Cinema cinema = LoginMainController.getCinema();

    public void setCinema(Cinema cinema) { // Setting the cinema-object
        MainController.cinema = cinema;
    }

    /** Holder of a switchable vista. */
    @FXML
    private StackPane viewHolder;

    @FXML
    public Label headerLabelEdit;

    public void initialize() {
        headerLabelEdit.setText(GuiData.getViewTitle());
    }


    public void pressLogOut(ActionEvent event) throws IOException {
        StageSceneNavigator.loginStage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void pressHome(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Films");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_FILM);
    }

    public void pressProfile(ActionEvent event) throws IOException {
        GuiData.setViewTitle("User Profile");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_PROFILE);
    }

    public void pressShowings(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Showings");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SHOWINGS);
    }

    public void pressEditUserProfiles(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Edit Profiles");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_EDIT_USER_PROFILE);
    }

    public void pressScreens(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Screens");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SCREENS);
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