package cinemaclub.guiMain.CustomerGui;

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
 * Main controller class for the Customer Root layout.
 */
public class CustomerMainController {

    static Cinema cinema = LoginMainController.getCinema();

    public void setCinema(Cinema cinema) { // Setting the cinema-object
        CustomerMainController.cinema = cinema;
    }

    /** Stackpane holder for the switchable views */
    @FXML private StackPane viewHolder;
    @FXML public Label headerLabelEdit;

    /**
     * Intiialises the view withe header text
     */
    public void initialize() {
        headerLabelEdit.setText(GuiData.getViewTitle());
    }

    /**
     * Log out button to go back to the login screen
     * @param event press the logo out button
     * @throws IOException  from stage navigator
     */
    public void pressLogOut(ActionEvent event) throws IOException {
        StageSceneNavigator.loginStage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Profile view button to enter the profile pane
     * Sets header text and switches to the profile
     * @param event press the profile button
     * @throws IOException from stage navigator
     */
    public void pressProfile(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Profile");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);

    }

    /**
     * Book film by date view button to enter the film by date pane
     * Sets header text and switches to the film by date view
     * @param event press the film by date button
     * @throws IOException from stage navigator
     */
    public void pressFilmByDate(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Book Film By Date");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
    }

    /**
     * Film browser view button to enter the film browser pane
     * Sets header text and switches to the film browser view
     * @param event press the film browser button
     * @throws IOException from stage navigator
     */
    public void pressFilm(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Film Browser");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_FILM_VIEW);
    }

    /**
     * Switches the view displayed with a new view
     *
     * @param node the view node to be swapped.
     */

    public void setView(Node node) {
        viewHolder.getChildren().setAll(node);
    }

}