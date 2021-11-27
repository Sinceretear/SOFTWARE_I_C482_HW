package com.example.softwareic482.controller;

import com.example.softwareic482.HelloController;
import com.example.softwareic482.model.InHouse;
import com.example.softwareic482.model.Navigation;
import com.example.softwareic482.model.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    private Part selectedPart;

    @FXML
    private Button cancel;
    @FXML
    private Button savePart;
    @FXML
    private TextField partID;
    @FXML
    private TextField partName;
    @FXML
    private TextField partInv;
    @FXML
    private TextField partPrice;
    @FXML
    private TextField partMax;
    @FXML
    private TextField partMin;
    @FXML
    private TextField partMachineID;
    @FXML
    private RadioButton partInHouse;
    @FXML
    private RadioButton partOutsourced;
    @FXML
    private Label machineOrCompany;


    @FXML
    Navigation nav = new Navigation();

    public ModifyPartController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void sendPart(Part part) {
        partName.setText(InHouse.class.getName());
    }

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
    }



}

