package factory;

import models.Computer;

public class ComputerFactory extends factory.ProductFactory {
    @Override
    public Computer createProduct(String brand, String model) {
        return new Computer(brand, model, "Intel i5", 8, 2048);
    }
}
