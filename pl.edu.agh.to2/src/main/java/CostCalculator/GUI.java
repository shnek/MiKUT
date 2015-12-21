package CostCalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GUI extends Application {

    public Scene helloScene;
    public Scene analysisScene;
    public Scene resultsScene;

    @Override
    public void start(Stage primaryStage) {
        try {
            Pane helloScreen = FXMLLoader.load(getClass().getResource("/views/hello_screen.fxml"));
            Pane analysisScreen = FXMLLoader.load(getClass().getResource("/views/analysis_screen.fxml"));
            Pane resultsScreen = FXMLLoader.load(getClass().getResource("/views/results_screen.fxml"));
            helloScene = new Scene(helloScreen);
            analysisScene = new Scene(analysisScreen);
            resultsScene = new Scene(resultsScreen);
            primaryStage.setScene(helloScene);
            primaryStage.setTitle("MiKUT");
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        launch();
    }

}
