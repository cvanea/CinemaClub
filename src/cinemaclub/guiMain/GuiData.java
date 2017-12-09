package cinemaclub.guiMain;

import cinemaclub.cinema.Film;
import cinemaclub.cinema.Showing;
import exceptions.SeatNotFoundException;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GuiData {

    private static String successMessage;
    private static Film film;
    private static String date;
    private static String time;
    public static Button selectedSeat = null;
    private static Showing showing;
    private static String seatRow;
    private static int seatNumber;


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

    /*
     Methods For Creating Cinema
     */

    public static void splitSeat(Button button) {
        String seat = button.getText();
        String[] splitSeat = seat.split("(?!^)", 2);
        seatRow = splitSeat[0];
        seatNumber = Integer.parseInt(splitSeat[1]);
    }

    public static Boolean isSeatTaken() {
        try {
            return showing.isSeatTaken(seatRow, seatNumber);
        } catch (SeatNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void seatSelect(Button button) {
        Image imgSeatWhite = new Image("/seatW32.png");
        Image imgSeatYellow = new Image("/seatY32.png");
        if(selectedSeat == null){
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

    public static void setupSeatButtons(GridPane gridSeats, int gridWidth, int gridHeight, String method ) {
        int numRows = showing.getScreen().getNumberRow();
        int numCols = showing.getScreen().getSeatsPerRow();
        int rowHeight = gridHeight / numRows;
        int columnWidth = gridWidth / numCols;
        Image imgSeat;

        for (int r = 1; r < numRows + 1; r++) {
            RowConstraints row = new RowConstraints(rowHeight);
            gridSeats.getRowConstraints().add(row);

            for (int c = 1; c < numCols + 1; c++) {
                String letter = getCharForNumber(r);
                String seatName = letter + c;
                ColumnConstraints column = new ColumnConstraints(columnWidth);
                gridSeats.getColumnConstraints().add(column);
                Button button = new Button(String.valueOf(seatName));
                splitSeat(button);
                if (isSeatTaken()) {
                    imgSeat = new Image("/seatR32.png");
                } else {
                    imgSeat = new Image("/seatW32.png");
                }

                button.setGraphic(new ImageView(imgSeat));

                button.setOnAction((ActionEvent e) -> {
                    Object object = e.getSource();
                    Button b = null;

                    if (object instanceof Button) {
                        b = (Button) object;
                    }

                    if(!method.equals("staff")) {
                        splitSeat(b);

                        if (isSeatTaken().equals(false)) {
                            seatSelect(b);
                        }
                    }
                });
                GridPane.setHalignment(button, HPos.CENTER);
                gridSeats.add(button, c - 1, r - 1);
            }
        }
        //        gridSeats.gridLinesVisibleProperty().set(true);
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
}
