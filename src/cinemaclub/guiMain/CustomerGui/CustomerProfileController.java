package cinemaclub.guiMain.CustomerGui;

import cinemaclub.cinema.Film;
import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import cinemaclub.user.Booking;
import exceptions.NoBookingsException;
import exceptions.NoFutureBookingsException;
import exceptions.PastDateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CustomerProfileController extends CustomerMainController {

    @FXML Label nameBox;
    @FXML Label emailBox;
    @FXML Label passwordBox;
    @FXML Label firstNameBox;
    @FXML Label lastNameBox;
    @FXML ListView futureList;
    @FXML ListView pastList;

    @FXML private void initialize() {
        nameBox.setText(cinema.getProfileDetails().getUsername());
        emailBox.setText(cinema.getCurrentUser().getEmail());
        passwordBox.setText(cinema.getCurrentUser().getPassword());
        firstNameBox.setText(cinema.getProfileDetails().getFirstName());
        lastNameBox.setText(cinema.getProfileDetails().getSurname());
        fillFutureBookings();
        fillPastBookings();
    }

    public void cancelBooking(ActionEvent event){

    }

    public void futureMouseClick(ActionEvent event){

    }

    public void setProfileText(ActionEvent event) {
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_PROFILE_EDIT);
    }

    public void fillFutureBookings() {
        try {
            ArrayList<Booking> bookings = cinema.getFutureBookingsHistory();
            ArrayList<String> filmTitles = new ArrayList<>();

            for (Booking booking : bookings) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(booking.getFilm().getTitle()).append(" | ").append(booking.getDate()).append(" | ").append(booking.getTime()).append(" | ").append(booking.getSeat());
                filmTitles.add(stringBuilder.toString());
//                System.out.println(stringBuilder.toString());
            }

            ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
            futureList.setItems(data);
        } catch (NoBookingsException | NoFutureBookingsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillPastBookings() {
        try {
            ArrayList<Booking> bookings = cinema.getPastBookingsHistory();
            ArrayList<String> filmTitles = new ArrayList<>();

            for (Booking booking : bookings) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(booking.getFilm().getTitle()).append(" | ").append(booking.getDate()).append(" | ").append(booking.getTime()).append(" | ").append(booking.getSeat());
                filmTitles.add(stringBuilder.toString());
//                System.out.println(stringBuilder.toString());
            }
            ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
            pastList.setItems(data);
            System.out.println("empty bookings");
        } catch (NoBookingsException e) {
            System.out.println(e.getMessage());
        }
    }



}
