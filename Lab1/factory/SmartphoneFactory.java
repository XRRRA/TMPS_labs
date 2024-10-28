package factory;

import models.Smartphone;

public class SmartphoneFactory extends factory.ProductFactory{
    @Override
    public Smartphone createProduct(String brand, String model) {
        return new Smartphone(brand, model, "Titanium gray", 8, 128);
    }
}
