package com.example.softwareic482;

import com.example.softwareic482.model.InHouse;
import com.example.softwareic482.model.Inventory;
import com.example.softwareic482.model.Part;
import com.example.softwareic482.model.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        URL url = new File("src/main/java/com/example/softwareic482/views/mainForm.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 1200, 400);
        stage.setTitle("Inventory Management System - Home");
        stage.setScene(scene);
        stage.show();
    }

    private static void populateParts() {
        InHouse part1 = new InHouse(1,"tire",156.73,14,4,999, 12);
        Inventory.addPart(part1);
        InHouse part2 = new InHouse(2,"engine",7156.73,414,1,999, 82);
        Inventory.addPart(part2);
        InHouse part3 = new InHouse(3,"screen",456.73,317,2,999, 64);
        Inventory.addPart(part3);
    }

    private static void populateProducts() {
        Product product1 = new Product(1,"Car",1776.73,6,2,999);
        Inventory.addProduct(product1);
        Product product2 = new Product(2,"Boat",19096.73,121,3,999);
        Inventory.addProduct(product2);
        Product product3 = new Product(3,"ATV",12763.73,87,4,999);
        Inventory.addProduct(product3);
    }


    public static void main(String[] args) {
        populateParts();
        populateProducts();
        launch();
    }



}