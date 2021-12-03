package com.example.softwareic482.controller;

import com.example.softwareic482.HelloController;
import com.example.softwareic482.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {

    private Part selectedPart;
    private Alert alert = new Alert(Alert.AlertType.ERROR);

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
    public void setItems(Part part) {
        if (part instanceof InHouse) {
            partInHouse.setSelected(true);
            machineOrCompany.setText("Machine ID");
            partMachineID.setText(((InHouse) part).getMachineId());
        }
        if (part instanceof Outsourced) {
            partOutsourced.setSelected(true);
            machineOrCompany.setText("Company Name");
            partMachineID.setText(((Outsourced) part).getCompanyName());
        }

        partName.setText(part.getName());
        partID.setText(part.getId());
        partInv.setText(part.getStock());
        partPrice.setText(part.getPrice());
        partMin.setText(part.getMin());
        partMax.setText(part.getMax());

        selectedPart = part;
    }

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
    }

    @FXML
    protected void modifyPart(ActionEvent event) throws IOException {
        Inventory.deletePart(selectedPart);

        String name = partName.getText();
        int id = Integer.parseInt(partID.getText());
        int inventory = Integer.parseInt(partInv.getText());
        double price = Double.parseDouble(partPrice.getText());
        int max = Integer.parseInt(partMax.getText());
        int min = Integer.parseInt(partMin.getText());

        if ( (partInHouse.isSelected()) && (inventory < max && min < max) ) {
            int machineID = Integer.parseInt(partMachineID.getText());
            InHouse newPart = new InHouse(id,name,price,inventory,min,max,machineID);
            Inventory.addPart(newPart);
            nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
        }  else if ( (partOutsourced.isSelected()) && (inventory < max && min < max) ) {
            String companyName = partMachineID.getText();
            Outsourced newPart = new Outsourced(id,name,price,inventory,min,max,companyName);
            Inventory.addPart(newPart);
            nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
        } else {
            showAlertMsg();
        }
    }

    private void showAlertMsg() {
        alert.setTitle("Error");
        alert.setContentText("Please enter correct min & max values");
        alert.showAndWait();
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

}

