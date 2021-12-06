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
