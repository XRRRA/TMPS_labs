package domain.builder;

import domain.models.Smartphone;

public class SmartphoneBuilder {
    private String brand;
    private String model;
    private String color;
    private int ram;
    private int storage;

    public SmartphoneBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public SmartphoneBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public SmartphoneBuilder setColor(String color){
        this.color = color;
        return this;
    }

    public SmartphoneBuilder setRam(int ram) {
        this.ram = ram;
        return this;
    }

    public SmartphoneBuilder setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    public Smartphone build() {
        return new Smartphone(brand, model, color, ram, storage);
    }
}
