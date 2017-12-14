package cinemaclub.guiMain;

import cinemaclub.cinema.Cinema;
import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import exceptions.SeatNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class GuiData {

    private static String successMessage;
    private static String viewTitle;
    private static Film film;
    private static String date;
    private static String time;
    private static Button selectedSeat = null;
    private static ArrayList<Button> selectedSeatMulti = new ArrayList<>();
    public static Showing showing;
    private static String seatRow;
    private static int seatNumber;
    private static int numberOfRows;
    private static int seatsPerRow;
    private static String seatBooked;

    /**
     * Getter for get selected multi seats button lists.
     * @return
     */
    public static ArrayList<Button> getSelectedSeatMulti() {
        return selectedSeatMulti;
    }


    /**
     * Setter of the seat Arraylist of buttons.
     * @param selectedSeatMulti
     */
    public static void setSelectedSeatMulti(ArrayList<Button> selectedSeatMulti) {
        GuiData.selectedSeatMulti = selectedSeatMulti;
    }

    /**
     * Success message getter.
     * @return success message
     */
    public static String getSuccessMessage() {
        return successMessage;
    }

    /**
     * Success message setter.
     * @param newSuccessMessage
     */
    public static void setSuccessMessage(String newSuccessMessage) {
        successMessage = newSuccessMessage;
    }

    /**
     * Film getter.
     * @return film
     */
    public static Film getFilm() {
        return film;
    }

    /**
     * Film setter.
     * @param film
     */
    public static void setFilm(Film film) {
        GuiData.film = film;
    }

    /**
     * Date getter.
     * @return date string
     */
    public static String getDate() {
        return date;
    }

    /**
     * Date setter
     * @param date
     */
    public static void setDate(String date) {
        GuiData.date = date;
    }

    /**
     * Time getter
     * @return time string
     */
    public static String getTime() {
        return time;
    }

    /**
     * Time setter
     * @param time string
     */
    public static void setTime(String time) {
        GuiData.time = time;
    }

    /**
     * Sets the number of of the screen.
     * @param numberOfRows
     */
    public static void setNumberOfRows(int numberOfRows) {
        GuiData.numberOfRows = numberOfRows;
    }

    /**
     * Sets the number of seats per row.
     * @param seatsPerRow
     */
    public static void setSeatsPerRow(int seatsPerRow) {
        GuiData.seatsPerRow = seatsPerRow;
    }

    /**
     * Gets the number of seats.
     * @return
     */
    public static int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Gets the Seat row.
     * @return
     */
    public static String getSeatRow() {
        return seatRow;
    }

    /**
     * Gets the current showing.
     * @return
     */
    public static Showing getShowing() {
        return showing;
    }

    /**
     * Sets the current showing.
     * @param showing
     */
    public static void setShowing(Showing showing) {
        GuiData.showing = showing;
    }

    /**
     * Gets the view title
     * @return
     */
    public static String getViewTitle() {
        return viewTitle;
    }

    /**
     * Sets the view title
     * @param viewTitle
     */
    public static void setViewTitle(String viewTitle) {
        GuiData.viewTitle = viewTitle;
    }

    /**
     * Gets the total number of seats booked
     * @return
     */
    public static String getSeatBooked() {
        return seatBooked;
    }

    /**
     * Sets the total number of seats booked.
     * @param seatBooked
     */
    public static void setSeatBooked(String seatBooked) {
        GuiData.seatBooked = seatBooked;
    }

    /**
     * Methods For Creating Showings View to display seats in a graphical manner and add
     * functionality to the buttons.
     */


    /**
     * Splits the text e.g. A4 into a character and a number.
     * Gets a accessible text off a button
     * @param button takes a control button
     */
    private static void splitSeat(Button button) {
        String seat = button.getAccessibleText();
        String[] splitSeat = seat.split("(?!^)", 2);
        seatRow = splitSeat[0];
        seatNumber = Integer.parseInt(splitSeat[1]);
    }

    /**
     * Checks whether the seat is taken .
     * throws seat not found if no seat exists
     * @return true if the seat is taken
     */
    private static Boolean isSeatTaken() {
        try {
            return showing.isSeatTaken(seatRow, seatNumber);
        } catch (SeatNotFoundException e) {
            return false;
        }
    }

    /**
     * Sets up the seat view for booked seats, screen and individual showings.
     * @param gridSeats the grid pane control that is being populated
     * @param gridWidth  the width of the grid pane
     * @param gridHeight  the height of the grid pane
     * @param useCase which functionality to be added to the buttons
     */
    public static void setupSeatButtons(GridPane gridSeats, int gridWidth, int gridHeight, String useCase) {
        int rowHeight = gridHeight / (numberOfRows); //calculates the average row height
        int columnWidth = gridWidth / (seatsPerRow); //calculates the average column width
        Image imgSeat;
        for (int r = 0; r < numberOfRows+1; r++) {
            RowConstraints row;
                if (r == 0) {
                     row = new RowConstraints(12); // sets top row to fixed height
                }else{
                    row = new RowConstraints(rowHeight); // auto sizes each row to average
                }
                gridSeats.getRowConstraints().add(row); // adds new row

            for (int c = 0; c < seatsPerRow+1; c++) {
                ColumnConstraints column;

                if (c == 0) {
                    column = new ColumnConstraints(15); // sets first column to set width
                } else{
                    column = new ColumnConstraints(columnWidth); // auto sizes each column to average
                }

                gridSeats.getColumnConstraints().add(column); // adds colunm

                if (r == 0 && c != 0) {
                    Label seatLabel = new Label(String.valueOf(c));
                    GridPane.setHalignment(seatLabel, HPos.CENTER);
                    gridSeats.add(seatLabel, c, r); // adds letter label to each row head
                } else if (c == 0 && r != 0) {
                    String letter = getCharForNumber(r);
                    Label seatLabel = new Label(String.valueOf(letter));
                    GridPane.setHalignment(seatLabel, HPos.RIGHT);
                    gridSeats.add(seatLabel, c, r); // adds the correct column number
                } else if (c > 0 && r > 0){
                    String letter = getCharForNumber(r);
                    String seatName = letter + c;
                    Button button = new Button();
                    button.setAccessibleText(String.valueOf(seatName));
                    splitSeat(button); // creates button with accessible text
                    imgSeat = new Image("/seatW32.png");

                    if (!useCase.equals("ScreenView")) {

                        if (isSeatTaken()) {
                            imgSeat = new Image("/seatR32.png");
                        }

                        button.setOnAction((ActionEvent e) -> { // assigns action to each button
                            Object object = e.getSource();
                            Button b = null;

                            if (object instanceof Button) {
                                b = (Button) object; //creates button object from object
                            }

                            if (!useCase.equals("staff")) {
                                splitSeat(b); //finds the seat text

                                if (isSeatTaken().equals(false)) { //checks if seat is taken
                                    seatSelectorMulti(b); //
                                }
                            }
                        });
                    }
                    button.setGraphic(new ImageView(imgSeat));
                    button.setStyle("-fx-background-color: white"); //assigns button colour
                    GridPane.setHalignment(button, HPos.CENTER);
                    gridSeats.add(button, c, r); // places button i pane
                }
            }
        }
    }

    /**
     * Adds seat to to the multi seat array list and removes and adds colour to
     * show seat is selected
     * @param button takes seat button
     */
    private static void seatSelectorMulti(Button button) {
        Image imgSeatWhite = new Image("/seatW32.png");
        Image imgSeatYellow = new Image("/seatY32.png");
        button.setGraphic(new ImageView(imgSeatYellow));
        if(!isSeatTaken()) {
            if (selectedSeatMulti.contains(button)) {
                button.setGraphic(new ImageView(imgSeatWhite));
                int index = selectedSeatMulti.indexOf(button);
                selectedSeatMulti.remove(index);
            } else {
                selectedSeatMulti.add(button);
            }
        }
    }

    /**
     * Creates an observable array list of films to populate list views.
     * @param cinema takes the class cinema
     * @return an observable array list of films
     */
    public static ObservableList<String> getFilmList(Cinema cinema){
        ArrayList<Film> films = cinema.displayAllFilms();
        ArrayList<String> filmTitles = new ArrayList<>();
        for (Film film : films) {
            filmTitles.add(film.getTitle());
        }
        ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
        return data;
    }

    /**
     * Gets the corresponding letter for a number
     * @param i integer number
     * @return string with letter
     */
    private  static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

}
