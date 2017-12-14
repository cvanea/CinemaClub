package cinemaclub.guiMain.LoginGui;

import cinemaclub.cinema.Cinema;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Main controller class for the entire layout.
 */
public class LoginMainController {

    /** Holder of a switchable vista. */
    @FXML private StackPane viewHolder;

    @FXML public ImageView imageView;

    static Cinema cinema = new Cinema();

    public void initialize() {
    }


    public static Cinema getCinema() {
        return cinema;
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