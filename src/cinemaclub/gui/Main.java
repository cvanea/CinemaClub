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

    static AnchorPane root;

    static List<GridPane> grid = new ArrayList<>();

    private static int idx_cur = 0;

    @Override
    public void start(Stage primaryStage) {
        try {
            root = (AnchorPane)FXMLLoader.load(getClass().getResource("anchor.fxml"));

            grid.add((GridPane)FXMLLoader.load(getClass().getResource("loginGui.fxml")));
            grid.add((GridPane)FXMLLoader.load(getClass().getResource("registerGui.fxml")));

            root.getChildren().add(grid.get(0));

            Scene scene = new Scene(root, 600, 400);
//            scene.getStylesheets().add(getClass().getResource("gui.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init_app(){
        for(int i = 0; i < grid.size(); i++){

        }
    }

    public static GridPane get_pane(int idx){
        return grid.get(idx);
    }

    public static void set_pane(int idx){
        root.getChildren().remove(grid.get(idx_cur));
        root.getChildren().add(grid.get(idx));
        idx_cur= idx;

    }

}
