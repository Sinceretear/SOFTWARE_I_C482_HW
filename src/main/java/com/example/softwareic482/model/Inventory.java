/**
 *
 * @author Hunter Walker
 */

package com.example.softwareic482.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts =  FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partID) {
            InHouse tmp = new InHouse(1,"blah", 14.4, 2, 4,5, 81);
            return tmp;
    }

    public Product lookupProduct(int productId) {
        Product tmp = new Product(1,"blah", 14.4, 2, 4,5);
        return tmp;
    }

    public ObservableList<Part> lookupPart(String partName) {
            return allParts;
    }

    public ObservableList<Product> lookupProduct(String productName) {
            return allProducts;
    }

    public void updatePart(int index, Part selectedPart) {

    }

    public void updateProduct(int index, Product selectedProduct) {

    }

    public boolean deletePart(Part selectedPart) {
            return true;
    }

    public boolean deleteProduct(Product selectedPart) {
            return true;
    }

    public ObservableList<Part> getAllParts() {
            return allParts;
    }
    public ObservableList<Product> getAllProducts() {
            return allProducts;
    }


}