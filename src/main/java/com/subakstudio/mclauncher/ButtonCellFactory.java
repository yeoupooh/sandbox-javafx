package com.subakstudio.mclauncher;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Created by yeoupooh on 1/24/16.
 */
public class ButtonCellFactory implements Callback<TableColumn<Item, String>, TableCell<Item, String>> {

    @Override
    public TableCell<Item, String> call(TableColumn<Item, String> param) {
        final TableCell<Item, String> cell = new TableCell<Item, String>() {

            final Button btn = new Button("Click Me");

            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction((ActionEvent event) ->
                    {
                        Item person = getTableView().getItems().get(getIndex());
                        System.out.println(person.getName());
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        };
        return cell;
    }
}
