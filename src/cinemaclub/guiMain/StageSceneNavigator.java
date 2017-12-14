package cinemaclub.guiMain;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.CustomerGui.CustomerMainController;
import cinemaclub.guiMain.LoginGui.LoginMainController;
import cinemaclub.guiMain.StaffGui.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class for controlling navigation between views and stages.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class StageSceneNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */

    // Login
    public static final String LOGIN_MAIN = "LoginGui/loginMain.fxml";
    public static final String LOGIN_USER = "LoginGui/loginUser.fxml";
    public static final String LOGIN_REGISTER = "LoginGui/loginRegister.fxml";

    //Customer
    public static final String CUSTOMER_MAIN = "CustomerGui/Main.fxml";
    public static final String CUSTOMER_HOME = "CustomerGui/FilmByDate.fxml";
    public static final String CUSTOMER_BOOK_SEATS = "CustomerGui/BookSeats.fxml";
    public static final String CUSTOMER_PROFILE = "CustomerGui/Profile.fxml";
    public static final String CUSTOMER_FILM_VIEW = "CustomerGui/FilmBrowser.fxml";

    //Staff
    public static final String STAFF_MAIN = "StaffGui/Main.fxml";
    public static final String STAFF_FILM = "StaffGui/Film.fxml";
    public static final String STAFF_IND_SHOWING = "StaffGui/IndividualShowing.fxml";
    public static final String STAFF_PROFILE = "StaffGui/Profile.fxml";
    public static final String STAFF_EDIT_USER_PROFILE = "StaffGui/EditUserProfile.fxml";
    public static final String STAFF_SCREENS = "StaffGui/Screens.fxml";
    public static final String STAFF_SHOWINGS = "StaffGui/Showings.fxml";


    /**
     *The main application layout controllers.
     */
    private static LoginMainController loginMainController;
    private static CustomerMainController customerMainController;
    private static MainController mainController;

    /**
     * Stores the main controller for later use in navigation tasks.
     * @param loginMainController the main application layout controller.
     */
    public static void setLoginMainController(LoginMainController loginMainController) {
        StageSceneNavigator.loginMainController = loginMainController;
    }
    /**
     * Stores the main controller for later use in navigation tasks.
     * @param customerMainController the main application layout controller.
     */
    public static void setCustomerMainController(CustomerMainController customerMainController) {
        StageSceneNavigator.customerMainController = customerMainController;
    }

    /**
     * Stores the main controller for later use in navigation tasks.
     * @param mainController the main application layout controller.
     */
    public static void setMainController(MainController mainController) {
        StageSceneNavigator.mainController = mainController;
    }

    /**
     * Loads the login stage
     * @param fxml of the main login stage controller
     */
    public static void loadLoginView(String fxml) {
        try {
            loginMainController.setView(FXMLLoader.load(StageSceneNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the customer stage
     * @param fxml of the main customer stage controller
     */
    public static void loadCustomerView(String fxml) {
        try {
            customerMainController.setView(FXMLLoader.load(StageSceneNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the staff view stage
     * @param fxml of the main staff view stage controller
     */
    public static void loadStaffView(String fxml) {
        try {
            mainController.setView(FXMLLoader.load(StageSceneNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates panes of the login controller.
     * @return the login pane to be displayed
     * @throws IOException if view cannot be loaded
     */
    public static Pane loadLoginPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane loginPane = (Pane) loader.load(StageSceneNavigator.class.getResourceAsStream(StageSceneNavigator.LOGIN_MAIN));

        LoginMainController loginMainController = loader.getController();

        StageSceneNavigator.setLoginMainController(loginMainController);
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_USER);

        return loginPane;
    }

    /**
     * Creates panes of the customer controller.
     * @return the customer pane to be displayed
     * @throws IOException if view cannot be loaded
     */
    private static Pane loadCustomerPane(Cinema cinema) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane customerPane = (Pane) loader.load(StageSceneNavigator.class.getResourceAsStream(StageSceneNavigator.CUSTOMER_MAIN));
        CustomerMainController customerMainController = loader.getController();
        customerMainController.setCinema(cinema);
        StageSceneNavigator.setCustomerMainController(customerMainController);
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_FILM_VIEW);

        return customerPane;
    }

    /**
     * Creates panes of the staff pane controller.
     * @return the staff pane to be displayed
     * @throws IOException if view cannot be loaded
     */
    private static Pane loadStaffPane(Cinema cinema) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane staffPane = (Pane) loader.load(StageSceneNavigator.class.getResourceAsStream(StageSceneNavigator.STAFF_MAIN));
        MainController mainController = loader.getController();
        mainController.setCinema(cinema);
        StageSceneNavigator.setMainController(mainController);
        StageSceneNavigator.loadStaffView(StageSceneNavigator.STAFF_FILM);

        return staffPane;
    }


    /**
     * Creates the login stage
     * @throws IOException if the load cannot be viewed
     */
    public static void loginStage() throws IOException{
        Stage stage = new Stage(); // new stage
        stage.setTitle("Cinema Club - Login");
        stage.setScene(createScene(loadLoginPane()));
        stage.show();
    }

    /**
     * Creates the customer stage
     * @throws IOException if the load cannot be viewed
     */
    public static void customerStage(Cinema cinema) throws IOException{

        Stage stage = new Stage(); // new stage
        stage.setTitle("Cinema Club - Customer");
        stage.setScene(createScene(loadCustomerPane(cinema)));
        stage.show();
    }

    /**
     * Creates the staff stage
     * @throws IOException if the load cannot be viewed
     */
    public static void staffStage(Cinema cinema) throws IOException{

        Stage stage = new Stage(); // new stage
        stage.setTitle("Cinema Club - Staff");
        stage.setScene(createScene(loadStaffPane(cinema)));
        stage.show();
    }

    /**
     * Creates the main application scene for views.
     * @param stageMainPane is main application layout pane.
     * @return the created scene.
     */
    public static Scene createScene(Pane stageMainPane) {
        Scene scene = new Scene(stageMainPane);
        scene.getStylesheets().setAll(StageSceneNavigator.class.getResource("style.css").toExternalForm());
        return scene;
    }

}