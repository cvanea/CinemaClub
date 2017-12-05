package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.gui.LoginGui;
import cinemaclub.guiMain.LoginGui.LoginMainController;
import cinemaclub.guiMain.LoginGui.LoginUserController;
import cinemaclub.guiMain.StageSceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

/**
 * Main controller class for the entire layout.
 */
public class CustomerMainController {

    static Cinema cinema = LoginUserController.getCinema();

    public void setCinema(Cinema cinema) { // Setting the cinema-object
        this.cinema = cinema;
    }

    /** Holder of a switchable vista. */
    @FXML
    private StackPane viewHolder;

    public void pressLogOut(ActionEvent event) throws IOException {
        StageSceneNavigator.loginStage();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
    public void pressProfile(ActionEvent event) throws IOException {
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE);

    }
    public void pressHome(ActionEvent event) throws IOException {
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
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