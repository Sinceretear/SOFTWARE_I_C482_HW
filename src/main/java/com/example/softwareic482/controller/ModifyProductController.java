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

public class ModifyProductController implements Initializable {

    private Product selectedProduct;
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();
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

    @FXML
    private TextField queryProducts;

    @FXML
    private TableView<Product> ProductTableView;
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


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTables();
    }

    public ModifyProductController() {
    }


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

    @FXML
    protected void addPart(ActionEvent event) throws IOException {
        if(ProductTableView.getSelectionModel().getSelectedItem() != null) {
            Product selectedPart = ProductTableView.getSelectionModel().getSelectedItem();
            Part newPart = createPart(selectedPart);
            assocParts.add(newPart);
            populateTables();
        } else {
            alert.showAlertMsg("part");
        }
    }

    @FXML
    protected void removeAssociatedPart(ActionEvent event) throws IOException {
        if (AssociatedPartTableView.getSelectionModel().getSelectedItem() == null) {
            alert.showAlertMsg("part");
        } else {
            if (alert.showconfirmationAlert()) {
                AssociatedPartTableView.getItems().removeAll(AssociatedPartTableView.getSelectionModel().getSelectedItem());
            }
        }
    }

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

    @FXML
    protected void getSearchResultsForProducts(ActionEvent event) throws IOException {
        String q = queryProducts.getText();
        ObservableList<Product> product = searchbyProductName(q);
        ProductTableView.setItems(product);
        if (product.isEmpty()) {
            alert.showAlertForSearch();
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

    @FXML
    protected void cancel(ActionEvent event) throws IOException {
        nav.sceneToGoTo("src/main/java/com/example/softwareic482/views/mainForm.fxml", event);
    }

    private void populateTables() {


        ProductTableView.setItems(Inventory.getAllProducts());
        productID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));


        AssociatedProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
        AssociatedProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        AssociatedProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        AssociatedProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
        AssociatedPartTableView.setItems(assocParts);

//        if (selectedProduct.getAllAssociatedParts().isEmpty() == false) {
//            AssociatedPartTableView.setItems(selectedProduct.getAllAssociatedParts());
//            AssociatedProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
//            AssociatedProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            AssociatedProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
//            AssociatedProductPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
//        }
    }

}
