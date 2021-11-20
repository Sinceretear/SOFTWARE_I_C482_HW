package com.example.softwareic482.controller;

import com.example.softwareic482.model.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {


    @FXML
    private Button cancel;

    @FXML
    Navigation nav = new Navigation();

    public ModifyPartController() {
    }


    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}

