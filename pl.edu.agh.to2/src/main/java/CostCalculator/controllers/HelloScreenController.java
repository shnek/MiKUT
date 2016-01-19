package CostCalculator.controllers;

import BillingReader.billings.IncorrectEntryException;
import BillingReader.billings.PlayReader;
import BillingReader.billings.PlusReader;
import OperatorResolver.operatorresolver.billingcontainers.Billing;
import BillingReader.billings.BillingReader;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloScreenController extends ScreenController implements Initializable {

    public Button loadBillingButton;
    public Button searchOffersButton;
    public TextField filePathTextField;
    public TextField paymentTextField;
    public RadioButton plusRadioButton;
    public RadioButton playRadioButton;

    private File billingFile;
    private boolean fileEntered = false;
    private boolean paymentEntered = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadBillingButton.setOnAction(this::handleLoadBillingButton);
        searchOffersButton.setOnAction(this::handleSearchOffersButton);
        ToggleGroup group = new ToggleGroup();
        plusRadioButton.setToggleGroup(group);
        playRadioButton.setToggleGroup(group);
        plusRadioButton.setSelected(true);
        searchOffersButton.setDisable(true);
        filePathTextField.setDisable(true);
        paymentTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            paymentEntered = isMoney(newValue);
            searchOffersButton.setDisable(searchButtonState());
        });
    }

    private void handleSearchOffersButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "searching offers");
        try {
            controllerManager.setBillingFile(billingFile);
            controllerManager.setBillingReader(getCurrentProviderReader());
            controllerManager.setCurrentPayment(getCurrentPayment());
            showAnalysisScreen();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    private boolean isMoney(String text) {
        return text.matches("[0-9]*(,|\\.)?[0-9]{0,2}");
    }

    private BigDecimal getCurrentPayment() {
        String paymentText = paymentTextField.getText();
        paymentText = paymentText.replaceAll(",", "\\.");
        return new BigDecimal(paymentText);
    }

    private BillingReader getCurrentProviderReader() {
        if (playRadioButton.isSelected()) {
            return new PlayReader();
        } else {
            return new PlusReader();
        }
    }

    private boolean searchButtonState() {
        return !(fileEntered && paymentEntered);
    }

    private void handleLoadBillingButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "loading billing");
        billingFile = chooseFile();
        if (billingFile != null) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "file chosen: {0}", billingFile);
            filePathTextField.setText(billingFile.toString());
            fileEntered = true;
            searchOffersButton.setDisable(searchButtonState());
        } else {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "no file chosen");
            fileEntered = false;
        }
    }

    private void showAnalysisScreen() throws IOException {
        ScreenController screenController = ScreenController.createController(controllerManager, "/views/analysis_screen.fxml");
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
