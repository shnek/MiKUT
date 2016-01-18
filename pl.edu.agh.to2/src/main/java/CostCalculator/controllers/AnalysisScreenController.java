package CostCalculator.controllers;

import BillingReader.billings.IncorrectEntryException;
import BillingReader.offers.Offer;
import BillingReader.offers.OfferDownloader;
import CostCalculator.CostCalculator;
import CostCalculator.Mocker;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;

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
    public Text currentStatusText;

    public Billing billing;
    private List<Offer> offers;
    private Task<Void> analysisTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        progressBar.setProgress(0.0);
        cancelButton.setCancelButton(true);
        cancelButton.setOnAction(this::handleCancelButton);
    }

    private Task<Void> createAnalysisTask() {
        return new Task<Void>() {
            @Override public Void call() {
                try {
                    updateProgress(0.00, 1.00);
                    currentStatusText.setText("Analyzing your billing...");
                    analyzeBilling();
                    updateProgress(0.25, 1.00);
                    currentStatusText.setText("Searching offers...");
                    searchOffers();
                    updateProgress(0.50, 1.00);
                    currentStatusText.setText("Calculating costs...");
                    calculateCosts();
                    updateProgress(0.75, 1.00);
                    currentStatusText.setText("Choosing best offer...");
                    chooseBestOffer();
                    updateProgress(1.00, 1.00);
                } catch (BillingAnalysisException e) {
                    showAlert();
                } catch (InterruptedException e) {
                    if (isCancelled()) return null;
                    else e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
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
        ResultsScreenController controller = (ResultsScreenController) ScreenController.createController(controllerManager, "/views/results_screen.fxml");
        controller.setCurrentOfferPriceText();
        controller.fillScreen();
        controllerManager.setCurrentScene(controllerManager.getResultsScene());
    }

    private void cancelAnalysis() throws IOException {
        analysisTask.cancel();
        controllerManager.setCurrentScene(controllerManager.getHelloScene());
    }

    private void analyzeBilling() throws InterruptedException, BillingAnalysisException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "analyzing billing");
        try {
            billing = controllerManager.getBillingReader().readBilling(controllerManager.getBillingFile());
        } catch (IncorrectEntryException | IOException | ArrayIndexOutOfBoundsException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, String.format("error reading billing: %s", e.toString()));
            throw new BillingAnalysisException();
        }
    }

    private void searchOffers() throws InterruptedException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "searching offers");

        try {
            offers = new OfferDownloader().getOffers();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "error downloading offers: {0}", e);
        }
    }

    private void calculateCosts() throws InterruptedException, CostCalculationException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "calculating costs");

        CostCalculator calculator = new CostCalculator(billing, offers);
        this.controllerManager.setCalculator(calculator);
        Map<Offer, BigDecimal> results = calculator.calculateCosts();
        if (results == null) throw new CostCalculationException();
        this.controllerManager.setResults(results);
    }

    private void chooseBestOffer() throws InterruptedException {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "choosing best offer");

        BigDecimal bestOfferValue = this.controllerManager.getCalculator()
                .getBestOfferValue(this.controllerManager.getResults());
        if (controllerManager.getCurrentPayment().compareTo(bestOfferValue) < 0) {
            // todo: show alert
        }
    }

    public void setScene(Scene scene) {
        controllerManager.setAnalysisScene(scene);
    }

    public void startAnalysis() {
        analysisTask = createAnalysisTask();
        analysisTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                try {
                    showResultsScreen();
                } catch (IOException e) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
                }
            }
        });
        analysisTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "analysis error");
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, "analysis debug");
                showAlert();
            }
        });

        progressBar.progressProperty().bind(analysisTask.progressProperty());
        Thread analysisThread = new Thread(analysisTask);
        this.controllerManager.analysisThread = analysisThread;
        analysisThread.setDaemon(true);
        analysisThread.start();

    }

    private void showAlert() {
        try {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Billing Analysis Error");
            alert.setContentText("Your billing file is invalid or broken. Choose another file and try again");
            alert.showAndWait();
            cancelAnalysis();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class BillingAnalysisException extends Exception {}
    private class CostCalculationException extends Exception {}
}
