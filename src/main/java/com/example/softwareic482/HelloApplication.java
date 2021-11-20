package com.example.softwareic482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL url = new File("src/main/java/com/example/softwareic482/views/mainForm.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 1200, 400);
        stage.setTitle("Inventory Management System - Home");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}