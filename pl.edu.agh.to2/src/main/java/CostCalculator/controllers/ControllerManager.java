package CostCalculator.controllers;

import OperatorResolver.operatorresolver.billingcontainers.Billing;
import BillingReader.billings.BillingReader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.math.BigDecimal;

public class ControllerManager {

    private Stage stage;
    private Scene helloScene;
    private Scene analysisScene;
    private Scene resultsScene;

    private File billingFile;
    private BillingReader billingReader;
    private BigDecimal currentPayment;
    private AnalysisScreenController analysisScreenController;

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
}
