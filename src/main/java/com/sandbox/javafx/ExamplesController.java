package com.sandbox.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by yeoupooh on 2/16/16.
 */
public class ExamplesController implements Initializable {
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="buttonMediaPlayerExample"
    private Button buttonMediaPlayerExample; // Value injected by FXMLLoader

    @FXML
    public void buttonMediaPlayerExampleClicked(ActionEvent event) {
        try {
            showWindow("hi");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showWindow(String message) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("media-player.fxml"));
        loader.setController(new MediaPlayerController(message));
        final Parent root = loader.load();
        final Scene scene = new Scene(root, 500, 400);
        Stage stage = new Stage();
        stage.setTitle("MediaPlayer Example");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.DECORATED);
        stage.initOwner(buttonMediaPlayerExample.getScene().getWindow());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    @Override
    // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL location, ResourceBundle resources) {
        assert buttonMediaPlayerExample != null : "fx:id=\"buttonMediaPlayerExample\" was not injected: check your FXML file 'examples.fxml'.";

    }
}
