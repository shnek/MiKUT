package CostCalculator.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloScreenController implements Initializable {

    private Stage rootStage;
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
            Pane analysisScreen = FXMLLoader.load(getClass().getResource("/views/analysis_screen.fxml"));
            rootStage.setScene(new Scene(analysisScreen));
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    private void handleLoadBillingButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "loading billing");
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Billing files (*.pdf, *.csv)", "*.pdf", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(rootStage);
        Logger.getLogger(getClass().getName()).log(Level.INFO, "file chosen: {0}", file);
        filePathTextField.setText(file.toString());
        // todo: pass file to BillingReader
    }


    public void populate(Stage rootStage) {
        this.rootStage = rootStage;
    }
}
