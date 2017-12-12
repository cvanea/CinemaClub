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
    public static Button selectedSeat = null;
    public static ArrayList<Button> selectedSeatMulti = new ArrayList<>();
    public static Showing showing;
    private static String seatRow;
    private static int seatNumber;
    private static int numberOfRows;
    private static int seatsPerRow;
    private static String seatBooked;

    public static ArrayList<Button> getSelectedSeatMulti() {
        return selectedSeatMulti;
    }

    public static void setSelectedSeatMulti(ArrayList<Button> selectedSeatMulti) {
        GuiData.selectedSeatMulti = selectedSeatMulti;
    }

    public static String getSuccessMessage() {
        return successMessage;
    }

    public static void setSuccessMessage(String newSuccessMessage) {
        successMessage = newSuccessMessage;
    }

    public static Film getFilm() {
        return film;
    }

    public static void setFilm(Film film) {
        GuiData.film = film;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        GuiData.date = date;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        GuiData.time = time;
    }

    public static void setNumberOfRows(int numberOfRows) {
        GuiData.numberOfRows = numberOfRows;
    }

    public static void setSeatsPerRow(int seatsPerRow) {
        GuiData.seatsPerRow = seatsPerRow;
    }

    /*
     Methods For Creating Cinema
     */

    private static void splitSeat(Button button) {
        String seat = button.getAccessibleText();
        String[] splitSeat = seat.split("(?!^)", 2);
        seatRow = splitSeat[0];
        seatNumber = Integer.parseInt(splitSeat[1]);
    }

    private static Boolean isSeatTaken() {
        try {
            return showing.isSeatTaken(seatRow, seatNumber);
        } catch (SeatNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void seatSelectSingle(Button button) {
        Image imgSeatWhite = new Image("/seatW32.png");
        Image imgSeatYellow = new Image("/seatY32.png");
        if (selectedSeat == null){
            button.setGraphic(new ImageView(imgSeatYellow));
            selectedSeat = button;
        } else if (selectedSeat == button){
            selectedSeat.setGraphic(new ImageView(imgSeatWhite));
            selectedSeat = null;
        } else {
            button.setGraphic(new ImageView(imgSeatYellow));
            selectedSeat.setGraphic(new ImageView(imgSeatWhite));
            selectedSeat = button;
        }
    }

    private static void seatSelectorMulti(Button button) {
        Image imgSeatWhite = new Image("/seatW32.png");
        Image imgSeatYellow = new Image("/seatY32.png");
        button.setGraphic(new ImageView(imgSeatYellow));

         if (selectedSeatMulti.contains(button)){
            button.setGraphic(new ImageView(imgSeatWhite));
            int index = selectedSeatMulti.indexOf(button);
            selectedSeatMulti.remove(index);
        } else {
            selectedSeatMulti.add(button);
        }
    }

    public static void setupSeatButtons(GridPane gridSeats, int gridWidth, int gridHeight, String useCase) {
        int rowHeight = gridHeight / (numberOfRows);
        int columnWidth = gridWidth / (seatsPerRow);
        Image imgSeat;
        for (int r = 0; r < numberOfRows+1; r++) {
            RowConstraints row;
                if (r == 0) {
                     row = new RowConstraints(12);
                }else{
                    row = new RowConstraints(rowHeight);
                }
                gridSeats.getRowConstraints().add(row);

            for (int c = 0; c < seatsPerRow+1; c++) {
                ColumnConstraints column;

                if (c == 0) {
                    column = new ColumnConstraints(15);
                } else{
                    column = new ColumnConstraints(columnWidth);
                }

                gridSeats.getColumnConstraints().add(column);

                if (r == 0 && c != 0) {
                    Label seatLabel = new Label(String.valueOf(c));
                    GridPane.setHalignment(seatLabel, HPos.CENTER);
                    gridSeats.add(seatLabel, c, r);
                } else if (c == 0 && r != 0) {
                    String letter = getCharForNumber(r);
                    Label seatLabel = new Label(String.valueOf(letter));
                    GridPane.setHalignment(seatLabel, HPos.RIGHT);
                    gridSeats.add(seatLabel, c, r);
                } else if (c > 0 && r > 0){
                    String letter = getCharForNumber(r);
                    String seatName = letter + c;
                    Button button = new Button();
                    button.setAccessibleText(String.valueOf(seatName));
                    splitSeat(button);

                    imgSeat = new Image("/seatW32.png");

                    if (!useCase.equals("ScreenView")) {

                        if (isSeatTaken()) {
                            imgSeat = new Image("/seatR32.png");
                        }

                        button.setOnAction((ActionEvent e) -> {
                            Object object = e.getSource();
                            Button b = null;

                            if (object instanceof Button) {
                                b = (Button) object;
                            }

                            if (!useCase.equals("staff")) {
                                splitSeat(b);

                                if (isSeatTaken().equals(false)) {
//                                    seatSelectSingle(b);
//                                    selectedSeatMulti.add(button);
//                                    System.out.println(selectedSeatMulti.toString());
                                    seatSelectorMulti(b);
                                }
                            }
                        });
                    }

                    button.setGraphic(new ImageView(imgSeat));
                    button.setStyle("-fx-background-color: white");

                    GridPane.setHalignment(button, HPos.CENTER);
                    gridSeats.add(button, c, r);
                }
            }
        }
//                gridSeats.gridLinesVisibleProperty().set(true);
    }

    public static ObservableList<String> getFilmList(Cinema cinema){
        ArrayList<Film> films = cinema.displayAllFilms();
        ArrayList<String> filmTitles = new ArrayList<>();
        for (Film film : films) {
            filmTitles.add(film.getTitle());
        }
        ObservableList<String> data = FXCollections.observableArrayList(filmTitles);
        return data;
    }

    private  static String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }

    public static int getSeatNumber() {
        return seatNumber;
    }

    public static String getSeatRow() {
        return seatRow;
    }

    public static Showing getShowing() {
        return showing;
    }

    public static void setShowing(Showing showing) {
        GuiData.showing = showing;
    }

    public static String getViewTitle() {
        return viewTitle;
    }
    public static void setViewTitle(String viewTitle) {
        GuiData.viewTitle = viewTitle;
    }

    public static String getSeatBooked() {
        return seatBooked;
    }

    public static void setSeatBooked(String seatBooked) {
        GuiData.seatBooked = seatBooked;
    }
}
