package com.example.softwareic482.model;

/**
 * Subclass or Part. Is able to be instantiated and extends part functionality
 * by adding an additional param.
 */

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, Double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public void setCompanyName(String companyName) {
            this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

}