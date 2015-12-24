package CostCalculator.controllers;

import CostCalculator.CostCalculator;
import OperatorResolver.operatorresolver.Billing;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class AnalysisScreenController extends ScreenController implements Initializable {

    public ProgressBar progressBar;
    public Button cancelButton;
    public Text currentStatusText;

    public Billing billing;
    public CostCalculator costCalculator;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setProgress(0.0);
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(this::handleCancelButton);
    }

    private void handleCancelButton(ActionEvent actionEvent) {
        // tymczasowo wyświetla results_screen
        Logger.getLogger(getClass().getName()).log(Level.INFO, "canceling analysis");
        try {
            showResultsScreen();
//            cancelAnalysis();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    private void showResultsScreen() throws IOException {
        ScreenController.createController(controllerManager, "/views/results_screen.fxml");
        controllerManager.setCurrentScene(controllerManager.getResultsScene());
    }

    private void cancelAnalysis() throws IOException {
        controllerManager.setCurrentScene(controllerManager.getHelloScene());
    }

    private void analyze(Billing billing) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "analyzing billing");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressBar.setProgress(0.25);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "searching offers");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressBar.setProgress(0.50);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "calculating costs");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressBar.setProgress(0.75);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "choosing best offer");
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressBar.setProgress(1.0);
    }

    public void passData(Billing billing) {
        this.billing = billing;
    }

    public void setScene(Scene scene) {
        controllerManager.setAnalysisScene(scene);
    }
}
