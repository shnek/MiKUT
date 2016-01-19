package CostCalculator.controllers;

import BillingReader.offers.Offer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ResultsScreenController extends ScreenController implements Initializable {

    public Text currentOfferPriceText;
    public Button exitButton;

    public TableView<TableEntry> tableView;
    public TableColumn<TableEntry, String> offerNameCol;
    public TableColumn<TableEntry, String> operatorCol;
    public TableColumn<TableEntry, Double> amountCol;
    public TableColumn<TableEntry, String> detailsCol;

    private Map<String, Offer> offersMap = new HashMap<>();
    private Map<Offer, BigDecimal> results;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //currentOfferDetailsButton.setOnAction(this::handleCurrentOfferDetailsButton);
        exitButton.setOnAction(this::handleFinishButton);

        offerNameCol.setCellValueFactory(new PropertyValueFactory<>("offerName"));
        operatorCol.setCellValueFactory(new PropertyValueFactory<>("operatorName"));
        amountCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        detailsCol.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getId()));
        detailsCol.setCellFactory(p -> new ButtonCell("Details"));
        detailsCol.setSortable(false);
    }

    public void fillScreen() {
        setCurrentOfferPriceText();
        tableView.getItems().setAll(getTableContent());
        checkBestOffer();
    }

    public void checkBestOffer() {
        BigDecimal bestOfferValue = this.controllerManager.getCalculator().getBestOfferValue(this.controllerManager.getResults());
        if (controllerManager.getCurrentPayment().compareTo(bestOfferValue) < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Best Offer!");
            alert.setContentText("Congratulations! You currently have the best offer!");
            alert.showAndWait();
        }
    }

    public void setCurrentOfferPriceText() {
        BigDecimal currentOfferPrice = this.controllerManager.getCurrentPayment();
        currentOfferPriceText.setText(String.format("%s zł", currentOfferPrice.toString()));
    }

    private class ButtonCell extends TableCell<TableEntry, String> {

        final Button cellButton = new Button();

        public ButtonCell(String buttonText) {
            cellButton.setText(buttonText);
            cellButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            cellButton.setOnAction(event -> {
                String id = getItem(); // id of the TableEntry
                Offer offer = offersMap.get(id);
                System.out.println(offer.toString());

                Stage window = new Stage();

                StackPane layout = new StackPane();
                VBox infoBox = new VBox(5);

                Text offerNameText = new Text("Offer:\t" + offer.getName());
                Text operatorText = new Text("Operator:\t" + offer.getOperator().getName());
                Text amountText = new Text("Your cost:\t" + results.get(offer).doubleValue());

                Text abonamentText = new Text("\n\nAbonament:\t" + (offer.isAbonament() ? "yes" : "no"));
                Text monthlyPaymentText = new Text("Monthly payment:\t" + offer.getMonthlyPayment());
                Text innerCallCostText = new Text("Inner call:\t" + offer.getInnerCallCost());
                Text outerCallCostText = new Text("Outer call:\t" + offer.getOuterCallCost());
                Text innerSmsCostText = new Text("Inner SMS:\t" + offer.getInnerSmsCost());
                Text outerSmsCostText = new Text("Outer SMS:\t" + offer.getOuterSmsCost());
                Text innerMmsCostText = new Text("Inner MMS:\t" + offer.getInnerMmsCost());
                Text outerMmsCostText = new Text("Outer MMS:\t" + offer.getOuterMmsCost());
                Text internetCostText = new Text("Internet MB:\t" + (offer.getInternetMbCost() == null ? "-" : offer.getInternetMbCost()));
                Text freeInternetText = new Text("Free Internet MB:\t" + offer.getFreeInternetMb() + "\n\n");

                Button closeButton = new Button("Close");
                closeButton.setOnAction(event1 -> window.close());

                infoBox.getChildren().addAll(offerNameText, operatorText, amountText, abonamentText, monthlyPaymentText, innerCallCostText, outerCallCostText, innerSmsCostText,outerSmsCostText,
                        innerMmsCostText, outerMmsCostText, internetCostText, freeInternetText, closeButton);

                layout.getChildren().add(infoBox);

                window.setTitle("Details");
                Scene scene = new Scene(layout, 300, 500);
                window.setScene(scene);
                window.show();
            });
        }

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    private List<TableEntry> getTableContent() {
        List<TableEntry> entries = new ArrayList<>();
        int counter = 0;

        results = this.controllerManager.getResults();

        for (Map.Entry<Offer, BigDecimal> entry : results.entrySet()) {
            Offer offer = entry.getKey();
            String id = Integer.toString(counter++);
            TableEntry tableEntry = new TableEntry();
            tableEntry.setId(id);
            tableEntry.setOfferName(offer.getName());
            tableEntry.setOperatorName(offer.getOperator().getName());
            tableEntry.setCost(entry.getValue().doubleValue());
            offersMap.put(id, offer);
            entries.add(tableEntry);
        }

        Collections.sort(entries, (o1, o2) -> {
            if (o1.getCost() < o2.getCost()) return -1;
            else if (o1.getCost() == o2.getCost()) return 0;
            else return 1;
        });

        return entries;
    }

    private void handleFinishButton(ActionEvent event) {
        this.controllerManager.getStage().close();
        Logger.getLogger(getClass().getName()).log(Level.INFO, "exiting application");
    }

    private void handleCurrentOfferDetailsButton(ActionEvent event) {
        Logger.getLogger(getClass().getName()).log(Level.INFO, "displaying details");
        try {
            createDetailsStage();
        } catch (IOException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "can't load new screen: {0}", e);
        }
    }

    private void createDetailsStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/details_screen.fxml"));
        Parent root = loader.load();
        DetailsScreenController controller = loader.getController();
        Stage stage = new Stage();
        Scene detailsScene = new Scene(root);
        controller.setStage(stage);
        stage.setTitle("MiKUT");
        stage.setScene(detailsScene);
        stage.show();
    }

    @Override
    public void setScene(Scene scene) {
        controllerManager.setResultsScene(scene);
    }

}
