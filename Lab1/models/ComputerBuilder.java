package models;

public class ComputerBuilder {
    private String brand;
    private String model;
    private String cpu;
    private int ram;
    private int storage;

    public ComputerBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public ComputerBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public ComputerBuilder setCpu(String cpu) {
        this.cpu = cpu;
        return this;
    }

    public ComputerBuilder setRam(int ram) {
        this.ram = ram;
        return this;
    }

    public ComputerBuilder setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    public Computer build() {
        return new Computer(brand, model, cpu, ram, storage);
    }
}
