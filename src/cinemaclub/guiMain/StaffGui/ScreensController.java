package cinemaclub.guiMain.StaffGui;

import cinemaclub.guiMain.GuiData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ScreensController extends MainController implements Initializable {

    @FXML GridPane gridSeats;

    public void pressDelete(ActionEvent actionEvent) {
//        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GuiData.setNumberOfRows(cinema.getScreen(1).getNumberRow());
        GuiData.setSeatsPerRow(cinema.getScreen(1).getSeatsPerRow());
        GuiData.setupSeatButtons(gridSeats, 1000,500, "ScreenView");
    }

}
