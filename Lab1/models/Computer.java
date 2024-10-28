package models;

import domain.Product;

public class Computer extends Product {
    private String cpu;
    private int ram;
    private int storage;

    public Computer(String brand, String model, String cpu, int ram, int storage) {
        super(brand, model);
        this.cpu = cpu;
        this.ram = ram;
        this.storage = storage;
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Computer [Brand=" + brand + ", Model=" + model + ", CPU=" + cpu + ", RAM=" + ram + "GB, Storage=" + storage + "GB]");
    }
}
