package domain.models;

import domain.Product;

public class Smartphone extends Product {

    private final String color;
    private final int ram;
    private final int storage;

    public Smartphone(String brand, String model, String color, int ram, int storage) {
        super(brand, model);
        this.color = color;
        this.ram = ram;
        this.storage = storage;
    }

    @Override
    public void displayProductInfo() {
        System.out.println("Smartphone [Brand=" + brand + ", Model=" + model + ", Color=" + color + ", RAM=" + ram + "GB, Storage=" + storage + "GB]");
    }
}
