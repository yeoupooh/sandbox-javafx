package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebView;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BooleanSupplier;

public class Controller implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML // fx:id="webview"
    private WebView webview; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Item> table; // Value injected by FXMLLoader

    @FXML // fx:id="nameCol"
    private TableColumn nameCol; // Value injected by FXMLLoader

    @FXML // fx:id="enabledCol"
    private TableColumn enabledCol; // Value injected by FXMLLoader

    @FXML // fx:id="actionCol"
    private TableColumn actionCol; // Value injected by FXMLLoader

    @FXML
    public void buttonClicked(ActionEvent ae) {
        System.out.println("clicked");
        webview.getEngine().load("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-tools/2602357-mclauncher-1-4-easy-mod-forge-installer-and");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'main.fxml'.";
//        assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'main.fxml'.";
//        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'main.fxml'.";
//        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'main.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'main.fxml'.";
        assert webview != null : "fx:id=\"webview\" was not injected: check your FXML file 'main.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'main.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'main.fxml'.";
        assert enabledCol != null : "fx:id=\"enabledCol\" was not injected: check your FXML file 'main.fxml'.";
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'main.fxml'.";

        nameCol.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        enabledCol.setCellValueFactory(new PropertyValueFactory<Item, Boolean>("enabled"));
        enabledCol.setCellFactory(CheckBoxTableCell.forTableColumn(enabledCol));
        actionCol.setCellValueFactory(new PropertyValueFactory<Item, String>("action"));
        actionCol.setCellFactory(new ButtonCellFactory());

        table.setItems(FXCollections.observableArrayList(
                new Item("Name1", true, "Action1"),
                new Item("Name2", false, "Action2"),
                new Item("Name3", true, "Action3")
        ));
    }
}
