package CostCalculator.controllers;

import BillingReader.Offer;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsScreenController implements Initializable {

    private Stage stage;
    private Offer offer;

    public Button closeButton;
    public Text offerNameText;
    public Text operatorText;
    public Text amountText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeButton.setOnAction(this::handleCloseButton);
        //offerNameText.setText(offer.getName());
        //operatorText.setText(offer.getOperator().getName());
        //amountText.setText("");
    }

    private void handleCloseButton(ActionEvent actionEvent) {
        stage.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
