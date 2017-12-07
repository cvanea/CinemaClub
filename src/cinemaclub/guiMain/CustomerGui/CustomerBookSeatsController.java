package cinemaclub.guiMain.CustomerGui;

import cinemaclub.guiMain.GuiData;
import cinemaclub.guiMain.StageSceneNavigator;
import exceptions.SeatAlreadyTakenException;
import exceptions.SeatNotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerBookSeatsController extends CustomerMainController implements Initializable {

    @FXML
    ImageView imageBox;
    @FXML
    Label titleText;
    @FXML
    Label descriptionText;
    @FXML
    Label timeText;
    @FXML
    Label runtimeText;
    @FXML
    Label dateText;
    @FXML
    Label errorLabel;
    @FXML
    GridPane gridSeats;

    private String seatRow;
    private int seatNumber;
    private Button selectedSeat = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleText.setText(GuiData.getFilm().getTitle());
        descriptionText.setText(GuiData.getFilm().getDescription());
        runtimeText.setText(GuiData.getFilm().getRunTime());
        timeText.setText(GuiData.getTime());
        dateText.setText(GuiData.getDate());
        Image img = new Image(GuiData.getFilm().getImagePath());
        imageBox.setImage(img);
        setupSeatButtons();
    }

    public void pressReserveSeat(ActionEvent actionEvent) throws IOException {
        try {
            if (cinema.getScreen(1).isSeatTaken(seatRow, seatNumber)) {
                errorLabel.setText("Seat taken!");
            } else {
                cinema.bookFilm(GuiData.getDate(), GuiData.getTime(), GuiData.getFilm(), cinema.getScreen(1), seatRow, seatNumber);
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(CustomerBookSeatsController.class.getResource("ModalBooked.fxml"));
                stage.setScene(new Scene(root));
                stage.setTitle("Booked");
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) actionEvent.getSource()).getScene().getWindow());
                stage.show();
                StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);
            }
        } catch (SeatAlreadyTakenException | SeatNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void seatTaken(Button button) {
        String seat = button.getText();
        String[] splitSeat = seat.split("(?!^)");
        seatRow = splitSeat[0];
        seatNumber = Integer.parseInt(splitSeat[1]);
        try {
            if (cinema.getScreen(1).isSeatTaken(seatRow, seatNumber)) {
//                errorLabel.setText("Seat taken!");
            }
        } catch (SeatNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Button seatSelect(Button button) {
        Image imgSeat;
        if (selectedSeat != null) {
            imgSeat = new Image("/seatW32.png");
            selectedSeat.setGraphic(new ImageView(imgSeat));
        }
        try {
            if (cinema.getScreen(1).isSeatTaken(seatRow, seatNumber)) {
                imgSeat = new Image("/seatR32.png");
            } else {
                imgSeat = new Image("/seatY32.png");
                selectedSeat = button;
            }
            button.setGraphic(new ImageView(imgSeat));
        } catch (SeatNotFoundException e) {

        }
        return selectedSeat;
    }

    public void setupSeatButtons() {

        //TODO: add get max number of rows and cols to model - replace value here
        int numRows = 5;
        int numCols = 10;
        int rowHeight = 500 / numRows;
        int columnWidth = 780 / numCols;

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

                try {
                    Image imgSeat;
                    if (cinema.getScreen(1).isSeatTaken(getCharForNumber(r), c)) {
                        imgSeat = new Image("/seatR32.png");

                    } else {
                        imgSeat = new Image("/seatW32.png");
                    }

                    button.setGraphic(new ImageView(imgSeat));

                    button.setOnAction((ActionEvent e) -> {
                        Object o = e.getSource();
                        Button b = null;
                        if (o instanceof Button) {
                            b = (Button) o;
                        }
                        seatTaken(b);
                        seatSelect(b);
                    });

                } catch (SeatNotFoundException e) {

                }
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
