/**
 *
 * @author Hunter Walker
 */

package com.example.softwareic482.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Holds all inventory (Parts and Products) for the main tableviews
 */

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts =  FXCollections.observableArrayList();

    /**
     * Adds new parts to parts array
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    /**
     * Adds new products to products array
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * returns parts & products arrays
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * returns deletes parts and products from respective arrays
     */
    public static boolean deletePart(Part selectedPart) {
            if (allParts.contains(selectedPart)) {
                allParts.remove(selectedPart);
                return true;
            }
            else {
                return false;
            }
    }

    public static boolean deleteProduct(Product selectedPart) {
        if (allProducts.contains(selectedPart)) {
            allProducts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Unused methods?
     * Probably named something else elsewhere.
     */
    public Part lookupPart(int partID) {
        InHouse tmp = new InHouse(1,"blah", 14.4, 2, 4,5, 81);
        return tmp;
    }

    public Product lookupProduct(int productId) {
        Product tmp = new Product(1,"blah", 14.4, 2, 4,5);
        return tmp;
    }

    public void updatePart(int index, Part selectedPart) {

    }

    public void updateProduct(int index, Product selectedProduct) {

    }

    public ObservableList<Part> lookupPart(String partName) {
        return allParts;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return allProducts;
    }
}