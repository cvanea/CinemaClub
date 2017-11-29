package cinemaclub.gui;

import cinemaclub.cinema.Cinema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

//    public Cinema cinema = new Cinema();

    static AnchorPane rootLogin;
    static AnchorPane rootCinema;


    static List<GridPane> loginGrid = new ArrayList<>();
    static List<GridPane> cinemaGrid = new ArrayList<>();

    private static int loginIdxCur = 0;
    private static int cinemaIdxCur = 0;

//    private static Stage loginStage;

//    private void setLoginStage(Stage stage) {
//        Main.loginStage = stage;
//    }
//
//    static public Stage getLoginStage() {
//        return Main.loginStage;
//    }
//
//    private static Stage cinemaStage;
//
//    private void setCinemaStage(Stage stage) {
//        Main.cinemaStage = stage;
//    }
//
//    static public Stage getCinemaStage() {
//        return Main.cinemaStage;
//    }

    @Override
    public void start(Stage loginStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("anchorLogin.fxml"));
            rootLogin = loader.load();
            loginGrid.add((GridPane) FXMLLoader.load(getClass().getResource("loginGui.fxml")));
            loginGrid.add((GridPane) FXMLLoader.load(getClass().getResource("registerGui.fxml")));
            rootLogin.getChildren().add(loginGrid.get(0));
            Scene loginScene = new Scene(rootLogin, 600, 400);
//            scene.getStylesheets().add(getClass().getResource("gui.css").toExternalForm());
            loginStage.setScene(loginScene);
            loginStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static GridPane get_pane(int idx) {
        return loginGrid.get(idx);
    }

    public static void setPaneLogin(int idx) {
        rootLogin.getChildren().remove(loginGrid.get(loginIdxCur));
        rootLogin.getChildren().add(loginGrid.get(idx));
        loginIdxCur = idx;

    }
    public static void setPaneCinema(int idx) {
        rootCinema.getChildren().remove(cinemaGrid.get(cinemaIdxCur));
        rootCinema.getChildren().add(cinemaGrid.get(idx));
        cinemaIdxCur = idx;
    }
    
    public static void cinemaStage(Cinema cinema) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("anchorCinema.fxml"));
            rootCinema = loader.load();
            AnchorCinema mainController = loader.getController(); // This did the "trick"
            mainController.setCinema(cinema);
            cinemaGrid.add((GridPane)FXMLLoader.load(Main.class.getResource("cinemaHome.fxml")));
            cinemaGrid.add((GridPane)FXMLLoader.load(Main.class.getResource("profileGui.fxml")));
            cinemaGrid.add((GridPane)FXMLLoader.load(Main.class.getResource("bookingsGui.fxml")));
            rootCinema.getChildren().add(cinemaGrid.get(0));
//            ProfileController profileController = loader.getController();
            Stage newStage = new Stage(); // new stage
            Scene cinemaScene = new Scene(rootCinema, 600, 400);
            newStage.setScene(cinemaScene);
            newStage.show();
            System.out.println(cinema.getCurrentUser().IExist());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
