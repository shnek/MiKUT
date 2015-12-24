package CostCalculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsScreenController implements Initializable {

    public Button closeButton;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton.setOnAction(this::handleCloseButton);
    }

    private void handleCloseButton(ActionEvent actionEvent) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
