package CostCalculator.controllers;

import BillingReader.offers.Offer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class DetailsScreenController extends ScreenController implements Initializable {

    private Stage stage;

    public Button closeButton;
    public Text offerNameText;
    public Text operatorText;
    public Text amountText;

    private Offer offer = controllerManager.getSelectedOffer();
    private Map<Offer, BigDecimal> results;

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

    @Override
    public void setScene(Scene scene) {

    }
}
