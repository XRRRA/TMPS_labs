package client;

import domain.Store;
import domain.Product;
import factory.ComputerFactory;
import factory.ProductFactory;
import models.ComputerBuilder;

public class Main {
    public static void main(String[] args) {
        // Singleton - Accessing the store instance
        Store store = Store.getInstance();

        // Factory Method - Creating products through specific factories
        ProductFactory computerFactory = new ComputerFactory();
        Product computer = computerFactory.createProduct("Lenovo", "Legion 5i pro");
        store.addProduct(computer);

        // Builder Pattern - Building a customized computer
        ComputerBuilder builder = new ComputerBuilder();
        Product customComputer = builder.setBrand("Asus")
                .setModel("ROG")
                .setCpu("AMD Ryzen 7")
                .setRam(16)
                .setStorage(512)
                .build();
        store.addProduct(customComputer);

        // Display the store's inventory
        store.displayInventory();
    }
}
