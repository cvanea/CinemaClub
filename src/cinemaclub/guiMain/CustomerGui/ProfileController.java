package cinemaclub.guiMain.CustomerGui;

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

/**
 * Controller for customer profile view.
 */
public class ProfileController extends CustomerMainController implements Initializable {

    @FXML TextField username;
    @FXML TextField password;
    @FXML TextField email;
    @FXML TextField firstName;
    @FXML TextField surname;
    @FXML Label errorLabel;
    @FXML Label errorLabel2;
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

    /**
     * Sets the user information text fields with the current user information on initialisation.
     * Fills the past and future bookings tables
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
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

    /**
     * Cancel booking button to delete a booking in the future.
     * Takes a booking clicked by the user in the future bookings list and passes this
     * to the delete bookings method
     * Throws green message if deletion is successful else it throws a red error message
     * @param event press the cancel button
     */
    public void cancelBooking(ActionEvent event){
        try {
            cinema.deleteFutureBooking(chosenBooking);
            fillFutureBookings();
            errorLabel2.setText("Booking deleted");
            errorLabel2.setStyle("-fx-text-fill: darkgreen");
        } catch(NoBookingsException | NotAFutureBookingException | NoFutureBookingsException | SeatNotFoundException e){
            errorLabel2.setText(e.getMessage());
            errorLabel2.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Click an event in the future bookings list.
     * @param event click a row in future bookings table
     */
    public void futureMouseClick(MouseEvent event){
        chosenBooking = futureTable.getSelectionModel().getSelectedItem();
    }

    /**
     * Updates the current user credentials in the databases.
     * On update profile press
     * Checks whether the username text field has been altered. If not it ignores this
     * field.
     * Checks whether the user name exists in the databases. If it does an error message is thrown
     * Sets all other fields to the edited values.
     * @param event press update profile
     */
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

    /**
     * Fills the future bookings table.
     * Clears the table of any previous values
     * get an array list of all the future bookings of the current user
     * Converts the array list to an observable list.
     * Sets the headings in the table. Fills the tables with future bookings
     * Throws an error if there are no bookings or no future bookings
     */
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
            errorLabel2.setText(e.getMessage());
            errorLabel2.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Fills the past bookings table.
     * Clears the table of any previous values
     * get an array list of all the past bookings of the current user
     * Converts the array list to an observable list.
     * Sets the headings in the table. Fills the tables with past bookings
     * Throws an error if there are no no past bookings
     */
    private void fillPastBookings() {
        try {
            ArrayList<Booking> bookings = cinema.getPastBookingsHistory();
            ObservableList<Booking> bookingObservableList = FXCollections.observableArrayList(bookings);
            filmTableP.setCellValueFactory(new PropertyValueFactory<>("Title"));
            dateTableP.setCellValueFactory(new PropertyValueFactory<>("Date"));
            timeTableP.setCellValueFactory(new PropertyValueFactory<>("Time"));
            seatTableP.setCellValueFactory(new PropertyValueFactory<>("Seat"));
            pastTable.setItems(bookingObservableList);
        } catch (NoBookingsException | NoPastBookingsException e) {
            errorLabel2.setText(e.getMessage());
            errorLabel2.setStyle("-fx-text-fill: red");
        }
    }
}
