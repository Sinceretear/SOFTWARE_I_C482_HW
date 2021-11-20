package com.example.softwareic482;

import com.example.softwareic482.model.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class HelloController implements Initializable {

    @FXML
    private Button addPartButton;
    @FXML
    private Button modifyPartButton;
    @FXML
    private Button deletePartButton;
    @FXML
    private Button addProductButton;
    @FXML
    private Button modifyProductButton;
    @FXML
    private Button deleteProductButton;
    @FXML
    private Button exit;
    @FXML
    private Navigation nav = new Navigation();

    public HelloController() {
    }


    @FXML
    protected void addPartClicked(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/addpartF.fxml", event);
    }

    @FXML
    protected void modifyPartClicked(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/modifyPartForm.fxml", event);
    }

    @FXML
    protected void deletePartClicked() {
        deletePartButton.setText("Fuckd");
    }

    @FXML
    protected void addProductClicked(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/addProductForm.fxml", event);
    }

    @FXML
    protected void modifyProductClicked(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/modifyProductForm.fxml", event);
    }

    @FXML
    protected void deleteProductClicked() {
        deleteProductButton.setText("Fuckd");
    }

    @FXML
    protected void exitProgram() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}