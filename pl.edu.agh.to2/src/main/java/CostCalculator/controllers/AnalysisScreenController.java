package CostCalculator.controllers;

import BillingReader.Offer;
import CostCalculator.CostCalculator;
import CostCalculator.Mocker;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class AnalysisScreenController extends ScreenController implements Initializable {

    public ProgressBar progressBar;
    public Button cancelButton;
    public Button startButton;
    public Text currentStatusText;

    public Billing billing;
    private CostCalculator calculator;
    private Map<Offer, BigDecimal> results;
    private Task<Void> analysisTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setProgress(0.0);
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(this::handleCancelButton);
        startButton.setOnAction(this::handleStartButton);
    }

    private void handleStartButton(ActionEvent actionEvent) {
        analysisTask = createAnalysisTask();
        analysisTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t)
            {
                try {
                    showResultsScreen();
                } catch (IOException e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
                }
            }
        });
        analysisTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t)
            {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "analysis error: {0}", t.toString());
            }
        });

        progressBar.progressProperty().bind(analysisTask.progressProperty());
        Thread analysisThread = new Thread(analysisTask);
        analysisThread.setDaemon(true);
        analysisThread.start();
    }

    private Task<Void> createAnalysisTask() {
        return new Task<Void>() {
            @Override public Void call() {
                startButton.setDisable(true);
                try {
                    updateProgress(0.00, 1.00);
                    analyzeBilling();
                    updateProgress(0.25, 1.00);
                    searchOffers();
                    updateProgress(0.50, 1.00);
                    calculateCosts();
                    updateProgress(0.75, 1.00);
                    chooseBestOffer();
                    updateProgress(1.00, 1.00);
                } catch (InterruptedException e) {
                    if (isCancelled()) return null;
                    else e.printStackTrace();
                }
                return null;
            }
        };
    }

    private void handleCancelButton(ActionEvent actionEvent) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "canceling analysis");
        try {
            cancelAnalysis();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    private void showResultsScreen() throws IOException {
        ScreenController.createController(controllerManager, "/views/results_screen.fxml");
        controllerManager.setCurrentScene(controllerManager.getResultsScene());
    }

    private void cancelAnalysis() throws IOException {
        analysisTask.cancel();
        controllerManager.setCurrentScene(controllerManager.getHelloScene());
    }

    private void analyzeBilling() throws InterruptedException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "analyzing billing");
        sleep(1000);
    }

    private void searchOffers() throws InterruptedException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "searching offers");
        sleep(1000);
    }

    private void calculateCosts() throws InterruptedException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "calculating costs");
        sleep(1000);

        Billing billing = controllerManager.getBilling();
        List<Offer> offers = new Mocker().getMockOffers(); // todo: get actual offers
        calculator = new CostCalculator(billing, offers);
        results = calculator.calculateCosts();
    }

    private void chooseBestOffer() throws InterruptedException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "choosing best offer");
        sleep(1000);

        BigDecimal bestOfferValue = calculator.getBestOfferValue(results);
        // todo: check if bestOfferValue <= current offer
    }

    public void setScene(Scene scene) {
        controllerManager.setAnalysisScene(scene);
    }
}
