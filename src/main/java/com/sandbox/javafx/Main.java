package com.sandbox.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

/**
 * Created by yeoupooh on 2/16/16.
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getProtectionDomain().getCodeSource().getLocation());
        Parent root = fxmlLoader.load(getClass().getClassLoader().getResource("examples.fxml").openStream());
        primaryStage.setTitle("Sandbox for JavaFX");
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
