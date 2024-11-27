package adapters;

import domain.AdvancedProduct;
import domain.Product;

public class AdvancedProductAdapter implements AdvancedProduct {
    private final Product product;

    public AdvancedProductAdapter(Product product) {
        this.product = product;
    }

    @Override
    public void showDetailedProductInfo() {
        product.displayProductInfo();
        System.out.println("The product: " + product + " is available in VERY LIMITED stock.");
    }
}