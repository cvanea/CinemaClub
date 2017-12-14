package cinemaclub.guiMain.StaffGui;

import cinemaclub.cinema.Screen;
import cinemaclub.guiMain.GuiData;
import exceptions.MissingRowColException;
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

    private Screen selectedScreen;
    private int getLastScreen;

    /**
     * Initialises the screen view. Displays screen 1 with grid seats.
     * Populates the list of screens.
     * Populates the numbers of the row and column.
     * @param location - The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources - used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        popScreenList();
        screenList.getSelectionModel().select(0);
        addNewScreenPane.setOpacity(0);
        selectedScreen = cinema.getScreen(screenList.getSelectionModel().getSelectedItem());
        setRowCol();
        populateRCBoxes();
    }

    /**
     * Deletes the selected screen.
     * @param actionEvent press the delete button
     */
    public void pressDelete(ActionEvent actionEvent) {
        cinema.deleteScreen(selectedScreen);
        screenList.getItems().clear();
        popScreenList();
    }

    /**
     * Reveals the add new screen pane.
     * @param actionEvent press add screen button.
     */
    public void pressAdd(ActionEvent actionEvent) {
        addNewScreenPane.setOpacity(1);
    }

    /**
     * Changes the display of the screen from user selection.
     * Looks for a new screen to be selected from the screens list view
     * @param actionEvent looks for new screen selection.
     */
    public void changeScreen(ActionEvent actionEvent) {
        selectedScreen = cinema.getScreen(screenList.getSelectionModel().getSelectedItem());
        if(selectedScreen != null){
            setRowCol();
        }
    }

    /**
     * Creates a new screen based on the users designated size.
     * Checks if both the drop down boxes are filled. Creates new screen based on the selected size.
     * @param actionEvent press create screen button.
     */
    public void pressConfirmScreen(ActionEvent actionEvent) {
        Integer newScreenNum = getLastScreen + 1;
        int newRowNum = newRows.getSelectionModel().getSelectedItem();
        int newColNum = newCols.getSelectionModel().getSelectedItem();

        try {
            validateRolColSelection(newRowNum, newColNum);
            cinema.addScreen(new Screen(newScreenNum, newRowNum, newColNum));
        } catch (ScreenNumberAlreadyExistsException | MissingRowColException e) {
            System.out.println(e.getMessage());
        }

        popScreenList();
        selectedScreen = cinema.getScreen(getLastScreen);
        screenList.getSelectionModel().select(newScreenNum);
        addNewScreenPane.setOpacity(0);
    }

    /**
     * Setups a new screen grid view based on the selected screen.
     */
    private void setRowCol(){
        deleteGrid();
        numRows.setText(Integer.toString(selectedScreen.getNumberRow()));
        numCols.setText(Integer.toString(selectedScreen.getSeatsPerRow()));
        int selectedRow = selectedScreen.getNumberRow();
        int selectedCol = selectedScreen.getSeatsPerRow();
        GuiData.setNumberOfRows(selectedRow);
        GuiData.setSeatsPerRow(selectedCol);
        GuiData.setupSeatButtons(gridSeats, 1120,500, "ScreenView");
    }

    /**
     * Populates the screen list view with a list of screens in the cinema.
     */
    private void popScreenList(){
        ArrayList<Screen> screensArray = cinema.getScreens();
        ArrayList<Integer> allScreenInts = new ArrayList<>();

        for(Screen screen: screensArray){
            allScreenInts.add(screen.getScreenNumber());
        }

        ObservableList<Integer> allScreens = FXCollections.observableArrayList(allScreenInts);
        screenList.setItems(allScreens);

        screenList.getSelectionModel().selectFirst();
        getLastScreen = allScreenInts.get(allScreenInts.size() - 1);
    }

    /**
     * Populates the row and column combo boxes with a list of sizes up to a max number.
     * Max number chosen based on the limitations of the screen view creator.
     */
    private void populateRCBoxes() {
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

    /**
     * Deletes the seats grid allow a clean updates to occur.
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
     * Checks if the row and column numbers are correctly selected.
     * @param RowNum integer of row size
     * @param ColNum integer of column size
     * @throws MissingRowColException error message if one is missing
     */
    private void validateRolColSelection(int RowNum, int ColNum) throws MissingRowColException {
        if (RowNum == 0 || ColNum == 0) {
            throw new MissingRowColException();
        }
    }

}

