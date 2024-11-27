package decorators;

import domain.Product;

public class DiscountDecorator extends ProductDecorator {
    private final double discountPercentage;

    public DiscountDecorator(Product decoratedProduct, double discountPercentage) {
        super(decoratedProduct);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void displayProductInfo() {
        decoratedProduct.displayProductInfo();
        System.out.println("The product: "+ decoratedProduct + " is now " + discountPercentage + "% off");
    }
}