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
        TableColumn<RankingEntry, Integer> innerCallCostCol = new TableColumn<>("Inner call cost");
        TableColumn<RankingEntry, Integer> outerCallCostCol = new TableColumn<>("Outer call cost");
        TableColumn<RankingEntry, Integer> innerSmsCostCol = new TableColumn<>("Inner SMS cost");
        TableColumn<RankingEntry, Integer> outerSmsCostCol = new TableColumn<>("Outer SMS cost");
        TableColumn<RankingEntry, Integer> innerMmsCostCol = new TableColumn<>("Inner MMS cost");
        TableColumn<RankingEntry, Integer> outerMmsCostCol = new TableColumn<>("Outer MMS cost");
        TableColumn<RankingEntry, Integer> internetCostCol = new TableColumn<>("Internet Mb Cost");

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        operatorNameCol.setCellValueFactory(new PropertyValueFactory("operatorName"));
        totalCostCol.setCellValueFactory(new PropertyValueFactory("totalCost"));
        innerCallCostCol.setCellValueFactory(new PropertyValueFactory("innerCallCost"));
        outerCallCostCol.setCellValueFactory(new PropertyValueFactory("outerCallCost"));
        innerSmsCostCol.setCellValueFactory(new PropertyValueFactory("innerSmsCost"));
        outerSmsCostCol.setCellValueFactory(new PropertyValueFactory("outerSmsCost"));
        innerMmsCostCol.setCellValueFactory(new PropertyValueFactory("innerMmsCost"));
        outerMmsCostCol.setCellValueFactory(new PropertyValueFactory("outerMmsCost"));
        internetCostCol.setCellValueFactory(new PropertyValueFactory("internetCost"));

        rankingTable.getColumns().setAll(idCol, operatorNameCol, totalCostCol, innerCallCostCol, outerCallCostCol,
                innerSmsCostCol, outerSmsCostCol, innerMmsCostCol, outerMmsCostCol, internetCostCol);

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
                    cost.doubleValue(),
                    offer.getInnerCallCost().doubleValue(),
                    offer.getOuterCallCost().doubleValue(),
                    offer.getInnerSmsCost().doubleValue(),
                    offer.getOuterSmsCost().doubleValue(),
                    offer.getInnerMmsCost().doubleValue(),
                    offer.getOuterMmsCost().doubleValue(),
                    offer.getInternetMbCost().doubleValue());
            this.entriesList.add(rankingEntry);
            it.remove();
        }

        launch();
    }

}
