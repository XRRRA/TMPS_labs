package domain;

import java.util.ArrayList;
import java.util.List;

public class Store {
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
}
