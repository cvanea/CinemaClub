package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Showing;
import cinemaclub.database.DataBase;
import cinemaclub.database.UserRepository;
import cinemaclub.guiMain.GuiData;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import cinemaclub.user.UserCredentials;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class StaffScreenController extends StaffMainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label timeText;
    @FXML GridPane gridSeats;
    @FXML TableView<BookedUser> userTable;
    @FXML TableColumn<BookedUser, String> userName;
    @FXML TableColumn <BookedUser, String> firstName;
    @FXML TableColumn <BookedUser, String> lastName;
    @FXML TableColumn <BookedUser, String> seatName;
//    @FXML TableView<Customer> userTable;
//    @FXML TableColumn<Customer, String> userName;
//    @FXML TableColumn <Customer, String> firstName;
//    @FXML TableColumn <Customer, String> lastName;
//    @FXML TableColumn <Customer, String> seatName;


    private Showing showing;

    public void pressExport(ActionEvent actionEvent) {
        cinema.exportShowingsToCsv();
    }

    public void userMouseClick(MouseEvent event) {
        BookedUser chosenUserBooking = userTable.getSelectionModel().getSelectedItem();
//        Booking booking = chosenUserBooking.getUserName()
    }

    public void pressDelete(ActionEvent actionEvent) {
//        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    public void fillUserTable() {
        ObservableList <BookedUser> data2 = FXCollections.observableArrayList();
        Map<String, String> bookedSeats = GuiData.showing.getTakenSeats();
        for(Map.Entry<String, String> entry: bookedSeats.entrySet()) {
            data2.add(new BookedUser(entry.getValue(), cinema.getUser(entry.getValue()).getFirstName(), cinema.getUser(entry.getValue()).getSurname(), entry.getKey()));
        }
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
            firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            lastName.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            seatName.setCellValueFactory(new PropertyValueFactory<>("Seat"));
            userTable.setItems(data2);
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
