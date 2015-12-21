package CostCalculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalysisScreenController implements Initializable {

    public Stage rootStage;
    public Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cancelButton.setOnAction(this::handleCancelButton);
    }

    private void handleCancelButton(ActionEvent actionEvent) {
        // tymczasowo wyświetla results_screen
        Logger.getLogger(getClass().getName()).log(Level.INFO, "displaying results");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/results_screen.fxml"));
            Pane resultsScreen = loader.load();
            rootStage.setScene(new Scene(resultsScreen));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    public void populate(Stage rootStage) {
        this.rootStage = rootStage;
    }
}
