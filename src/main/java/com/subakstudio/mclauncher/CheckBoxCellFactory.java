package com.subakstudio.mclauncher;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

/**
 * Created by yeoupooh on 1/24/16.
 */
public class CheckBoxCellFactory implements Callback<TableColumn<Item, Boolean>, TableCell<Item, Boolean>> {
    public TableCell<Item, Boolean> call(TableColumn<Item, Boolean> param) {
        CheckBoxTableCell<Item, Boolean> checkBoxCell = new CheckBoxTableCell();
        return checkBoxCell;
    }
}
