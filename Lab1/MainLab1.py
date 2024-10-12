from abc import ABC, abstractmethod


# SRP: Product class has a single responsibility to hold product data
class Product:
    def __init__(self, name, price):
        self.name = name
        self.price = price

    def get_info(self):
        return f"{self.name}: ${self.price:.2f}"


# SRP: Inventory class manages products
# noinspection PyShadowingNames
class Inventory:
    def __init__(self):
        self.products = []

    def add_product(self, product):
        self.products.append(product)

    def list_products(self):
        for product in self.products:
            print(product.get_info())


# OCP: DiscountStrategy interface allows for extending discount types
# noinspection PyShadowingNames
class DiscountStrategy(ABC):
    @abstractmethod
    def apply_discount(self, product):
        pass


# OCP: Implementation of a discount strategy using percentages
# noinspection PyShadowingNames
class PercentageDiscount(DiscountStrategy):
    def __init__(self, percentage):
        self.percentage = percentage

    def apply_discount(self, product):
        discount_amount = product.price * (self.percentage / 100)
        discounted_price_percentage = product.price - discount_amount
        return discounted_price_percentage


# OCP: Implementation of a discount strategy using a fixed price
class FixedAmountDiscount(DiscountStrategy):
    def __init__(self, amount):
        self.amount = amount

    def apply_discount(self, product):
        discounted_price_fixed = product.price - self.amount
        return max(discounted_price_fixed, 0)


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
