# Creational Design Patterns


## Author: Iordan Liviu, FAF-223

----

## Objectives:

* Implement 2 SOLID letters in a simple project.




## Used SOLID letters:
* S: Single-Responsibility Principle (SRP)
* O: Open-Closed Principle (OCP)

## Implementation

### 1. For the Single-Responsibility Principle (SRP):
* Explanation of SRP
The Single Responsibility Principle: a class should have only one reason to change, meaning it should only have one job or responsibility. This principle enhances maintainability and readability, because each class can be modified independently without affecting others.

1. I have implemented a "Product" class.
* The Product class is dedicated solely to holding product data, such as the name and price.
* It has a method get_info() that formats this data for display. If changes was needed—such as adding new attributes or methods related to product data—only this class needs to be modified.
```python
class Product:
    def __init__(self, name, price):
        self.name = name
        self.price = price

    def get_info(self):
        return f"{self.name}: ${self.price:.2f}"
```

2. I have implemented a "Inventory" class which manages a collection of products
* The Inventory class is responsible for managing a collection of products. It handles adding products and listing them.
* By separating this functionality from the Product class, we ensure that changes related to inventory management do not impact how products are represented or stored.
```python
class Inventory:
    def __init__(self):
        self.products = []

    def add_product(self, product):
        self.products.append(product)

    def list_products(self):
        for product in self.products:
            print(product.get_info())
```

> As an example, create an instance of Inventory and add some products to it.

```python
# Step 3: Basic usage example
if __name__ == "__main__":
    # Create inventory and add products
    inventory = Inventory()
    inventory.add_product(Product("Laptop", 1200))
    inventory.add_product(Product("Smartphone", 800))

    # List products
    print("Products in inventory:")
    inventory.list_products()
```

### 2. For the Open-Closed Principle (OCP)
* Explanation of OCP: software entities should be open for extension but closed for modification. This principle allows developers to add new functionality without changing existing code, which reduces the risk of introducing bugs and makes the code more stable.

1. DiscountStrategy interface:
* The DiscountStrategy interface defines a contract for discount implementations. By using an abstract base class, we can introduce new discount strategies without modifying existing classes.

```python
class DiscountStrategy(ABC):
    @abstractmethod
    def apply_discount(self, product):
        pass
```

2. Percentage Discount Strategy:
* The PercentageDiscount class implements the DiscountStrategy interface and provides a specific way to calculate discounts based on a percentage.
* This design allows us to add more discount types in the future without changing the existing code structure.

```python
class PercentageDiscount(DiscountStrategy):
    def __init__(self, percentage):
        self.percentage = percentage

    def apply_discount(self, product):
        discount_amount = product.price * (self.percentage / 100)
        discounted_price_percentage = product.price - discount_amount
        return discounted_price_percentage
```
3. Fixed Amount Discount Strategy:
* The FixedAmountDiscount class also implements the DiscountStrategy interface but applies a fixed amount discount.
* This approach ensures that if we want to introduce new discount strategies in the future—like a seasonal discount or a loyalty discount—we can do so by creating new classes without modifying existing ones.

```python
class FixedAmountDiscount(DiscountStrategy):
    def __init__(self, amount):
        self.amount = amount

    def apply_discount(self, product):
        discounted_price_fixed = product.price - self.amount
        return max(discounted_price_fixed, 0)
```
> As an example, apply the discounts using both strategies and display the results.

```python
# Step 3: Basic usage example
if __name__ == "__main__":
    inventory = Inventory()
    inventory.add_product(Product("Laptop", 1200.98))
    inventory.add_product(Product("Smartphone", 800))

    print("Products before discounts:")
    inventory.list_products()

    percentage_discount_strategy = PercentageDiscount(25)
    for product in inventory.products:
        discounted_price = percentage_discount_strategy.apply_discount(product)
        print(f"Discounted price for {product.name}: ${discounted_price:.2f}")

    fixed_discount_strategy = FixedAmountDiscount(100)
    for product in inventory.products:
        discounted_price = fixed_discount_strategy.apply_discount(product)
        print(f"Discounted price for {product.name} with fixed discount: ${discounted_price:.2f}")
    inventory.list_products()

```


## Conclusions / Screenshots / Results

In summary, by applying the Single Responsibility Principle and the Open/Closed Principle in this project:
* SRP keeps each class focused on one task, making it easier to manage.
* OCP allows extending the functionality without modifying existing code, which helps prevent bugs.
<p>This approach not only follows best practices but also makes the code more flexible and easier to work with in the long run.</p>