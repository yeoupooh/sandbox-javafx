package com.subakstudio.mclauncher;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Thomas on 2/13/2016.
 */
public class Controller implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="textFieldUrl"
    private TextField textFieldUrl; // Value injected by FXMLLoader

    @FXML // fx:id="webView"
    private WebView webView; // Value injected by FXMLLoader

    @FXML // fx:id="buttonLaunchMinecraft"
    private Button buttonLaunchMinecraft; // Value injected by FXMLLoader

    @FXML // fx:id="buttonGo"
    private Button buttonGo; // Value injected by FXMLLoader

    @FXML
    void buttonLaunchMinecraftClicked(ActionEvent event) {

    }

    @FXML
    void buttonGoClicked(ActionEvent event) {
        System.out.println("url:" + textFieldUrl.getText());
        webView.getEngine().load(textFieldUrl.getText());
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert textFieldUrl != null : "fx:id=\"textFieldUrl\" was not injected: check your FXML file 'main2.fxml'.";
        assert buttonLaunchMinecraft != null : "fx:id=\"buttonLaunchMinecraft\" was not injected: check your FXML file 'main2.fxml'.";
        assert buttonGo != null : "fx:id=\"buttonGo\" was not injected: check your FXML file 'main2.fxml'.";

    }
}
