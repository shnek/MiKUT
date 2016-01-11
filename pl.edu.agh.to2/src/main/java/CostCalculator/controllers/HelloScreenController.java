package CostCalculator.controllers;

import CostCalculator.Mocker;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloScreenController extends ScreenController implements Initializable {

    public Button loadBillingButton;
    public Button searchOffersButton;
    public TextField filePathTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBillingButton.setOnAction(this::handleLoadBillingButton);
        searchOffersButton.setOnAction(this::handleSearchOffersButton);
    }

    private void handleSearchOffersButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "searching offers");
        try {
            Billing billing = new Billing(); // todo: get actual billing repr
            //Billing billing = new Mocker().getMockBilling();
            controllerManager.setBilling(billing);
            showAnalysisScreen();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    private void handleLoadBillingButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "loading billing");
        File file = chooseFile();
        if (file != null) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "file chosen: {0}", file);
            filePathTextField.setText(file.toString());
            // todo: pass file to BillingReader
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "no file chosen");
        }
    }

    private void showAnalysisScreen() throws IOException {
        ScreenController screenController =
                ScreenController.createController(controllerManager, "/views/analysis_screen.fxml");
        controllerManager.setAnalysisScreenController((AnalysisScreenController) screenController);
        controllerManager.setCurrentScene(controllerManager.getAnalysisScene());
        controllerManager.startAnalysis();
    }

    private File chooseFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Billing files (*.pdf, *.csv)", "*.pdf", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(controllerManager.getStage());
    }

    @Override
    public void setScene(Scene scene) {
        controllerManager.setHelloScene(scene);
    }
}
