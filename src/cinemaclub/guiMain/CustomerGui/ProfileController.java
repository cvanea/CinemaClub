package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.StageSceneNavigator;
import cinemaclub.user.Booking;
import exceptions.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProfileController extends CustomerMainController implements Initializable {

    @FXML TextField username;
    @FXML TextField password;
    @FXML TextField email;
    @FXML TextField firstName;
    @FXML TextField surname;
    @FXML Label errorLabel;
    @FXML TableView<Booking> futureTable;
    @FXML TableColumn <Booking, String> filmTable;
    @FXML TableColumn <Booking, String> dateTable;
    @FXML TableColumn <Booking, String> timeTable;
    @FXML TableColumn <Booking, String> seatTable;
    @FXML TableView<Booking> pastTable;
    @FXML TableColumn <Booking, String> filmTableP;
    @FXML TableColumn <Booking, String> dateTableP;
    @FXML TableColumn <Booking, String> timeTableP;
    @FXML TableColumn <Booking, String> seatTableP;

    private Booking chosenBooking;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(cinema.getProfileDetails().getUsername());
        email.setText(cinema.getCurrentUser().getEmail());
        password.setText(cinema.getCurrentUser().getPassword());
        firstName.setText(cinema.getProfileDetails().getFirstName());
        surname.setText(cinema.getProfileDetails().getSurname());
        fillFutureBookings();
        fillPastBookings();
    }

    public void cancelBooking(ActionEvent event){
        try {
            cinema.deleteFutureBooking(chosenBooking);
            fillFutureBookings();
        } catch(NoBookingsException | NotAFutureBookingException | NoFutureBookingsException | SeatNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void futureMouseClick(MouseEvent event){
        chosenBooking = futureTable.getSelectionModel().getSelectedItem();
    }

    public void setProfileText(ActionEvent event) {
        try {
            if(!username.getText().equals(cinema.getProfileDetails().getUsername())) {
                cinema.setUsername(username.getText());
            }
            cinema.setUserEmail(email.getText());
            cinema.setUserPassword(password.getText());
            cinema.setUserFirstName(firstName.getText());
            cinema.setUserSurname(surname.getText());
            errorLabel.setText("Profile Updated");
            errorLabel.setStyle("-fx-text-fill: darkgreen");
        } catch (UsernameTakenException e) {
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    private void fillFutureBookings() {
        try {
            futureTable.getItems().clear();
            ArrayList<Booking> bookings = cinema.getFutureBookingsHistory();
            ObservableList<Booking> bookingObservableList = FXCollections.observableArrayList(bookings);
            filmTable.setCellValueFactory(new PropertyValueFactory<>("Title"));
            dateTable.setCellValueFactory(new PropertyValueFactory<>("Date"));
            timeTable.setCellValueFactory(new PropertyValueFactory<>("Time"));
            seatTable.setCellValueFactory(new PropertyValueFactory<>("Seat"));
            futureTable.setItems(bookingObservableList);

        } catch (NoBookingsException | NoFutureBookingsException e) {
            System.out.println(e.getMessage());
        }
    }

    private void fillPastBookings() {
        try {
            ArrayList<Booking> bookings = cinema.getPastBookingsHistory();
            ObservableList<Booking> bookingObservableList = FXCollections.observableArrayList(bookings);
            filmTableP.setCellValueFactory(new PropertyValueFactory<>("Title"));
            dateTableP.setCellValueFactory(new PropertyValueFactory<>("Date"));
            timeTableP.setCellValueFactory(new PropertyValueFactory<>("Time"));
            seatTableP.setCellValueFactory(new PropertyValueFactory<>("Seat"));
            pastTable.setItems(bookingObservableList);
            System.out.println("empty bookings");
        } catch (NoBookingsException | NoPastBookingsException e) {
            System.out.println(e.getMessage());
        }
    }
}
