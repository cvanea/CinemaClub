package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Cinema;
import cinemaclub.cinema.Showing;
import cinemaclub.guiMain.CustomerGui.CustomerMainController;
import cinemaclub.guiMain.GuiData;
import exceptions.SeatNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class StaffScreenController extends StaffMainController implements Initializable {

    @FXML ImageView imageBox;
    @FXML Label titleText;
    @FXML Label descriptionText;
    @FXML Label runtimeText;
    @FXML Label dateText;
    @FXML Label timeText;
    @FXML GridPane gridSeats;

    private String seatRow;
    private int seatNumber;
    private Button selectedSeat = null;
    private Showing showing;

    public void selectDate(ActionEvent actionEvent) {

    }

    public void pressPickSeat(ActionEvent actionEvent) {
//        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_BOOK_SEATS);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Image img = new Image("/walle.jpg");
//        imageBox.setImage(img);
        setFilmInfo();
        setupSeatButtons();
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

    public void splitSeat(Button button) {
        String seat = button.getText();
        String[] splitSeat = seat.split("(?!^)");
        seatRow = splitSeat[0];
        seatNumber = Integer.parseInt(splitSeat[1]);
    }

    public Boolean isSeatTaken() {
        //TODO: Get the correct screen for each movie
        try {
            if (showing.isSeatTaken(seatRow, seatNumber)) {
//                errorLabel.setText("Seat taken!");
                return true;
            } else{
                return false;
            }
        } catch (SeatNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Button seatSelect(Button button) {
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

        return selectedSeat;
    }

    public void setupSeatButtons() {

        //TODO: add get max number of rows and cols to model - replace value here
        int numRows = showing.getScreen().getNumberRow();
        int numCols = showing.getScreen().getSeatsPerRow();
        int rowHeight = 500 / numRows;
        int columnWidth = 780 / numCols;
        Image imgSeat;

        for (int r = 1; r < numRows + 1; r++) {
            RowConstraints row = new RowConstraints(rowHeight);
            gridSeats.getRowConstraints().add(row);

            for (int c = 1; c < numCols + 1; c++) {
                String letter = getCharForNumber(r);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(letter).append(c);
                String seatName = stringBuilder.toString();
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

                    splitSeat(b);

                    if (!isSeatTaken()) {
                        seatSelect(b);
                    }
                });

                GridPane.setHalignment(button, HPos.CENTER);
                gridSeats.add(button, c - 1, r - 1);
            }
        }
        //        gridSeats.gridLinesVisibleProperty().set(true);
    }

    private String getCharForNumber(int i) {
        return i > 0 && i < 27 ? String.valueOf((char) (i + 64)) : null;
    }
}
