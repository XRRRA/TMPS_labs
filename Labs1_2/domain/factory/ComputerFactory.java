package domain.factory;

import domain.models.Computer;

public class ComputerFactory extends domain.factory.ProductFactory {
    @Override
    public Computer createProduct(String brand, String model) {
        return new Computer(brand, model, "Intel i5", 8, 2048);
    }
}
