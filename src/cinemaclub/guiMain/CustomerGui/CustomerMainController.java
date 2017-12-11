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
 * Main controller class for the entire layout.
 */
public class CustomerMainController {

    static Cinema cinema = LoginMainController.getCinema();

    public void setCinema(Cinema cinema) { // Setting the cinema-object
        CustomerMainController.cinema = cinema;
    }

    /** Holder of a switchable vista. */
    @FXML
    private StackPane viewHolder;
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
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);

    }
    public void pressHome(ActionEvent event) throws IOException {
        GuiData.setViewTitle("Book Film");
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
    }

    public void pressFilm(ActionEvent event) throws IOException {
//        GuiData.setViewTitle("Book Film");
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