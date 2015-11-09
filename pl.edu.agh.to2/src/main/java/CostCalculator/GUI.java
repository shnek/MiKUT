package CostCalculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class GUI extends Application {

    private Scene scene;
    private TableView<SampleEntry> rankingTable;

    @Override
    public void start(Stage primaryStage) throws Exception {

        rankingTable = new TableView<SampleEntry>();
        List<SampleEntry> entriesList = new ArrayList<SampleEntry>();
        entriesList.add(new SampleEntry(1, "Orange", 49.63, 0.15, 0.20, 10));
        entriesList.add(new SampleEntry(2, "T-Mobile", 77.54, 0.20, 0.30, 15));
        entriesList.add(new SampleEntry(3, "Play", 88.34, 0.25, 0.30, 20));
        ObservableList<SampleEntry> entries = FXCollections.observableList(entriesList);
        rankingTable.setItems(entries);

        TableColumn<SampleEntry, Integer> idCol = new TableColumn<SampleEntry, Integer>("Id");
        TableColumn<SampleEntry, String> operatorNameCol = new TableColumn<SampleEntry, String>("Operator");
        TableColumn<SampleEntry, Integer> totalCostCol = new TableColumn<SampleEntry, Integer>("Total cost");
        TableColumn<SampleEntry, Integer> smsCostCol = new TableColumn<SampleEntry, Integer>("SMS");
        TableColumn<SampleEntry, Integer> minuteCostCol = new TableColumn<SampleEntry, Integer>("Minute");
        TableColumn<SampleEntry, Integer> internetCostCol = new TableColumn<SampleEntry, Integer>("Internet");

        idCol.setCellValueFactory(new PropertyValueFactory("id"));
        operatorNameCol.setCellValueFactory(new PropertyValueFactory("operatorName"));
        totalCostCol.setCellValueFactory(new PropertyValueFactory("totalCost"));
        smsCostCol.setCellValueFactory(new PropertyValueFactory("smsCost"));
        minuteCostCol.setCellValueFactory(new PropertyValueFactory("minuteCost"));
        internetCostCol.setCellValueFactory(new PropertyValueFactory("internetCost"));

        rankingTable.getColumns().setAll(idCol, operatorNameCol, totalCostCol, smsCostCol, minuteCostCol, internetCostCol);

        scene = new Scene(rankingTable, 500, 200);

        primaryStage.setTitle("MiKUT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
