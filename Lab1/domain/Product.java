package domain;

public abstract class Product {
    protected String brand;
    protected String model;

    public Product(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public abstract void displayProductInfo();
}
