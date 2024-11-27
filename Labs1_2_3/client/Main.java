package client;

import adapters.AdvancedProductAdapter;
import decorators.DiscountDecorator;
import domain.AdvancedProduct;
import domain.Product;
import domain.Store;
import domain.factory.ComputerFactory;
import domain.factory.SmartphoneFactory;
import domain.factory.ProductFactory;
import domain.builder.ComputerBuilder;
import domain.builder.SmartphoneBuilder;
import domain.observer.StoreObserver;
import facade.StoreFacade;

public class Main {
    public static void main(String[] args) {
        // Singleton - Accessing the store instance
        Store store = Store.getInstance();

        // Create and attach observers
        StoreObserver observer1 = new StoreObserver("Observer1");
        StoreObserver observer2 = new StoreObserver("Observer2");
        store.attach(observer1);
        store.attach(observer2);

        // Update inventory and notify observers
        store.updateInventory("Laptop", 10);
        store.updateInventory("Smartphone", 20);

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

        // Adapter Pattern -  allows two incompatible interfaces to work together
        AdvancedProduct advancedProduct = new AdvancedProductAdapter(computer);
        advancedProduct.showDetailedProductInfo();

        // Decorator Pattern - add new functionality to objects dynamically
        Product discountedSmartphone = new DiscountDecorator(smartphone, 10.0);
        discountedSmartphone.displayProductInfo();

        // Facade Pattern - simplify complex operations by implementing one unified interface
        StoreFacade storeFacade = new StoreFacade();

        storeFacade.addProduct(new ComputerFactory(), "Apple", "MacBook Air");
        storeFacade.addProduct(new SmartphoneFactory(), "Sonny", "Xperia 9");

        storeFacade.showInventory();

        // Display the store's inventory
//        store.displayInventory();
    }
}
