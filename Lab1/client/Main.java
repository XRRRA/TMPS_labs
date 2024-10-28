package client;

import domain.Store;
import domain.Product;
import factory.ComputerFactory;
import factory.SmartphoneFactory;
import factory.ProductFactory;
import models.ComputerBuilder;
import models.SmartphoneBuilder;

public class Main {
    public static void main(String[] args) {
        // Singleton - Accessing the store instance
        Store store = Store.getInstance();

        // Factory Method - Creating products through specific factories
        ProductFactory computerFactory = new ComputerFactory();
        Product computer = computerFactory.createProduct("Lenovo", "Legion 5i pro");
        store.addProduct(computer);

        SmartphoneFactory smartphoneFactory = new SmartphoneFactory();
        Product smartphone = smartphoneFactory.createProduct("Samsung", "Galaxy S23");
        store.addProduct(smartphone);

        // Builder Pattern - Building a customized computer
        Product customComputer = new ComputerBuilder()
                .setBrand("Asus")
                .setModel("ROG")
                .setCpu("AMD Ryzen 7")
                .setRam(16)
                .setStorage(512)
                .build();
        store.addProduct(customComputer);


        Product customSmartphone = new SmartphoneBuilder()
                .setBrand("Apple")
                .setModel("16 pro Max")
                .setColor("Space gray")
                .setRam(8)
                .setStorage(512)
                .build();
        store.addProduct(customSmartphone);

        // Display the store's inventory
        store.displayInventory();
    }
}
