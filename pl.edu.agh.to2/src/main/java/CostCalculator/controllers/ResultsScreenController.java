package CostCalculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultsScreenController implements Initializable {

    public Stage rootStage;
    public Text currentOfferPriceText;
    public Button currentOfferDetailsButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentOfferDetailsButton.setOnAction(this::handleCurrentOfferDetailsButton);
    }

    private void handleCurrentOfferDetailsButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "displaying details");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/details_screen.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("MiKUT");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    public void populate(Stage rootStage) {
        this.rootStage = rootStage;
    }
}
