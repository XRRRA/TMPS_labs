package domain;

public abstract class Product {
    public String brand;
    public String model;

    public Product(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public abstract void displayProductInfo();

    @Override
    public String toString() {
        return brand + " " + model;
    }
}
