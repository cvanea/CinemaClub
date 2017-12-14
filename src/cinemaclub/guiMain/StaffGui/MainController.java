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

    /**
     * Sets the title of the main view
     */
    public void initialize() {
        headerLabelEdit.setText(GuiData.getViewTitle());
    }


    /**
     * Goes to logout page.
     * @param event press logout
     * @throws IOException throws error message
     */
    public void pressLogOut(ActionEvent event) throws IOException {
        StageSceneNavigator.loginStage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Goes to film edit page.
     * @param event press film edit
     * @throws IOException throws error message
     */
    public void pressHome(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Films");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_FILM);
    }

    /**
     * Goes to profile page.
     * @param event press profile
     * @throws IOException throws error message
     */
    public void pressProfile(ActionEvent event) throws IOException {
        GuiData.setViewTitle("User Profile");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_PROFILE);
    }

    /**
     * Goes to Showings page.
     * @param event press Showings
     * @throws IOException throws error message
     */
    public void pressShowings(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Showings");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SHOWINGS);
    }

    /**
     * Goes to Edit user profile page.
     * @param event press Edit user profile
     * @throws IOException throws error message
     */
    public void pressEditUserProfiles(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Edit Profiles");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_EDIT_USER_PROFILE);
    }

    /**
     * Goes to Screens page.
     * @param event press screens
     * @throws IOException throws error message
     */
    public void pressScreens(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Screens");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_SCREENS);
    }

    /**
     * Replaces the view displayed in the view holder with a new view.
     * @param node the view node to be placed.
     */

    public void setView(Node node) {
        viewHolder.getChildren().setAll(node);
    }

}