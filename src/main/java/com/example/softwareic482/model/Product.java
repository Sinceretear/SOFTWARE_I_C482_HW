package com.example.softwareic482.model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Initializes products and creates array for associated parts with getters and setters
 * for product data.
 */

public class Product {
    /**
     * Holds associated parts
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Methods to return product data and set new values
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds associated parts
     */
    public void addAssociatedPart(Part newPart) {
        associatedParts.add(newPart);
    }

    /**
     * Deletes associated parts
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else {
            return false;
        }
    }

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public void setAllAssociatedParts(ObservableList<Part> listOfParts) {
        this.associatedParts = listOfParts;
    }
}