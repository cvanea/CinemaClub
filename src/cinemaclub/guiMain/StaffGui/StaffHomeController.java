package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.CustomerGui.CustomerMainController;
import cinemaclub.guiMain.StageSceneNavigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffHomeController extends CustomerMainController implements Initializable {

//    @FXML ImageView imageBox;
//    @FXML Label titleText;
//    @FXML Label descriptionText;

    public void selectDate(ActionEvent actionEvent) {

    }

    public void pressPickSeat(ActionEvent actionEvent) {
//        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Image img = new Image("/walle.jpg");
//        imageBox.setImage(img);
    }

    public void setFilmInfo(Cinema cinema){
//        titleText.setText("Title");
//        descriptionText.setText("Description");
//        Image img = new Image("/walle.jpg");
//        imageBox.setImage(img);
    }
}
