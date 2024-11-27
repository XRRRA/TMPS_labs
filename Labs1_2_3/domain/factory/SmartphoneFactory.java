package domain.factory;

import domain.models.Smartphone;

public class SmartphoneFactory extends domain.factory.ProductFactory{
    @Override
    public Smartphone createProduct(String brand, String model) {
        return new Smartphone(brand, model, "Titanium gray", 8, 128);
    }
}
