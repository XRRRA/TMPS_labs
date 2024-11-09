package facade;

import domain.Product;
import domain.Store;
import domain.factory.ProductFactory;

public class StoreFacade {
    private final Store store;

    public StoreFacade() {
        this.store = Store.getInstance();
    }

    public void addProduct(ProductFactory factory, String brand, String model) {
        Product product = factory.createProduct(brand, model);
        store.addProduct(product);
    }

    public void showInventory() {
        store.displayInventory();
    }
}
