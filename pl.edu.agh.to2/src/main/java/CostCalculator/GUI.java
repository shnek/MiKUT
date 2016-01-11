package CostCalculator;

import CostCalculator.controllers.ControllerManager;
import CostCalculator.controllers.ScreenController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;


public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            ControllerManager controllerManager = new ControllerManager(primaryStage);
            ScreenController.createController(controllerManager, "/views/hello_screen.fxml");
            controllerManager.setCurrentScene(controllerManager.getHelloScene());
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
