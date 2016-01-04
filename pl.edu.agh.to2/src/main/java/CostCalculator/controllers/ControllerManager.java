package CostCalculator.controllers;

import OperatorResolver.operatorresolver.Billing;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerManager {

    private Stage stage;
    private Scene helloScene;
    private Scene analysisScene;
    private Scene resultsScene;

    private Billing billing;

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

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Billing getBilling() {
        return billing;
    }
}
