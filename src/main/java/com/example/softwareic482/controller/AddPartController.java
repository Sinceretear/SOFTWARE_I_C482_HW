package com.example.softwareic482.controller;

import com.example.softwareic482.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Business logic for add part screen
 */

public class AddPartController implements Initializable {

    private int partIDNum;
    private Alerts alert = new Alerts();

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

    /**
     * Sets part ID
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int random = (int)(Math.random() * 99 + 1);
        partIDNum = random;
        partID.setText(String.valueOf(random));
    }

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
    }

    /**
     * Controls raido buttons
     */
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

    /**
     * Saves part to inventory
     */
    @FXML
    protected void savePart(ActionEvent event) throws IOException {
        try {
            String name = partName.getText();
            int inventory = Integer.parseInt(partInv.getText());
            double price = Double.parseDouble(partPrice.getText());
            int max = Integer.parseInt(partMax.getText());
            int min = Integer.parseInt(partMin.getText());

            if ( (partInHouse.isSelected()) && (inventory < max && min < max && inventory > min)) {
                int machineID = Integer.parseInt(partMachineID.getText());
                InHouse newPart = new InHouse(partIDNum,name,price,inventory,min,max,machineID);
                Inventory.addPart(newPart);
                nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
            }  else if ( (partOutsourced.isSelected()) && (inventory < max && min < max && inventory > min)) {
                String companyName = partMachineID.getText();
                Outsourced newPart = new Outsourced(partIDNum,name,price,inventory,min,max,companyName);
                Inventory.addPart(newPart);
                nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
            } else {
                alert.showAlertMsg();
            }
        } catch(Exception e) {
            alert.showAlertInputValid();
        }
    }

}
