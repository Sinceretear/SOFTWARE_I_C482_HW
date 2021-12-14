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
 * Business logic for modifying a product
 */

public class ModifyProductController implements Initializable {

    /**
     * The selected product to modify needs to be set before showing the modify parts screen
     */
    private Product selectedProduct;

    /**
     * Holds associated parts for this product
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();
    private int newParts = 0;

    private Alerts alert = new Alerts();

    @FXML
    private Button cancel;
    @FXML
    private Button saveProduct;
    @FXML
    private Button addPartButton;
    @FXML
    private Button removeAssociatedPartButton;

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

    /**
     * Connects textfield to search functionality
     */
    @FXML
    private TextField queryParts;

    @FXML
    private TableView<Part> PartsTableView;
    @FXML
    private TableColumn<Product,Integer> productID;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productInventoryLevel;
    @FXML
    private TableColumn<Product, Double> productPricePerUnit;

    @FXML
    private TableView<Part> AssociatedPartTableView;
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

    /**
     * Populates tables with appropriate data from selectedProduct
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTables();
    }

    public ModifyProductController() {
    }

    /**
     * Sets up text fields and tables with preloaded data is called before pushing screen onto view
     */
    @FXML
    public void setItems(Product product) {
        String toPartName = product.getName();
        int toPartID = product.getId();
        double toPriceName = product.getPrice();
        int toStock = product.getStock();
        int toMin = product.getMin();
        int toMax = product.getMax();

        productNameField.setText(toPartName);
        productIDField.setText(String.valueOf(toPartID));
        productInv.setText(String.valueOf(toStock));
        productPrice.setText(String.valueOf(toPriceName));
        productMin.setText(String.valueOf(toMin));
        productMax.setText(String.valueOf(toMax));

        selectedProduct = product;
        assocParts = selectedProduct.getAllAssociatedParts();
        populateTables();
    }

    /**
     * Adds a new art to the associated parts list for this product
     */
    @FXML
    protected void addPart(ActionEvent event) throws IOException {
        if(PartsTableView.getSelectionModel().getSelectedItem() != null) {
            Part selectedPart = PartsTableView.getSelectionModel().getSelectedItem();
            assocParts.add(selectedPart);
            newParts += 1;
            populateTables();
        } else {
            alert.showAlertMsg("part");
        }
    }

    /**
     * deletes an associaated part
     */
    @FXML
    protected void removeAssociatedPart(ActionEvent event) throws IOException {
        if (AssociatedPartTableView.getSelectionModel().getSelectedItem() == null) {
            alert.showAlertDeleteMsg("part");
        } else {
            if (alert.showconfirmationAlert("part")) {
                AssociatedPartTableView.getItems().remove(AssociatedPartTableView.getSelectionModel().getSelectedItem());
            }
        }
    }

    /**
     * Saves the Product by essentially deleting the old one and creating the new one to give the appearance of
     * an update
     */
    @FXML
    protected void save(ActionEvent event) throws IOException {
        try {
            String name = productNameField.getText();
            int toPartID = Integer.parseInt(productIDField.getText());
            int inventory = Integer.parseInt(productInv.getText());
            double price = Double.parseDouble(productPrice.getText());
            int max = Integer.parseInt(productMax.getText());
            int min = Integer.parseInt(productMin.getText());

            if ((inventory < max && min < max && inventory > min)) {
                Product newProduct = new Product(toPartID,name,price,inventory,min,max);
                Inventory.addProduct(newProduct);
                for (Part part : assocParts) {
                    newProduct.addAssociatedPart(part);
                }
                Inventory.deleteProduct(selectedProduct);
                nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
            } else {
                alert.showAlertMsg();
            }
        } catch(Exception e) {
            alert.showAlertInputValid();
        }

    }

    /**
     * FUTURE ENHANCEMENT - should probably be in its own class
     */
    @FXML
    protected void getSearchResultsForParts(ActionEvent event) throws IOException {
        String q = queryParts.getText();
        ObservableList<Part> parts = searchbyPartName(q);
        PartsTableView.setItems(parts);
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

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        if (alert.showconfirmationCancelAlert()) {
            for (int i = 0; i < newParts; i++) {
                assocParts.remove(assocParts.size() - 1);
            }
            nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
        }
    }

    /**
     * FUTURE ENHANCEMENT - Could also probably be in its own class and
     * could have params to pass in the table and list of components to setValues for.
     */
    private void populateTables() {
        PartsTableView.setItems(Inventory.getAllParts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        AssociatedProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        AssociatedPartTableView.setItems(assocParts);
    }

}
