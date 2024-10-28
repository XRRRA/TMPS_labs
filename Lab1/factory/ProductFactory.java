package factory;

import models.Computer;

public abstract class ProductFactory {
    public abstract Computer createProduct(String brand, String model);
}
