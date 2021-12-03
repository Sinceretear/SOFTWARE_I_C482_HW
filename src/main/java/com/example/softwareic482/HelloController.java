package com.example.softwareic482;

import com.example.softwareic482.controller.ModifyPartController;
import com.example.softwareic482.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HelloController implements Initializable {

    private static Part partToModify;
    private Alert alert = new Alert(Alert.AlertType.ERROR);

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
    private TextField queryParts;
    @FXML
    private TextField queryProducts;
    @FXML
    private Navigation nav = new Navigation();

    @FXML
    private TableView<Part> PartTableView;
    @FXML
    private TableColumn<Part, Integer> partID;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> inventoryLevel;
    @FXML
    private TableColumn<Part, Double> pricePerUnit;
    @FXML
    private TableView<Product> ProductTableView;
    @FXML
    private TableColumn<Product,Integer> productID;
    @FXML
    private TableColumn<Part, String> productName;
    @FXML
    private TableColumn<Part, Integer> productInventoryLevel;
    @FXML
    private TableColumn<Part, Double> productPricePerUnit;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTables();
    }

    public HelloController() {
    }

    @FXML
    protected void addPartClicked(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/addpartF.fxml", event);
    }


    @FXML
    protected void modifyPartClicked(ActionEvent event) throws IOException {
        if (PartTableView.getSelectionModel().getSelectedItem() == null) {
            showAlertModifyMsg("part");
        } else {
            Part selectedPart = PartTableView.getSelectionModel().getSelectedItem();
            nav.goToModifyPartController(selectedPart,"src/main/java/com/example/softwareic482/views/modifyPartForm.fxml", event);
        }

    }

    @FXML
    protected void deletePartClicked() {
        if (PartTableView.getSelectionModel().getSelectedItem() == null) {
            showAlertMsg("part");
        } else {
            PartTableView.getItems().removeAll(PartTableView.getSelectionModel().getSelectedItem());
        }
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
        if (ProductTableView.getSelectionModel().getSelectedItem() == null) {
            showAlertMsg("product");
        } else {
            ProductTableView.getItems().removeAll(ProductTableView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    protected void exitProgram() {
        System.exit(0);
    }

    @FXML
    protected void getSearchResultsForParts(ActionEvent event) throws IOException {
        String q = queryParts.getText();
        ObservableList<Part> parts = searchbyPartName(q);
        PartTableView.setItems(parts);
        if (parts.isEmpty()) {
            showAlertForSearch();
        }
    }

    private ObservableList<Part> searchbyPartName(String partialName) {
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();
        for(Part pt : allParts) {
            if(pt.getName().contains(partialName) || String.valueOf(pt.getId()).contains(partialName)) {
                namedParts.add(pt);
            }
        }
        return namedParts;
    }

    @FXML
    protected void getSearchResultsForProducts(ActionEvent event) throws IOException {
        String q = queryProducts.getText();
        ObservableList<Product> product = searchbyProductName(q);
        ProductTableView.setItems(product);
        if (product.isEmpty()) {
            showAlertForSearch();
        }
    }

    private ObservableList<Product> searchbyProductName(String partialName) {
        ObservableList<Product> namedProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(Product pd : allProducts) {
            if(pd.getName().contains(partialName) || String.valueOf(pd.getId()).contains(partialName)) {
                namedProducts.add(pd);
            }
        }
        return namedProducts;
    }

    private void showAlertMsg(String item) {
        alert.setTitle("Error");
        alert.setContentText("Please choose a %s to delete.".formatted(item));;
        alert.showAndWait();
    }

    private void showAlertModifyMsg(String item) {
        alert.setTitle("Error");
        alert.setContentText("Please choose a %s to modify.".formatted(item));;
        alert.showAndWait();
    }

    private void showAlertForSearch() {
        alert.setTitle("Error");
        alert.setContentText("No Results (Search results are case sensitive)");
        alert.showAndWait();
    }

    private void populateTables() {
        PartTableView.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        ProductTableView.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}