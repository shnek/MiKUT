package CostCalculator.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;

public abstract class ScreenController {
    ControllerManager controllerManager;

    public void populate(ControllerManager controllerManager) {
        this.controllerManager = controllerManager;
    }

    public static ScreenController createController(ControllerManager controllerManager, String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(ControllerManager.class.getResource(fxmlPath));
        Pane screen = loader.load();
        ScreenController controller = loader.getController();
        Scene scene = new Scene(screen);
        controller.populate(controllerManager);
        controller.setScene(scene);
        return controller;
    }

    public abstract void setScene(Scene scene);
}
