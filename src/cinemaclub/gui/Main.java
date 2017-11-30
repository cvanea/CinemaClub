package cinemaclub.gui;

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

//    public final Model = new Model();

    static AnchorPane rootLogin;
    static AnchorPane rootCinema;


    static List<GridPane> loginGrid = new ArrayList<>();
    static List<GridPane> cinemaGrid = new ArrayList<>();

    private static int loginIdxCur = 0;
    private static int cinemaIdxCur = 0;

    static AnchorCinema anchorCinema;
    static CinemaHome cinemaHome;
    static ProfileController profileController;
    static BookingsGui bookingsGui;
    static ProfileEditController profileEditController;

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

    public static void cinemaStage() {
        try {
            FXMLLoader anchor = new FXMLLoader(Main.class.getResource("anchorCinema.fxml"));
            FXMLLoader profile = new FXMLLoader(Main.class.getResource("profileGui.fxml"));
            FXMLLoader profileEdit = new FXMLLoader(Main.class.getResource("profileEditGui.fxml"));
            FXMLLoader home = new FXMLLoader(Main.class.getResource("cinemaHome.fxml"));
            FXMLLoader bookings = new FXMLLoader(Main.class.getResource("bookingsGui.fxml"));
            rootCinema = anchor.load();
            AnchorCinema controller = anchor.getController();
            ProfileEditController profileEditor = profileEdit.getController();
//            profileEditor.setModel(model);
            cinemaGrid.add((GridPane)home.load());
            cinemaGrid.add((GridPane)profile.load());
            cinemaGrid.add((GridPane)bookings.load());
            cinemaGrid.add((GridPane)profileEdit.load());
            rootCinema.getChildren().add(cinemaGrid.get(0));
            Stage newStage = new Stage(); // new stage
            Scene cinemaScene = new Scene(rootCinema, 600, 400);
            newStage.setScene(cinemaScene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
