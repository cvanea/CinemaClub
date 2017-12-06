package cinemaclub.guiMain.LoginGui;

import cinemaclub.cinema.Cinema;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Main controller class for the entire layout.
 */
public class LoginMainController {

    /** Holder of a switchable vista. */
    @FXML private StackPane viewHolder;

    static Cinema cinema = new Cinema();

    public static Cinema getCinema() {
        return cinema;
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