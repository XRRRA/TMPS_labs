package factory;

import domain.Product;


public abstract class ProductFactory {
    public abstract Product createProduct(String brand, String model);
}
