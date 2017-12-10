package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
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
    @FXML TableView<BookingUserInfo> userTable;
    @FXML TableColumn<BookingUserInfo, String> username;
    @FXML TableColumn <BookingUserInfo, String> firstName;
    @FXML TableColumn <BookingUserInfo, String> lastName;
    @FXML TableColumn <BookingUserInfo, String> seat;

    private Showing showing;

    public void pressExport(ActionEvent actionEvent) {
        cinema.exportShowingsToCsv();
    }

    public void userMouseClick(MouseEvent event) {
        BookingUserInfo chosenUser = userTable.getSelectionModel().getSelectedItem();
    }

    public void pressDelete(ActionEvent actionEvent) {
//        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    private void fillUserTable() {
        ObservableList <BookingUserInfo> data = FXCollections.observableArrayList();

        Map<String, String> takenSeats = showing.getTakenSeats();

        for (Map.Entry<String, String> entry : takenSeats.entrySet()) {
            String username = entry.getValue();
            User user = cinema.getUser(username);

            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                Booking booking = customer.getBookingByShowing(showing);
                //TODO: FIX BOOKING to get all seats (Not just A1)
                data.add(new BookingUserInfo(customer, booking));
            }
        }

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("surname"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        userTable.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showing = GuiData.getShowing();
        setFilmInfo();
        GuiData.setupSeatButtons(gridSeats, 780,450, "staff");
        fillUserTable();
    }

    private void setFilmInfo(){
        titleText.setText(showing.getFilm().getTitle());
        descriptionText.setText(showing.getFilm().getDescription());
        runtimeText.setText(showing.getFilm().getRunTime());
        timeText.setText(showing.getTime());
        dateText.setText(showing.getDate());
        Image img = new Image(showing.getFilm().getImagePath());
        imageBox.setImage(img);
    }

    public class BookingUserInfo {

        String username;
        String firstName;
        String surname;
        String seat;

        private BookingUserInfo(Customer customer, Booking booking) {
            this.username = customer.getUsername();
            this.firstName = customer.getFirstName();
            this.surname = customer.getSurname();
            this.seat = customer.getSeat(booking);
        }

        public String getUsername() {
            return username;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getSurname() {
            return surname;
        }

        public String getSeat() {
            return seat;
        }
    }
}
