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
        if (!allParts.isEmpty()) {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getId() == partID) {
                    return allParts.get(i);
                }
            }

        }
        return null;
    }

    public Product lookupProduct(int productId) {
        if (!allProducts.isEmpty()) {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getId() == productId) {
                    return allProducts.get(i);
                }
            }
        }
        return null;
    }

    public void updatePart(int index, Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getId() == selectedPart.getId()) {
                allParts.set(i, selectedPart);
                break;
            }
        }
        return;
    }

    public void updateProduct(int index, Product selectedProduct) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).getId() == selectedProduct.getId()) {
                allProducts.set(i, selectedProduct);
                break;
            }
        }
        return;
    }

    public ObservableList<Part> lookupPart(String partName) {
        return allParts;
    }

    public ObservableList<Product> lookupProduct(String productName) {
        return allProducts;
    }
}