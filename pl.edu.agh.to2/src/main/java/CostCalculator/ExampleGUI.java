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


public class ExampleGUI extends Application {

    private Scene scene;
    private TableView<ExampleEntry> rankingTable;

    @Override
    public void start(Stage primaryStage) throws Exception {

        rankingTable = new TableView<ExampleEntry>();
        List<ExampleEntry> entriesList = new ArrayList<ExampleEntry>();
        entriesList.add(new ExampleEntry(1, "Orange", 49.63, 0.15, 0.20, 10));
        entriesList.add(new ExampleEntry(2, "T-Mobile", 77.54, 0.20, 0.30, 15));
        entriesList.add(new ExampleEntry(3, "Play", 88.34, 0.25, 0.30, 20));
        ObservableList<ExampleEntry> entries = FXCollections.observableList(entriesList);
        rankingTable.setItems(entries);

        TableColumn<ExampleEntry, Integer> idCol = new TableColumn<ExampleEntry, Integer>("Id");
        TableColumn<ExampleEntry, String> operatorNameCol = new TableColumn<ExampleEntry, String>("Operator");
        TableColumn<ExampleEntry, Integer> totalCostCol = new TableColumn<ExampleEntry, Integer>("Total cost");
        TableColumn<ExampleEntry, Integer> smsCostCol = new TableColumn<ExampleEntry, Integer>("SMS");
        TableColumn<ExampleEntry, Integer> minuteCostCol = new TableColumn<ExampleEntry, Integer>("Minute");
        TableColumn<ExampleEntry, Integer> internetCostCol = new TableColumn<ExampleEntry, Integer>("Internet");

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
