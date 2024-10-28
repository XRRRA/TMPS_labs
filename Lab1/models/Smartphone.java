package models;

import domain.Product;

public class Smartphone extends Product {
    private int ram;
    private int storage;

    public Smartphone(String brand, String model, int ram, int storage) {
        super(brand, model);
        this.ram = ram;
        this.storage = storage;
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Smartphone [Brand=" + brand + ", Model=" + model + ", RAM=" + ram + "GB, Storage=" + storage + "GB]");
    }
}
