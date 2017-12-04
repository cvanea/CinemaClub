package cinemaclub.guiMain;

import cinemaclub.cinema.Cinema;
import cinemaclub.guiMain.CustomerGui.CustomerMainController;
import cinemaclub.guiMain.LoginGui.LoginMainController;
import cinemaclub.guiMain.StaffGui.StaffMainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class StageSceneNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */

    // Login
    public static final String LOGIN_MAIN    = "LoginGui/loginMain.fxml";
    public static final String LOGIN_USER    = "LoginGui/loginUser.fxml";
    public static final String LOGIN_REGISTER = "LoginGui/loginRegister.fxml";

    //Customer
    public static final String CUSTOMER_MAIN = "CustomerGui/CustomerMain.fxml";
    public static final String CUSTOMER_HOME = "CustomerGui/CustomerHome.fxml";
    public static final String CUSTOMER_PROFILE = "CustomerGui/CustomerProfile.fxml";

    //Staff
    public static final String STAFF_MAIN = "StaffGui/StaffMain.fxml";
    public static final String VISTA_1 = "vista1.fxml";
    public static final String VISTA_2 = "vista2.fxml";

    /** The main application layout controller. */
    private static LoginMainController loginMainController;
    private static CustomerMainController customerMainController;
    private static StaffMainController staffMainController;

    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param loginMainController the main application layout controller.
     */
    public static void setLoginMainController(LoginMainController loginMainController) {
        StageSceneNavigator.loginMainController = loginMainController;
    }
    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param customerMainController the main application layout controller.
     */
    public static void setCustomerMainController(CustomerMainController customerMainController) {
        StageSceneNavigator.customerMainController = customerMainController;
    }
    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param staffMainController the main application layout controller.
     */
    public static void setStaffMainController(StaffMainController staffMainController) {
        StageSceneNavigator.staffMainController = staffMainController;
    }

    /**
     * Loads the view specified by the fxml file into the
     * viewHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */

    public static void loadLoginView(String fxml) {
        try {
            loginMainController.setView(
                FXMLLoader.load(StageSceneNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  public static void loadCustomerView(String fxml) {
        try {
            customerMainController.setView(
                FXMLLoader.load(StageSceneNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  public static void loadStaffView(String fxml) {
        try {
            staffMainController.setView(
                FXMLLoader.load(StageSceneNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Create Panes
     */

    public static Pane loadLoginPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane loginPane = (Pane) loader.load(StageSceneNavigator.class.getResourceAsStream(StageSceneNavigator.LOGIN_MAIN));

        LoginMainController loginMainController = loader.getController();

        StageSceneNavigator.setLoginMainController(loginMainController);
        StageSceneNavigator.loadLoginView(StageSceneNavigator.LOGIN_USER);

        return loginPane;
    }

    private static Pane loadCustomerPane(Cinema cinema) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane customerPane = (Pane) loader.load(StageSceneNavigator.class.getResourceAsStream(StageSceneNavigator.CUSTOMER_MAIN));
        CustomerMainController customerMainController = loader.getController();
        customerMainController.setCinema(cinema);
        StageSceneNavigator.setCustomerMainController(customerMainController);
        StageSceneNavigator.loadCustomerView(StageSceneNavigator.CUSTOMER_HOME);

        return customerPane;
    }

    private static Pane loadStaffPane(Cinema cinema) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane staffPane = (Pane) loader.load(StageSceneNavigator.class.getResourceAsStream(StageSceneNavigator.STAFF_MAIN));
        StaffMainController staffMainController  = loader.getController();
        staffMainController.setCinema(cinema);
        StageSceneNavigator.setStaffMainController(staffMainController);
        StageSceneNavigator.loadStaffView(StageSceneNavigator.VISTA_1);

        return staffPane;
    }

    /*
     Creates New Stage
     */

    public static void loginStage() throws IOException{
        Stage stage = new Stage(); // new stage
        stage.setTitle("Cinema Club - Login");
        stage.setScene(createScene(loadLoginPane()));
        stage.show();
    }

    public static void customerStage(Cinema cinema) throws IOException{

        Stage stage = new Stage(); // new stage
        stage.setTitle("Cinema Club - Customer");
        stage.setScene(createScene(loadCustomerPane(cinema)));
        stage.show();
    }

    public static void staffStage(Cinema cinema) throws IOException{

        Stage stage = new Stage(); // new stage
        stage.setTitle("Cinema Club - Staff");
        stage.setScene(createScene(loadStaffPane(cinema)));
        stage.show();
    }

    /**
     * Creates the main application scene.
     *
     * @param stageMainPane the main application layout.
     *
     * @return the created scene.
     */

    public static Scene createScene(Pane stageMainPane) {
        Scene scene = new Scene(stageMainPane);
//        scene.getStylesheets().setAll(getClass().getResource("vista.css").toExternalForm());
        return scene;
    }

}