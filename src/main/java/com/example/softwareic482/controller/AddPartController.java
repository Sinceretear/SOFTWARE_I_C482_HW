package com.example.softwareic482.controller;

import com.example.softwareic482.model.InHouse;
import com.example.softwareic482.model.Inventory;
import com.example.softwareic482.model.Navigation;
import com.example.softwareic482.model.Outsourced;
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

public class AddPartController implements Initializable {


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

    public AddPartController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
    }

    @FXML
    protected void outsourcedSelected(ActionEvent event) throws IOException {
        if (partOutsourced.isSelected()) {
            partInHouse.setSelected(false);
            machineOrCompany.setText("Company Name");
        }
    }

    @FXML
    protected void inHouseSelected(ActionEvent event) throws IOException {
        if (partInHouse.isSelected()) {
            machineOrCompany.setText("Machine ID");
            partOutsourced.setSelected(false);
        }
    }

    @FXML
    protected void savePart(ActionEvent event) throws IOException {
        int random = (int )(Math.random() * 99 + 1);
        String name = partName.getText();
        int inventory = Integer.parseInt(partInv.getText());
        double price = Double.parseDouble(partPrice.getText());
        int max = Integer.parseInt(partMax.getText());
        int min = Integer.parseInt(partMin.getText());

        if (partInHouse.isSelected()) {
            int machineID = Integer.parseInt(partMachineID.getText());
            InHouse newPart = new InHouse(random,name,price,inventory,min,max,machineID);
            Inventory.addPart(newPart);
            nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
        } else if (partOutsourced.isSelected()) {
            String companyName = partMachineID.getText();
            Outsourced newPart = new Outsourced(random,name,price,inventory,min,max,companyName);
            Inventory.addPart(newPart);
            nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
        } else {
            // alert please selected a category
        }


    }



}
