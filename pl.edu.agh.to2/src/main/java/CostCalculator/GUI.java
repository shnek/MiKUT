package CostCalculator;

import BillingReader.Offer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class GUI extends Application {

    private Scene scene;
    private TableView<RankingEntry> rankingTable;
    private List<RankingEntry> entriesList = new ArrayList<RankingEntry>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        rankingTable = new TableView<RankingEntry>();
        List<RankingEntry> entriesList = new ArrayList<RankingEntry>();
        ObservableList<RankingEntry> entries = FXCollections.observableList(entriesList);
        rankingTable.setItems(entries);

        TableColumn<RankingEntry, Integer> idCol = new TableColumn<>("Id");
        TableColumn<RankingEntry, String> operatorNameCol = new TableColumn<>("Operator");
        TableColumn<RankingEntry, Integer> totalCostCol = new TableColumn<>("Total cost");

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        operatorNameCol.setCellValueFactory(new PropertyValueFactory("operatorName"));
        totalCostCol.setCellValueFactory(new PropertyValueFactory("totalCost"));

        rankingTable.getColumns().setAll(idCol, operatorNameCol, totalCostCol);

        scene = new Scene(rankingTable, 500, 200);

        primaryStage.setTitle("MiKUT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void run(Map<Offer, BigDecimal> calculatedOffers) {
        Iterator it = calculatedOffers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Offer offer = (Offer) pair.getKey();
            BigDecimal cost = (BigDecimal) pair.getValue();
            RankingEntry rankingEntry = new RankingEntry(
                    entriesList.size(),
                    offer.getOperator().getName(),
                    cost.doubleValue());
            this.entriesList.add(rankingEntry);
            it.remove();
        }

        launch();
    }

}
