package com.example.softwareic482.controller;

import com.example.softwareic482.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contains business logic for add product screen
 */

public class AddProductController implements Initializable {

    private int partIDNum;
    private Alerts alert = new Alerts();

    /**
     * Holds associated parts for this product
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    @FXML
    private Button cancel;
    @FXML
    private Button saveProduct;
    @FXML
    private Button addProductButton;
    @FXML
    private Button removeAssociatedProductButton;

    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productInv;
    @FXML
    private TextField productPrice;
    @FXML
    private TextField productMax;
    @FXML
    private TextField productMin;

    @FXML
    private TextField queryParts;

    @FXML
    private TableView<Part> PartTableView;
    @FXML
    private TableColumn<Product,Integer> productID;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevel;
    @FXML
    private TableColumn<Product, Double> productPricePerUnit;

    @FXML
    private TableView<Part> AssociatedPartsTableView;
    @FXML
    private TableColumn<Part,Integer> AssociatedProductID;
    @FXML
    private TableColumn<Part, String> AssociatedProductName;
    @FXML
    private TableColumn<Part, Integer> AssociatedProductInventoryLevel;
    @FXML
    private TableColumn<Part, Double> AssociatedProductPricePerUnit;

    @FXML
    Navigation nav = new Navigation();

    public AddProductController() {
    }

    /**
     * Creates Part ID and setup table views
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int random = (int)(Math.random() * 99 + 1);
        partIDNum = random;
        productIDField.setText(String.valueOf(random));

        populateTables();
    }

    private Part createPart(Product selectedPart) {
        String toPartName = selectedPart.getName();
        int toPartID = selectedPart.getId();
        double toPriceName = selectedPart.getPrice();
        int toStock = selectedPart.getStock();
        int toMin = selectedPart.getMin();
        int toMax = selectedPart.getMax();

        InHouse assPartToBeAdded = new InHouse(toPartID,toPartName,toPriceName,toStock,toMin,toMax, toPartID);

        return assPartToBeAdded;
    }

    /**
     * Adds associated part to product array
     */
    @FXML
    protected void addPart(ActionEvent event) throws IOException {
        if(PartTableView.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = PartTableView.getSelectionModel().getSelectedItem();
            assocParts.add(selectedPart);
            AssociatedPartsTableView.setItems(assocParts);
        } else {
            alert.showAlertMsg("part");
        }
    }

    /**
     * removes an associated part
     */
    @FXML
    protected void removeAssociatedPart(ActionEvent event) throws IOException {
        if (AssociatedPartsTableView.getSelectionModel().getSelectedItem() == null) {
            alert.showAlertDeleteMsg("part");
        } else {
            AssociatedPartsTableView.getItems().remove(AssociatedPartsTableView.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Adds a new product to inventory and saves its associated parts to the product
     */
    @FXML
    protected void save(ActionEvent event) throws IOException {
        try {
            String name = productNameField.getText();
            int inventory = Integer.parseInt(productInv.getText());
            double price = Double.parseDouble(productPrice.getText());
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());

            if ((inventory < max && min < max && inventory > min)) {
                int machineID = partIDNum;
                Product newProduct = new Product(partIDNum,name,price,inventory,min,max);
                for (Part part : assocParts) {
                    newProduct.addAssociatedPart(part);
                }
                Inventory.addProduct(newProduct);
                nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
            } else {
                alert.showAlertMsg();
            }
        } catch(Exception e) {
            alert.showAlertInputValid();
        }

    }

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        if (alert.showconfirmationCancelAlert()) {
            nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
        }
    }


    /**
     * FUTURE ENHANCEMENT - All methods below this line should probably be in their own appropriate class
     */
    private void populateTables() {
        PartTableView.setItems(Inventory.getAllParts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        AssociatedProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    protected void getSearchResultsForParts(ActionEvent event) throws IOException {
        String q = queryParts.getText();
        ObservableList<Part> parts = searchbyPartName(q);
        PartTableView.setItems(parts);
        if (parts.isEmpty()) {
            alert.showAlertForSearch();
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
}
