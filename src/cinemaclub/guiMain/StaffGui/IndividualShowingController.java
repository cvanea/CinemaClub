package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.GuiData;
import cinemaclub.user.Booking;
import cinemaclub.user.Customer;
import cinemaclub.user.User;
import exceptions.NoBookingsException;
import exceptions.NoFutureBookingsException;
import exceptions.NotAFutureBookingException;
import exceptions.SeatNotFoundException;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class IndividualShowingController extends MainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label timeText;
    @FXML Label screensText;
    @FXML Label errorLabel;
    @FXML GridPane gridSeats;
    @FXML TableView<BookingUserInfo> userTable;
    @FXML TableColumn<BookingUserInfo, String> username;
    @FXML TableColumn <BookingUserInfo, String> firstName;
    @FXML TableColumn <BookingUserInfo, String> lastName;
    @FXML TableColumn <BookingUserInfo, String> seat;

    private Showing showing;
    private Customer customer;
    private Booking chosenBooking;

    /**
     * Sets the film info pane and fills the view with seats in a grid.
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showing = GuiData.getShowing();
        setFilmInfo();
        GuiData.setNumberOfRows(showing.getScreen().getNumberRow());
        GuiData.setSeatsPerRow(showing.getScreen().getSeatsPerRow());
        GuiData.setupSeatButtons(gridSeats, 780,450, "staff");
        fillBookingsTable();
    }

    /**
     * User mouse click event when a booking is selected in the table
     * Gets the customer and the chosen booking of that field
     * @param event click a row in the bookings table
     */
    public void userMouseClick(MouseEvent event) {
        BookingUserInfo chosenUser = userTable.getSelectionModel().getSelectedItem();
        if(chosenUser != null) {
            User user = cinema.getUser(chosenUser.getUsername());
            if (user instanceof Customer) {
                customer = (Customer) user;
                chosenBooking = customer.getBooking(showing, chosenUser.getSeat());
            }
        }
    }

    /**
     * Deletes a selected booking from the database.
     *  Updates the gird of seats and table
     *  Passes delete to the database.
     * @param actionEvent press the delete button.
     */
    public void pressDelete(ActionEvent actionEvent) {
        try {
            cinema.deleteFutureBooking(customer, chosenBooking);
            fillBookingsTable();
            deleteGrid();
            GuiData.setupSeatButtons(gridSeats, 780,450, "staff");
            errorLabel.setText("Booking deleted");
            errorLabel.setStyle("-fx-text-fill: darkgreen");
        } catch(NoBookingsException | NotAFutureBookingException | NoFutureBookingsException | SeatNotFoundException e){
            errorLabel.setText(e.getMessage());
            errorLabel.setStyle("-fx-text-fill: red");
        }
    }

    /**
     * Fills the booking table with a showing bookings.
     */
    private void fillBookingsTable() {
        ObservableList <BookingUserInfo> data = FXCollections.observableArrayList();

        Map<String, String> takenSeats = showing.getTakenSeats();

        for (Map.Entry<String, String> entry : takenSeats.entrySet()) {
            String username = entry.getValue();
            User user = cinema.getUser(username);

            if (user instanceof Customer) {
                Customer customer = (Customer) user;

                String seat = entry.getKey();
                Booking booking = customer.getBooking(showing, seat);
                data.add(new BookingUserInfo(customer, booking));
            }
        }

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("surname"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        userTable.setItems(data);
    }

    /**
     * Sets the film information pane with film information.
     */
    private void setFilmInfo(){
        titleText.setText(showing.getFilm().getTitle());
        descriptionText.setText(showing.getFilm().getDescription());
        runtimeText.setText(showing.getFilm().getRuntime());
        timeText.setText(showing.getTime());
        dateText.setText(showing.getDate());
        screensText.setText(showing.getScreenNumber().toString());

        try {
            Image img = new Image(new FileInputStream("Images" + showing.getFilm().getImagePath()));
            imageBox.setImage(img);
        } catch (FileNotFoundException e) {
            errorLabel.setText(e.getMessage());
        }

    }

    /**
     * Clears the grid for when the seats view is updated
     * Prevents styling issues caused by a moving grid
     */
    private void deleteGrid(){
        while(gridSeats.getRowConstraints().size() > 0){
            gridSeats.getRowConstraints().remove(0);
        }
        while(gridSeats.getColumnConstraints().size() > 0){
            gridSeats.getColumnConstraints().remove(0);
        }
        gridSeats.getChildren().clear();
    }

    /**
     * Helper class of booking info of a showing to populate table.
     */
    public class BookingUserInfo {

        String username;
        String firstName;
        String surname;
        String seat;

        /**
         * Sets the Booking information fields.
         * @param customer customer who made the booking
         * @param booking the corresponding booking made
         */
        private BookingUserInfo(Customer customer, Booking booking) {
            this.username = customer.getUsername();
            this.firstName = customer.getFirstName();
            this.surname = customer.getSurname();
            this.seat = customer.getSeat(booking);
        }

        /**
         * Gets the username.
         * @return the username
         */
        public String getUsername() {
            return username;
        }

        /**
         * Gets the first name.
         * @return the first name
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Gets the surname.
         * @return the surname
         */
        public String getSurname() {
            return surname;
        }

        /**
         * Gets the seat.
         * @return the seat
         */
        public String getSeat() {
            return seat;
        }
    }
}
