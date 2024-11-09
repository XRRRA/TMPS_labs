package client;

import domain.*;
import domain.factory.ProductFactory;
import domain.models.*;

public class Main {
    public static void main(String[] args) {
        // Singleton: Only one inventory instance
        Inventory inventory = Inventory.getInstance();

        // Factory Method: Creating products via ProductFactory
        Product laptop = ProductFactory.createProduct("Laptop", "High-end Laptop", 1500.0);
        Product smartphone = ProductFactory.createProduct("Smartphone", "Latest Smartphone", 800.0);

        inventory.addProduct(laptop);
        inventory.addProduct(smartphone);

        System.out.println("Products before discounts:");
        inventory.listProducts();

        // Applying Percentage Discount Strategy
        DiscountStrategy percentageDiscount = new PercentageDiscount(20);
        for (Product product : inventory.getProducts()) {
            double discountedPrice = percentageDiscount.applyDiscount(product);
            System.out.println("Discounted price for " + product.getName() + ": $" + discountedPrice);
        }

        // Applying Fixed Amount Discount Strategy
        DiscountStrategy fixedDiscount = new FixedAmountDiscount(100);
        for (Product product : inventory.getProducts()) {
            double discountedPrice = fixedDiscount.applyDiscount(product);
            System.out.println("Discounted price for " + product.getName() + " with fixed discount: $" + discountedPrice);
        }
    }
}
