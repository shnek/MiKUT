package CostCalculator;

import CostCalculator.controllers.HelloScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/hello_screen.fxml"));
            Pane screen = loader.load();
            final HelloScreenController controller = loader.getController();
            controller.populate(primaryStage);
            Scene scene = new Scene(screen);
            primaryStage.setScene(scene);
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
