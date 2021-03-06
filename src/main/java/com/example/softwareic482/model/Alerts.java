package com.example.softwareic482.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Many alerts are used in our application. To prevent copy pasta we've created their own class.
 * These alerts can be used anywhere by creating an alert object and calling the appropriate method.
 */

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
        alert.setContentText("Please choose " + item + " to add.");
        alert.showAndWait();
    }

    public void showAlertDeleteMsg(String item) {
        alert.setTitle("Error");
        alert.setContentText("Please choose " + item + " to delete.");
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
        alert.setContentText("Please choose a %s to modify.".format(item));
        alert.showAndWait();
    }

    public boolean showconfirmationAlert(String item) {
        alert.setTitle("Alert");
        alert.setContentText("Delete this " + item + "?");
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
