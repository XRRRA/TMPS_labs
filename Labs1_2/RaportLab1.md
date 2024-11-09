### Design Patterns Used

1. **Singleton Pattern:**
   The Singleton pattern is used to ensure that a class has only one instance while providing a global access point to this instance. In this project, the Singleton pattern is applied to the `Store` class. By making the constructor private and providing a static `getInstance` method, only one instance of `Store` can be created and accessed throughout the application, allowing all products to be stored in a single inventory.

2. **Factory Method Pattern:**
   The Factory Method pattern provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created. Here, `ProductFactory` serves as the abstract domain.factory, with `ComputerFactory` and `SmartphoneFactory` as concrete implementations. Each domain.factory creates a specific product (`Computer` or `Smartphone`), following a consistent interface that allows the client code to request a product without needing to know the specific instantiation details.

3. **Builder Pattern:**
   The Builder pattern is used to construct complex objects step-by-step. This pattern is useful when the object requires numerous attributes, some of which may be optional. In this project, builders (`ComputerBuilder` and `SmartphoneBuilder`) are used to create customized `Computer` and `Smartphone` instances. Each domain.builder allows setting various attributes individually, and then calling the `build` method to create the final object.

---

### Code Explanations

#### `Main.java`

```java
package client;

import domain.Store;
import domain.Product;
import domain.factory.ComputerFactory;
import domain.factory.SmartphoneFactory;
import domain.factory.ProductFactory;
import domain.builder.ComputerBuilder;
import domain.builder.SmartphoneBuilder;

public class Main {
   public static void main(String[] args) {
      // Singleton - Accessing the store instance  
      Store store = Store.getInstance();

      // Factory Method - Creating products through specific factories  
      ProductFactory computerFactory = new ComputerFactory();
      Product computer = computerFactory.createProduct("Lenovo", "Legion 5i pro");
      store.addProduct(computer);

      SmartphoneFactory smartphoneFactory = new SmartphoneFactory();
      Product smartphone = smartphoneFactory.createProduct("Samsung", "Galaxy S23");
      store.addProduct(smartphone);

      // Builder Pattern - Building a customized computer  
      Product customComputer = new ComputerBuilder()
              .setBrand("Asus")
              .setModel("ROG")
              .setCpu("AMD Ryzen 7")
              .setRam(16)
              .setStorage(512)
              .build();
      store.addProduct(customComputer);


      Product customSmartphone = new SmartphoneBuilder()
              .setBrand("Apple")
              .setModel("16 pro Max")
              .setColor("Space gray")
              .setRam(8)
              .setStorage(512)
              .build();
      store.addProduct(customSmartphone);

      // Display the store's inventory  
      store.displayInventory();
   }
}
```

The `Main` class in this Java application is the entry point for executing the code, where it demonstrates the use of several design patterns to manage products in a tech store. Let’s go through it step-by-step to understand how it works:

1. **Singleton Pattern with Store Instance:**
   ```java
   Store store = Store.getInstance();
   ```
    - The `Store` class follows the Singleton pattern, meaning only one instance of `Store` is created and shared throughout the application.
    - By calling `Store.getInstance()`, we obtain this single instance. This ensures that there’s only one centralized `store` object managing all products in the inventory.

2. **Factory Pattern with Product Creation:**
    - This part of the code demonstrates the **Factory Method Pattern** by using `ProductFactory` to create `Computer` and `Smartphone` objects.

   ```java
   ProductFactory computerFactory = new ComputerFactory();
   Product computer = computerFactory.createProduct("Lenovo", "Legion 5i pro");
   store.addProduct(computer);
   ```
    - Here, a `ComputerFactory` object is created, which extends the `ProductFactory` abstract class. Using this domain.factory object, we call `createProduct` to create a `Computer` with specified attributes (e.g., "Lenovo" as the brand and "Legion 5i pro" as the model).
    - The newly created `computer` object is then added to the `store` inventory by calling `store.addProduct(computer)`.

   ```java
   SmartphoneFactory smartphoneFactory = new SmartphoneFactory();
   Product smartphone = smartphoneFactory.createProduct("Samsung", "Galaxy S23");
   store.addProduct(smartphone);
   ```
    - Similarly, a `SmartphoneFactory` object is created to produce a `Smartphone` with attributes "Samsung" and "Galaxy S23".
    - The resulting `smartphone` object is also added to the `store`.

   **Explanation:** This pattern allows creating specific products (like computers or smartphones) without specifying the exact class type in the `Main` class, making the code more modular and easier to extend.

3. **Builder Pattern for Custom Product Creation:**
    - The **Builder Pattern** is demonstrated when creating custom products by setting various attributes step-by-step.

   ```java
   Product customComputer = new ComputerBuilder()
           .setBrand("Asus")
           .setModel("ROG")
           .setCpu("AMD Ryzen 7")
           .setRam(16)
           .setStorage(512)
           .build();
   store.addProduct(customComputer);
   ```
    - Here, a `ComputerBuilder` object is used to create a custom computer by setting its brand, model, CPU, RAM, and storage one at a time.
    - Once all desired attributes are set, `build()` is called to finalize and return a `Computer` object, which is then added to the store’s inventory.

   ```java
   Product customSmartphone = new SmartphoneBuilder()
           .setBrand("Apple")
           .setModel("16 Pro Max")
           .setColor("Space gray")
           .setRam(8)
           .setStorage(512)
           .build();
   store.addProduct(customSmartphone);
   ```
    - Similarly, a `SmartphoneBuilder` is used to create a customized smartphone by setting the brand, model, color, RAM, and storage.
    - The `build()` method produces a `Smartphone` object that’s added to the inventory.

   **Explanation:** The Builder Pattern here allows for flexible and readable product creation, particularly for products with many attributes. Each domain.builder step method returns the domain.builder itself, allowing method chaining.

4. **Displaying the Store Inventory:**
   ```java
   store.displayInventory();
   ```
    - Finally, this line calls `displayInventory` on the `store` instance, which iterates over each product in the inventory and prints out its details.

   **Explanation:** By calling `displayInventory`, we see the results of our product creation. Each product uses its own `displayProductInfo` method to show its attributes in a human-readable format.

---

In summary, this `Main` class demonstrates how design patterns like Singleton, Factory, and Builder work together to manage product creation and storage in a structured and modular way. The Singleton pattern ensures only one `Store` instance manages inventory, the Factory pattern abstracts product creation, and the Builder pattern provides flexibility for creating customized products. This setup makes the code easy to extend, maintain, and scale.

---

### `Product.java`

```java
package domain;

public abstract class Product {
    protected String brand;
    protected String model;

    public Product(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public abstract void displayProductInfo();
}
```

- `Product` is an **abstract base class** for all products in the tech store. It contains attributes that are common to all products: `brand` and `model`.
- The constructor initializes `brand` and `model` for each product.
- The `displayProductInfo` method is abstract, meaning each subclass (e.g., `Computer`, `Smartphone`) must implement this method to define how their details will be displayed. This approach allows flexibility, as each product type can provide a customized output format.

---

### `Store.java`

```java
package domain;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private static Store instance;
    private final List<Product> inventory = new ArrayList<>();

    private Store() { }

    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public void displayInventory() {
        for (Product product : inventory) {
            product.displayProductInfo();
        }
    }
}
```

- `Store` uses the **Singleton Pattern**, meaning only one `Store` instance exists for managing products.
- `private static Store instance` is a static field holding the unique instance of the `Store`.
- `getInstance` checks if an instance exists; if not, it creates one, guaranteeing there is only one `Store` managing the inventory.
- `addProduct` adds a `Product` to the `inventory` list, which is a collection of all products.
- `displayInventory` iterates through the `inventory` list and calls `displayProductInfo` on each product to print out its details. This way, each product displays its information according to its class-specific implementation.

---

### `ProductFactory.java`

```java
package domain.factory;

import domain.Product;

public abstract class ProductFactory {
   public abstract Product createProduct(String brand, String model);
}
```

- `ProductFactory` is an **abstract domain.factory class** that defines the interface for creating products.
- The `createProduct` method is abstract, meaning any subclass must implement it to provide specific details for creating a product.
- This design allows `ProductFactory` subclasses (like `ComputerFactory` and `SmartphoneFactory`) to define how they create specific types of products. The client code can use these factories without knowing the exact class or creation details of the product.

---

### `ComputerFactory.java`

```java
package domain.factory;

import domain.models.Computer;

public class ComputerFactory extends domain.factory.ProductFactory {
   @Override
   public Computer createProduct(String brand, String model) {
      return new Computer(brand, model, "Intel i5", 8, 2048);
   }
}
```

- `ComputerFactory` is a subclass of `ProductFactory` and follows the **Factory Method Pattern** to create `Computer` instances.
- `createProduct` overrides the method from `ProductFactory`, providing specific details for creating a `Computer` (e.g., a default CPU type, RAM, and storage).
- This encapsulation makes it easy to create `Computer` objects without exposing their creation details directly in the client code.

---

### `SmartphoneFactory.java`

```java
package domain.factory;

import domain.models.Smartphone;

public class SmartphoneFactory extends domain.factory.ProductFactory {
   @Override
   public Smartphone createProduct(String brand, String model) {
      return new Smartphone(brand, model, "Titanium gray", 8, 128);
   }
}
```

- `SmartphoneFactory` is another subclass of `ProductFactory`, designed to create `Smartphone` objects using the **Factory Method Pattern**.
- It overrides `createProduct` to provide default values for a `Smartphone`, such as color, RAM, and storage.
- This abstraction allows client code to request a `Smartphone` without needing to know or set the default properties manually.

---

### `Computer.java`

```java
package domain.models;

import domain.Product;

public class Computer extends Product {
   private String cpu;
   private int ram;
   private int storage;

   public Computer(String brand, String model, String cpu, int ram, int storage) {
      super(brand, model);
      this.cpu = cpu;
      this.ram = ram;
      this.storage = storage;
   }

   @Override
   public void displayProductInfo() {
      System.out.println("Computer [Brand=" + brand + ", Model=" + model + ", CPU=" + cpu + ", RAM=" + ram + "GB, Storage=" + storage + "GB]");
   }
}
```

- `Computer` is a subclass of `Product`, representing a specific type of product in the store with additional attributes: `cpu`, `ram`, and `storage`.
- The constructor initializes these attributes along with `brand` and `model`.
- `displayProductInfo` is implemented to print out all relevant information for a `Computer`, including CPU type, RAM, and storage. This allows each `Computer` instance to display its details in a specific format.

---

### `ComputerBuilder.java`

```java
package domain.models;

public class ComputerBuilder {
   private String brand;
   private String model;
   private String cpu;
   private int ram;
   private int storage;

   public domain.builder.ComputerBuilder setBrand(String brand) {
      this.brand = brand;
      return this;
   }

   public domain.builder.ComputerBuilder setModel(String model) {
      this.model = model;
      return this;
   }

   public domain.builder.ComputerBuilder setCpu(String cpu) {
      this.cpu = cpu;
      return this;
   }

   public domain.builder.ComputerBuilder setRam(int ram) {
      this.ram = ram;
      return this;
   }

   public domain.builder.ComputerBuilder setStorage(int storage) {
      this.storage = storage;
      return this;
   }

   public Computer build() {
      return new Computer(brand, model, cpu, ram, storage);
   }
}
```

- `ComputerBuilder` uses the **Builder Pattern** to create a `Computer` object with customizable attributes.
- Each setter method (`setBrand`, `setModel`, etc.) sets an attribute and returns the domain.builder itself, enabling **method chaining**.
- The `build` method creates and returns a `Computer` instance using the attributes set in the domain.builder. This approach allows client code to create complex objects step-by-step with readable syntax.

---

### `Smartphone.java`

```java
package domain.models;

import domain.Product;

public class Smartphone extends Product {
   private String color;
   private int ram;
   private int storage;

   public Smartphone(String brand, String model, String color, int ram, int storage) {
      super(brand, model);
      this.color = color;
      this.ram = ram;
      this.storage = storage;
   }

   @Override
   public void displayProductInfo() {
      System.out.println("Smartphone [Brand=" + brand + ", Model=" + model + ", Color=" + color + ", RAM=" + ram + "GB, Storage=" + storage + "GB]");
   }
}
```

- `Smartphone` extends `Product` to represent a specific product type with additional attributes: `color`, `ram`, and `storage`.
- The constructor initializes these attributes along with `brand` and `model`.
- `displayProductInfo` provides a formatted output showing all details of the `Smartphone`, including color, RAM, and storage capacity. This ensures each `Smartphone` displays its unique properties in a clear, structured way.

---

### `SmartphoneBuilder.java`

```java
package domain.models;

public class SmartphoneBuilder {
   private String brand;
   private String model;
   private String color;
   private int ram;
   private int storage;

   public domain.builder.SmartphoneBuilder setBrand(String brand) {
      this.brand = brand;
      return this;
   }

   public domain.builder.SmartphoneBuilder setModel(String model) {
      this.model = model;
      return this;
   }

   public domain.builder.SmartphoneBuilder setColor(String color) {
      this.color = color;
      return this;
   }

   public domain.builder.SmartphoneBuilder setRam(int ram) {
      this.ram = ram;
      return this;
   }

   public domain.builder.SmartphoneBuilder setStorage(int storage) {
      this.storage = storage;
      return this;
   }

   public Smartphone build() {
      return new Smartphone(brand, model, color, ram, storage);
   }
}
```

- `SmartphoneBuilder` is the domain.builder for `Smartphone` objects, using the **Builder Pattern** to create a `Smartphone` step-by-step.
- Each setter method sets an attribute, returning the domain.builder itself to allow chaining.
- `build` finalizes the object creation, returning a `Smartphone` instance with the specified attributes. This setup enables flexible and clear customization for each `Smartphone` instance.

---

### Conclusion

This laboratory project demonstrates the effective application of three core creational design patterns: Singleton, Factory Method, and Builder, within a simulated tech store application. By selecting and implementing these patterns, we achieve a modular, maintainable, and scalable system that meets real-world requirements for object creation and resource management.

- **Singleton Pattern** is utilized to ensure a single, globally accessible `Store` instance, which prevents resource duplication and maintains a centralized inventory.
- **Factory Method Pattern** streamlines object creation, allowing new products like computers and smartphones to be instantiated in a consistent, standardized way. This approach separates instantiation logic from the client, fostering extensibility as new product types can be added by simply extending the domain.factory.
- **Builder Pattern** enhances object customization for complex products, enabling developers to create variations of a product with differing specifications in an organized, readable, and manageable format.

Each design pattern addresses a specific aspect of object creation and contributes to reducing dependencies and increasing flexibility. Through this project, we gain hands-on experience in solving common software design challenges, showcasing how creational patterns can be practically implemented to create well-structured and maintainable systems.