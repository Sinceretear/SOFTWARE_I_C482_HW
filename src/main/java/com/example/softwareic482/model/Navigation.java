package com.example.softwareic482.model;

import com.example.softwareic482.controller.ModifyPartController;
import com.example.softwareic482.controller.ModifyProductController;
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

/**
 * Navigation is used a lot in our application. It makes sense to separate this logic to its own area.
 */

public class Navigation {

    /**
     * generic navigation where a url is input and will navigate to that url
     */
    @FXML
    public void sceneToGoTo(String nameOfFile, ActionEvent event) throws IOException {
        URL url = new File(nameOfFile).toURI().toURL();
        Parent addPartVC = FXMLLoader.load(url);
        Scene scene = new Scene(addPartVC);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /**
     * Initializes the controller and calls setItems before navigating to this url
     * this allows us to populate the data before showing the screen.
     */
    public void goToModifyPartController(Part part, String nameOfFile, ActionEvent event) throws IOException {
        try{
            URL url = new File(nameOfFile).toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            ModifyPartController controller = loader.getController();
            controller.setItems(part);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex2){}
    }

    /**
     * Same functionality as above.
     * RUNTIME ERROR - had lots of trouble figuring out why my ModifyProductController
     * wasn't being instantiated.
     * FIX: Connect all FXML components with the correct names and connections in scenebuilder.
     */
    public void goToModifyProductController(Product product, String nameOfFile, ActionEvent event) throws IOException {
        try{
            URL url = new File(nameOfFile).toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();
            ModifyProductController controller = loader.getController();
            controller.setItems(product);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex2){
            System.out.println(ex2);
        }
    }
}
