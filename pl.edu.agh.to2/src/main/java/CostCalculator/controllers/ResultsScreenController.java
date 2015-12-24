package CostCalculator.controllers;

import CostCalculator.CostCalculator;
import OperatorResolver.operatorresolver.Billing;
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

public class ResultsScreenController extends ScreenController implements Initializable {

    public Text currentOfferPriceText;
    public Button currentOfferDetailsButton;
    public Button analyzeAnotherBillingButton;
    public Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentOfferDetailsButton.setOnAction(this::handleCurrentOfferDetailsButton);
        exitButton.setOnAction(this::handleFinishButton);
        analyzeAnotherBillingButton.setOnAction(this::analyzeAnotherBillingButton);
    }

    private void analyzeAnotherBillingButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "analyzing another billing");
        this.controllerManager.setCurrentScene(controllerManager.getHelloScene());
    }

    private void handleFinishButton(ActionEvent event) {
        this.controllerManager.getStage().close();
        Logger.getLogger(getClass().getName()).log(Level.INFO, "exiting application");
    }

    private void handleCurrentOfferDetailsButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "displaying details");
        try {
            createDetailsStage();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    private void createDetailsStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/details_screen.fxml"));
        Parent root = loader.load();
        DetailsScreenController controller = loader.getController();
        Stage stage = new Stage();
        Scene detailsScene = new Scene(root);
        controller.setStage(stage);
        stage.setTitle("MiKUT");
        stage.setScene(detailsScene);
        stage.show();
    }

    @Override
    public void setScene(Scene scene) {
        controllerManager.setResultsScene(scene);
    }
}
