package cinemaclub.guitest;

import cinemaclub.gui.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Main controller class for the entire layout.
 */
public class MainController {

    /** Holder of a switchable vista. */
    @FXML
    private StackPane vistaHolder;

    public void pressTest(ActionEvent event) {
        System.out.println("works");
//        Main.setPaneCinema(1);
    }

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */

    public void setVista(Node node) {
        vistaHolder.getChildren().setAll(node);
    }

}