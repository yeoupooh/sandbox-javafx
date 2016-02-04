package com.subakstudio.mclauncher;

import com.subakstudio.http.OkHttpClientHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebView;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="button"
    private Button button; // Value injected by FXMLLoader

    @FXML // fx:id="enabledCol"
    private TableColumn<Item, Boolean> enabledCol; // Value injected by FXMLLoader

    @FXML // fx:id="textFieldUrl"
    private TextField textFieldUrl; // Value injected by FXMLLoader

    @FXML // fx:id="webview"
    private WebView webview; // Value injected by FXMLLoader

    @FXML // fx:id="nameCol"
    private TableColumn<Item, String> nameCol; // Value injected by FXMLLoader

    @FXML // fx:id="x1"
    private Font x1; // Value injected by FXMLLoader

    @FXML // fx:id="x2"
    private Color x2; // Value injected by FXMLLoader

    @FXML // fx:id="actionCol"
    private TableColumn<Item, String> actionCol; // Value injected by FXMLLoader

    @FXML // fx:id="x3"
    private Font x3; // Value injected by FXMLLoader

    @FXML // fx:id="x4"
    private Color x4; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Item> table; // Value injected by FXMLLoader
    private Logger log;
    private CookieManager cookieManager;

    @FXML
    void buttonGoClicked(ActionEvent event) {
        webview.getEngine().load(textFieldUrl.getText());
    }

    @FXML
    public void buttonClicked(ActionEvent ae) {
        System.out.println("clicked");
        webview.getEngine().load("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-tools/2602357-mclauncher-1-4-easy-mod-forge-installer-and");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'main.fxml'.";
        assert enabledCol != null : "fx:id=\"enabledCol\" was not injected: check your FXML file 'main.fxml'.";
        assert textFieldUrl != null : "fx:id=\"textFieldUrl\" was not injected: check your FXML file 'main.fxml'.";
        assert webview != null : "fx:id=\"webview\" was not injected: check your FXML file 'main.fxml'.";
        assert nameCol != null : "fx:id=\"nameCol\" was not injected: check your FXML file 'main.fxml'.";
        assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'main.fxml'.";
        assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'main.fxml'.";
        assert actionCol != null : "fx:id=\"actionCol\" was not injected: check your FXML file 'main.fxml'.";
        assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'main.fxml'.";
        assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'main.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'main.fxml'.";

        setupLogger();
        setupTable();
        setupWebView();
    }

    private void setupLogger() {
        log = Logger.getLogger("Controller");
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        log.addHandler(handler);
        log.setLevel(Level.ALL);
    }

    private void setupWebView() {
        cookieManager = new java.net.CookieManager();
        CookieHandler.setDefault(cookieManager);
        webview.getEngine().locationProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                log.finest(newValue.toString());
                if (newValue instanceof String) {
                    String url = (String) newValue;
                    if (url.startsWith("https://dl.dropboxusercontent.com/content_link/")) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Download File");
                        alert.setHeaderText("Download a file from " + url);
                        alert.setContentText("Do you want to download this into mods folder?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            log.finest("Downloading..." + url);
                            downloadFile(url);
                        } else {
                            // ... user chose CANCEL or closed the dialog
                            log.finest("Canceled");
                        }
                    }
                }
            }
        });
    }

    private void downloadFile(String url) {
        for (HttpCookie cookie : cookieManager.getCookieStore().getCookies()) {
            log.finest(cookie.toString());
        }

        OkHttpClientHelper httpClient = new OkHttpClientHelper();
        try {
            httpClient.downloadBinary(url, new File("test.bin"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupTable() {
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
