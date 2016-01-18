package CostCalculator.controllers;

import BillingReader.offers.Offer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public Button currentOfferDetailsButton;
    public Button exitButton;

    public TableView<TableEntry> tableView;
    public TableColumn<TableEntry, String> offerNameCol;
    public TableColumn<TableEntry, String> operatorCol;
    public TableColumn<TableEntry, Double> amountCol;
    public TableColumn<TableEntry, String> detailsCol;

    private Map<String, Offer> offersMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentOfferDetailsButton.setOnAction(this::handleCurrentOfferDetailsButton);
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
    }

    public void setCurrentOfferPriceText() {
        BigDecimal currentOfferPrice = this.controllerManager.getCurrentPayment();
        currentOfferPriceText.setText(String.format("%s zł", currentOfferPrice.toString()));
    }

    private class ButtonCell extends TableCell<TableEntry, String> {

        final Button cellButton = new Button();

        public ButtonCell(String buttonText) {
            cellButton.setText(buttonText);

            cellButton.setOnAction(event -> {
                String id = getItem(); // id of the TableEntry
                Offer offer = offersMap.get(id);
                System.out.println(offer.toString());
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

        Map<Offer, BigDecimal> results = this.controllerManager.getResults();

        for (Map.Entry<Offer, BigDecimal> entry : results.entrySet()) {
            Offer offer = entry.getKey();
            String id = Integer.toString(counter++);
            TableEntry tableEntry = new TableEntry();
            tableEntry.setId(id);
            tableEntry.setOfferName(offer.getName());
            tableEntry.setOperatorName(offer.getOperator().getName());
            tableEntry.setCost(entry.getValue().doubleValue());
            offersMap.put(id, offer);
        }

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
