package domain;

import domain.observer.Subject;

import java.util.ArrayList;
import java.util.List;

public class Store extends Subject {
    private static Store instance;
    private final List<Product> inventory = new ArrayList<>();

    private Store() { }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public void displayInventory() {
        for (Product product : inventory) {
            product.displayProductInfo();
        }
    }

    public void updateInventory(String product, int quantity) {
        System.out.println("Inventory updated: " + product + " - Quantity: " + quantity);
        notifyObservers("Product: " + product + " has new inventory: " + quantity);
    }
}
