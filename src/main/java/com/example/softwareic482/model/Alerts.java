package com.example.softwareic482.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

    public Alert alert = new Alert(Alert.AlertType.ERROR);
    public Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);

    public void showAlertForSearch() {
        alert.setTitle("Error");
        alert.setContentText("No Results (Search results are case sensitive)");
        alert.showAndWait();
    }

    public void showAlertMsg(String item) {
        alert.setTitle("Error");
        alert.setContentText("Please choose a %s to add.".formatted(item));;
        alert.showAndWait();
    }

    public void showAlertMsg() {
        alert.setTitle("Error");
        alert.setContentText("Please enter correct min & max values");
        alert.showAndWait();
    }

    public void showAlertForAssociatedParts() {
        alert.setTitle("Error");
        alert.setContentText("Product cannot be deleted with associated parts");
        alert.showAndWait();
    }

    public void showAlertInputValid() {
        alert.setTitle("Error");
        alert.setContentText("Name must be a string" + "\n" + "Inventory must be an integer value"
                + "\n" + "Price must be a decimal value" + "\n" + "Min must be an integer value"
                + "\n" + "Max must be an integer value" + "\n" + "Machine ID must be an integer value" + "\n"
                + "Company name must be a string");
        alert.showAndWait();
    }

    public void showAlertModifyMsg(String item) {
        alert.setTitle("Error");
        alert.setContentText("Please choose a %s to modify.".formatted(item));;
        alert.showAndWait();
    }

    public boolean showconfirmationAlert() {
        alert.setTitle("Alert");
        alert.setContentText("Delete this Part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    public boolean showconfirmationCancelAlert() {
        alert.setTitle("Alert");
        alert.setContentText("Cancel and return Home?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
}
