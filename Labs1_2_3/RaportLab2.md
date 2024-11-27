## Structural Design Patterns in Java

**Author:** Iordan Liviu    
**Lab Topic:** Structural Design Patterns
  
---  

### 1. Introduction

Structural Design Patterns are design patterns that ease the design by identifying a simple way to realize relationships among entities. [1](https://en.wikipedia.org/wiki/Structural_pattern#:~:text=In%20software%20engineering%2C%20structural%20design,one%20that%20a%20client%20expects)

This group can be divided into two groups:
- Structural Class Patterns (using inheritance)
- Structural Object patterns (using composition) [2](https://else.fcim.utm.md/pluginfile.php/50523/mod_resource/content/2/StructuralDPs.pdf)

Examples of Structural patterns [1](https://en.wikipedia.org/wiki/Structural_pattern#:~:text=In%20software%20engineering%2C%20structural%20design,one%20that%20a%20client%20expects) are:
- Adapter pattern: 'adapts' one interface for a class into one that a client expects
- Aggregate pattern: a version of the Composite pattern with methods for aggregation of children
- Bridge pattern: decouple an abstraction from its implementation so that the two can vary independently
- Composite pattern: a tree structure of objects where every object has the same interface
- Decorator pattern: add additional functionality to an object at runtime where subclassing would result in an exponential rise of new classes
- Extensibility pattern: a.k.a. Framework - hide complex code behind a simple interface
- Facade pattern: create a simplified interface of an existing interface to ease usage for common tasks
- Flyweight pattern: a large quantity of objects share a common properties object to save space
- Marker pattern: an empty interface to associate metadata with a class.
- Pipes and filters: a chain of processes where the output of each process is the input of the next
- Opaque pointer: a pointer to an undeclared or private type, to hide implementation details
- Proxy pattern: a class functioning as an interface to another thing


In this project, I used three structural design patterns — **Adapter**, **Decorator**, and **Facade** — to enhance the functionality of a tech store system. These patterns improve how products are represented, customized, and managed.
  
---  

### 2. Patterns Used

- #### Adapter Pattern
  The **Adapter Pattern** allows objects with incompatible interfaces to work together. The `Adapter` thus acts as a bridge, allowing `Product` to be used as an `AdvancedProduct` without changing the original `Product` class. [3](https://refactoring.guru/design-patterns/adapter)

  **Implementation Snippet**:
  ```java  
  // domain/AdvancedProduct.java  
  package domain;  
  public interface AdvancedProduct { void showDetailedProductInfo(); }  
    
  // adapters/AdvancedProductAdapter.java  
  package adapters;  
  import domain.Product; import domain.AdvancedProduct;  
    
  public class AdvancedProductAdapter implements AdvancedProduct {  
      private final Product product;  
      public AdvancedProductAdapter(Product product) { this.product = product; }  
      @Override    public void showDetailedProductInfo() {        product.displayProductInfo();        System.out.println("Additional info: This product is available in limited stock.");    }}  
  ```  

    1. `AdvancedProduct` Interface: defines a method `showDetailedProductInfo`, for displaying product information in a specific way
    2. `AdvancedProduct Adapter` Class: Implements `AdvancedProduct` to adapt the existing `Product` class
        1. Constructor: Takes a `Product` and stores it in a private field
        2. `showDetailedProductInfo` Method: Calls the `displayProductInfo` method of the `Product` (incompatible interface), then adds an extra message to indicate that the product is available in limited stock.


- #### Decorator Pattern
  The **Decorator Pattern** allows you to add functionality to an object at runtime without altering its original structure. This pattern is useful for adding optional features dynamically and independently of other instances. By using this pattern, you can “decorate” individual `Product` instances with features like discounts without modifying the `Product` class itself. This aligns with the **open-closed principle** (open for extension but closed for modification) [4](https://refactoring.guru/design-patterns/decorator)
  **Implementation Snippet**:
  ```java  
  // decorators/DiscountDecorator.java  
  package decorators;  
  import domain.Product;  
    
  public class DiscountDecorator extends ProductDecorator {  
      private double discountPercentage;  
      public DiscountDecorator(Product decoratedProduct, double discountPercentage) {        super(decoratedProduct); this.discountPercentage = discountPercentage;    }  
      @Override    public void displayProductInfo() {        decoratedProduct.displayProductInfo();        System.out.println("Discount: " + discountPercentage + "% off");    }}  
  ```  

    1. **`ProductDecorator` (abstract)**: This is the base decorator that extends `Product`. It wraps a `Product` instance (`decoratedProduct`), enabling additional behavior on top of the wrapped instance.
    2. **`DiscountDecorator`**: Extends `ProductDecorator` to add a discount message.
        1. **Constructor**: Takes a `Product` object and a `discountPercentage`.
        2. **`displayProductInfo`**: Calls `displayProductIn`

- #### Facade Pattern
  The **Facade Pattern** provides a simple interface to complex subsystems, reducing the client’s dependency on multiple classes and streamlining interactions. The `Facade` pattern here simplifies how the main client interacts with the store by encapsulating multiple steps into a single, easy-to-use class. This reduces coupling between the client and the underlying system[5](https://refactoring.guru/design-patterns/facade).

  **Implementation Snippet**:
  ```java  
  // facade/StoreFacade.java  
  package facade;  
  import domain.Product;  import factory.ProductFactory;  
    
  public class StoreFacade {  
      private final Store store = Store.getInstance();  
      public void addProduct(ProductFactory factory, String brand, String model) {        Product product = factory.createProduct(brand, model);        store.addProduct(product);    }  
      public void showInventory() { store.displayInventory(); }}  
  ```  

    1. **`StoreFacade` Class**: Acts as a single access point for store operations, hiding the complexity of managing products and inventory.
        1. **`addProduct`**: Takes a `ProductFactory`, brand, and model, and adds the created product to the store.
        2. **`showInventory`**: Calls `displayInventory` on `Store`, showing all products.

---  

### 3. Conclusion

Structural design patterns are essential in software engineering for constructing flexible and scalable applications by focusing on how objects and classes are composed. In this project, I implemented three structural patterns — **Adapter**, **Decorator**, and **Facade** — which allowed me to enhance my tech store system’s flexibility, extendibility, and simplicity.

The **Adapter** pattern enabled operations between incompatible interfaces, allowing the `Product` class to meet the `AdvancedProduct` interface requirements. This flexibility is crucial for integrating new components without altering existing code, promoting software reusability and adherence to the **single responsibility principle** ([Refactoring Guru, Adapter pattern](https://refactoring.guru/design-patterns/adapter)).

The **Decorator** pattern facilitated dynamic feature extension by enabling `Product` objects to receive optional discounts. This pattern aligns with the **open-closed principle**, enabling extensions without modifying core classes. It highlights the power of composition over inheritance, as described in [Drumea Vasile’s Structural Design Patterns presentation](https://else.fcim.utm.md/pluginfile.php/50523/mod_resource/content/2/StructuralDPs.pdf) and [Refactoring Guru, Decorator pattern](https://refactoring.guru/design-patterns/decorator).

The **Facade** pattern provided a simplified interface for managing store operations, improving ease of use and reducing client dependency on complex subsystem components. This pattern supports a modular architecture, hiding intricate interactions within the system ([Wikipedia, Structural patterns](https://en.wikipedia.org/wiki/Structural_pattern#:~:text=In%20software%20engineering%2C%20structural%20design,one%20that%20a%20client%20expects) and [Refactoring Guru, Facade pattern](https://refactoring.guru/design-patterns/facade)).


## References:
[1](https://en.wikipedia.org/wiki/Structural_pattern#:~:text=In%20software%20engineering%2C%20structural%20design,one%20that%20a%20client%20expects) Wikipedia: Structural patterns

[2](https://else.fcim.utm.md/pluginfile.php/50523/mod_resource/content/2/StructuralDPs.pdf) Drumea Vasile's Structural Design Patterns presentation

[3](https://refactoring.guru/design-patterns/adapter) Refactoring guru - Adapter pattern

[4](https://refactoring.guru/design-patterns/decorator) Refactoring guru - Decorator pattern

[5](https://refactoring.guru/design-patterns/facade) Refactoring guru - Facade pattern