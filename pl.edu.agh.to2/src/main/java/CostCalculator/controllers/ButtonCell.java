package CostCalculator.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class ButtonCell extends TableCell<TableEntry, String> {

    final Button cellButton = new Button();

    public ButtonCell(String buttonText) {
        cellButton.setText(buttonText);

        cellButton.setOnAction(event -> {
            String id = getItem(); // id of the TableEntry
            // todo: show details
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
