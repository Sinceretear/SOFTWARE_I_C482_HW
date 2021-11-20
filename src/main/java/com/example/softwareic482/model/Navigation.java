package com.example.softwareic482.model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;



public class Navigation {

    @FXML
    public void sceneToGoTo(String nameOfFile, ActionEvent event) throws IOException {
        URL url = new File(nameOfFile).toURI().toURL();
        Parent addPartVC = FXMLLoader.load(url);
        Scene scene = new Scene(addPartVC);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }


}
