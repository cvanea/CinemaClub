package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.CustomerGui.CustomerMainController;
import cinemaclub.guiMain.GuiData;
import cinemaclub.user.Booking;
import exceptions.NoBookingsException;
import exceptions.NoFutureBookingsException;
import exceptions.SeatNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StaffScreenController extends StaffMainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label timeText;
    @FXML GridPane gridSeats;
    @FXML TableView<Booking> userTable;
    @FXML TableColumn<Booking, String> userName;
    @FXML TableColumn <Booking, String> firstName;
    @FXML TableColumn <Booking, String> lastName;
    @FXML TableColumn <Booking, String> seatName;


    private Showing showing;

    public void pressExport(ActionEvent actionEvent) {

    }

    public void pressDelete(ActionEvent actionEvent) {
//        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    public void fillUserTable() {
//        try {
//            ArrayList<Booking> bookings = cinema.getFutureBookingsHistory();
//            ObservableList<Booking> bookingObservableList = FXCollections.observableArrayList(bookings);
//            userName.setCellValueFactory(new PropertyValueFactory<>("User"));
//            firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
//            lastName.setCellValueFactory(new PropertyValueFactory<>("Surname"));
//            seatName.setCellValueFactory(new PropertyValueFactory<>("Seat"));
//            userTable.setItems(bookingObservableList);
//        } catch (NoBookingsException | NoFutureBookingsException e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setFilmInfo();
        showing = GuiData.getShowing();
        GuiData.setupSeatButtons(gridSeats, 780,500, "staff" );
        fillUserTable();
    }

    public void setFilmInfo(){
        titleText.setText(GuiData.getFilm().getTitle());
        descriptionText.setText(GuiData.getFilm().getDescription());
        runtimeText.setText(GuiData.getFilm().getRunTime());
        timeText.setText(GuiData.getTime());
        dateText.setText(GuiData.getDate());
        Image img = new Image(GuiData.getFilm().getImagePath());
        imageBox.setImage(img);
    }

}
