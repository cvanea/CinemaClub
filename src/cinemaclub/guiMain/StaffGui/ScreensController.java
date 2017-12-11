package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Screen;
import cinemaclub.guiMain.GuiData;
import exceptions.ScreenNumberAlreadyExistsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScreensController extends MainController implements Initializable {

    @FXML GridPane gridSeats;
    @FXML Label numRows;
    @FXML Label numCols;
    @FXML ComboBox<Integer> screenList;
    @FXML ComboBox<Integer> newRows;
    @FXML ComboBox<Integer> newCols;
    @FXML AnchorPane addNewScreenPane;

    Screen selectedScreen;
    int getLastScreen;

    public void pressDelete(ActionEvent actionEvent) {

    }

    public void pressAdd(ActionEvent actionEvent) {
        addNewScreenPane.setOpacity(1);
    }

    public void changeScreen(ActionEvent actionEvent) {
        selectedScreen = cinema.getScreen(screenList.getSelectionModel().getSelectedItem());
        if(selectedScreen != null){
            setRowCol();
        }
    }

    public void pressConfirmScreen(ActionEvent actionEvent) {
    Integer newScreenNum = getLastScreen+1;
    int newRowNum = newRows.getSelectionModel().getSelectedItem();
    int newColNum = newCols.getSelectionModel().getSelectedItem();
        if (newColNum != 0 &&  newRowNum != 0) {
            //TODO: ADD Exceptions
            try {
            cinema.addScreen(new Screen(newScreenNum, newRowNum, newColNum));
            } catch (ScreenNumberAlreadyExistsException e) {
                System.out.println(e.getMessage());
            }
            popScreenList();
            selectedScreen = cinema.getScreen(getLastScreen);
            screenList.getSelectionModel().select(newScreenNum);
            addNewScreenPane.setOpacity(0);
        } else {
            System.out.println("Both row and col not selected");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popScreenList();
        screenList.getSelectionModel().select(0);
        addNewScreenPane.setOpacity(0);
        selectedScreen = cinema.getScreen(screenList.getSelectionModel().getSelectedItem());
        setRowCol();
        populateRCBoxes();
    }


    public void setRowCol(){
        deleteGrid();
//        Integer screenNum = screenList.getSelectionModel().getSelectedItem();
//        numRows.setText(Integer.toString(selectedScreen.getNumberRow()));
//        numCols.setText(Integer.toString(selectedScreen.getSeatsPerRow()));
        int selcRow = selectedScreen.getNumberRow();
        int selcCol = selectedScreen.getSeatsPerRow();
        GuiData.setNumberOfRows(selcRow);
        GuiData.setSeatsPerRow(selcCol);
        GuiData.setupSeatButtons(gridSeats, 1120,500, "ScreenView");
    }

    public void popScreenList(){
        ArrayList<Screen> screensArray = cinema.getScreens();
        ArrayList<Integer> allScreenInts = new ArrayList<>();
        for(Screen screen: screensArray){
            allScreenInts.add(screen.getScreenNumber());
        }
        ObservableList<Integer> allScreens = FXCollections.observableArrayList(allScreenInts);
        try {
            screenList.setItems(allScreens);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        getLastScreen = allScreenInts.get(allScreenInts.size() - 1);
    }

    public void populateRCBoxes() {
        ArrayList<Integer> newRowsArray = new ArrayList<>();

        for (Integer i = 1; i < 13; i++) {
            newRowsArray.add(i);
        }
        ObservableList<Integer> newRowsObs = FXCollections.observableArrayList(newRowsArray);
        newRows.setItems(newRowsObs);

        ArrayList<Integer> newColsArray = new ArrayList<>();
        for (Integer i = 0; i < 15; i++) {
            newColsArray.add(i);
        }
        ObservableList<Integer> newColsObs = FXCollections.observableArrayList(newColsArray);
        newCols.setItems(newColsObs);
    }

    public void deleteGrid(){
        while(gridSeats.getRowConstraints().size() > 0){
            gridSeats.getRowConstraints().remove(0);
        }
        while(gridSeats.getColumnConstraints().size() > 0){
            gridSeats.getColumnConstraints().remove(0);
        }
        gridSeats.getChildren().clear();
    }
}

