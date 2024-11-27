package decorators;

import domain.Product;

public abstract class ProductDecorator extends Product {
    protected Product decoratedProduct;

    public ProductDecorator(Product decoratedProduct) {
        super(decoratedProduct.brand, decoratedProduct.model);
        this.decoratedProduct = decoratedProduct;
    }

    public abstract void displayProductInfo();
}