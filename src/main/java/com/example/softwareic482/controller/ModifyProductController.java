package com.example.softwareic482.controller;

import com.example.softwareic482.model.Navigation;
import com.example.softwareic482.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductController implements Initializable {


    @FXML
    private Button cancel;

    @FXML
    Navigation nav = new Navigation();

    public ModifyProductController() {
    }


    @FXML
    public void setItems(Part part) {
        System.out.println("set items in modify product");
    }

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
