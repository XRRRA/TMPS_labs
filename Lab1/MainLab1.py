from abc import ABC, abstractmethod


# SRP: Product class has a single responsibility to hold product data
class Product:
    def __init__(self, name, price):
        self.name = name
        self.price = price

    def get_info(self):
        return f"{self.name}: ${self.price:.2f}"


# SRP: Inventory class manages products
class Inventory:
    def __init__(self):
        self.products = []

    def add_product(self, product):
        self.products.append(product)

    def list_products(self):
        for product in self.products:
            print(product.get_info())


# Usage Example
# Step 3: Basic usage example
if __name__ == "__main__":
    # Create inventory and add products
    inventory = Inventory()
    inventory.add_product(Product("Laptop", 1200))
    inventory.add_product(Product("Smartphone", 800))

    # List products
    print("Products in inventory:")
    inventory.list_products()
