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

    public void initialize() {
        headerLabelEdit.setText(GuiData.getViewTitle());
    }

    public void pressLogOut(ActionEvent event) throws IOException {
        StageSceneNavigator.loginStage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void pressProfile(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Profile");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);

    }

    public void pressHome(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Book Film By Date");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
    }

    public void pressFilm(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Film Browser");
        headerLabelEdit.setText(GuiData.getViewTitle());
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_FILM_VIEW);
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