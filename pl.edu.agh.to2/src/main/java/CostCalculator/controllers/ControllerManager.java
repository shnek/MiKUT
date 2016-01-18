package CostCalculator.controllers;

import BillingReader.offers.Offer;
import CostCalculator.CostCalculator;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import BillingReader.billings.BillingReader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ControllerManager {

    private Stage stage;
    private Scene helloScene;
    private Scene analysisScene;
    private Scene resultsScene;

    private File billingFile;
    private BillingReader billingReader;
    private BigDecimal currentPayment;
    private CostCalculator calculator;
    private Map<Offer, BigDecimal> results;
    private Offer selectedOffer;

    private AnalysisScreenController analysisScreenController;
    public Thread analysisThread;

    public ControllerManager(Stage stage) {
        this.stage = stage;
    }

    public void setHelloScene(Scene helloScene) {
        this.helloScene = helloScene;
    }

    public void setAnalysisScene(Scene analysisScene) {
        this.analysisScene = analysisScene;
    }

    public void setResultsScene(Scene resultsScene) {
        this.resultsScene = resultsScene;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getHelloScene() {
        return helloScene;
    }

    public Scene getAnalysisScene() {
        return analysisScene;
    }

    public Scene getResultsScene() {
        return resultsScene;
    }

    public void setCurrentScene(Scene scene) {
        this.stage.setScene(scene);
    }

    public void setAnalysisScreenController(AnalysisScreenController analysisScreenController) {
        this.analysisScreenController = analysisScreenController;
    }

    public void setBillingReader(BillingReader billingReader) {
        this.billingReader = billingReader;
    }

    public BigDecimal getCurrentPayment() {
        return currentPayment;
    }

    public void setCurrentPayment(BigDecimal currentPayment) {
        this.currentPayment = currentPayment;
    }

    public BillingReader getBillingReader() {
        return billingReader;
    }

    public File getBillingFile() {
        return billingFile;
    }

    public void setBillingFile(File billingFile) {
        this.billingFile = billingFile;
    }

    public void startAnalysis() {
        analysisScreenController.startAnalysis();
    }

    public CostCalculator getCalculator() {
        return calculator;
    }

    public void setCalculator(CostCalculator calculator) {
        this.calculator = calculator;
    }

    public Map<Offer, BigDecimal> getResults() {
        return results;
    }

    public void setResults(Map<Offer, BigDecimal> results) {
        this.results = results;
    }

    public Offer getSelectedOffer() {
        return selectedOffer;
    }

    public void setSelectedOffer(Offer selectedOffer) {
        this.selectedOffer = selectedOffer;
    }
}
