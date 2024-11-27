## Behavioral Design Patterns in Java

**Author:** Iordan LIVIU

**Lab Topic:** Behavioral Design Patterns

---

### 1. Introduction

Behavioral Design Patterns focus on communication between objects, defining how objects interact and exchange information to perform tasks in a coordinated way. These patterns not only improve flexibility in interaction but also promote loose coupling, making the system easier to maintain and extend. [1](https://refactoring.guru/design-patterns/behavioral-patterns)

One such pattern is the **Observer Pattern**, which establishes a one-to-many dependency between objects, ensuring that changes in one object (the Subject) are automatically reflected in all its dependents (Observers). [2](https://refactoring.guru/design-patterns/observer)

This report documents the integration of the **Observer Pattern** into a tech store system to provide real-time notifications to clients whenever the inventory changes.

---

### 2. Pattern Used

#### **Observer Pattern**

The **Observer Pattern** allows an object, known as the Subject, to notify a list of observers when its state changes. This is particularly useful in scenarios requiring real-time updates, such as a store notifying clients about product availability. [3](https://refactoring.guru/design-patterns/observer)

---

### 3. Implementation

#### **3.1 Overview**

- **Subject**: The `Store` class acts as the Subject. It manages a list of observers and notifies them of changes in inventory.
- **Observer**: `StoreObserver` represents entities subscribed to receive updates (e.g., clients).
- **Concrete Implementation**: `Store` updates its inventory and notifies all registered observers.

---

#### **3.2 Code Snippets**

1. **Observer Interface** Defines the contract for all observers.

    ```java
    // domain/observer/Observer.java
    package domain.observer;
    
    public interface Observer {
        void update(String message);
    }
    ```

2. **Concrete Observer** Implements the `Observer` interface, listening for notifications from the `Store`.

    ```java
    // domain/observer/StoreObserver.java
    package domain.observer;
    
    public class StoreObserver implements Observer {
        private String name;
    
        public StoreObserver(String name) {
            this.name = name;
        }
    
        @Override
        public void update(String message) {
            System.out.println("Observer " + name + " received update: " + message);
        }
    }
    ```

3. **Subject Class** Manages observer registration, removal, and notification.

    ```java
    // domain/observer/Subject.java
    package domain.observer;
    
    import java.util.ArrayList;
    import java.util.List;
    
    public abstract class Subject {
        private List<Observer> observers = new ArrayList<>();
    
        public void attach(Observer observer) {
            observers.add(observer);
        }
    
        public void detach(Observer observer) {
            observers.remove(observer);
        }
    
        public void notifyObservers(String message) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }
    }
    ```

4. **Integration in the Store Class** The `Store` extends `Subject` and sends notifications when the inventory is updated.

    ```java
    // domain/Store.java
    package domain;
    
    import domain.observer.Subject;
    
    public class Store extends Subject {
    
        public void updateInventory(String product, int quantity) {
            System.out.println("Inventory updated: " + product + " - Quantity: " + quantity);
            notifyObservers("Product: " + product + " has new inventory: " + quantity);
        }
    }
    ```

5. **Main Class** Demonstrates the Observer Pattern in action.

    ```java
    // client/Main.java
    package client;
    
    import domain.observer.StoreObserver;
    import domain.Store;
    
    public class Main {
        public static void main(String[] args) {
            Store store = new Store();
    
            // Create and attach observers
            StoreObserver observer1 = new StoreObserver("Observer1");
            StoreObserver observer2 = new StoreObserver("Observer2");
            store.attach(observer1);
            store.attach(observer2);
    
            // Update inventory and notify observers
            store.updateInventory("Laptop", 10);
            store.updateInventory("Smartphone", 20);
        }
    }
    ```


---

### 4. Results

#### **Execution Output**

When running the `Main` class, the output is as follows:

```
Inventory updated: Laptop - Quantity: 10
Observer Observer1 received update: Product: Laptop has new inventory: 10
Observer Observer2 received update: Product: Laptop has new inventory: 10
Inventory updated: Smartphone - Quantity: 20
Observer Observer1 received update: Product: Smartphone has new inventory: 20
Observer Observer2 received update: Product: Smartphone has new inventory: 20
```

---

### 5. Conclusion

The **Observer Pattern** effectively decouples the `Store` and its clients by enabling real-time notifications without tightly coupling the two components. This implementation ensures the system remains scalable, allowing for easy addition of new observers or subjects without significant refactoring.

This pattern adheres to the **open-closed principle**, allowing extensions (e.g., adding more observers) without modifying existing code. Moreover, it provides a clear mechanism for handling dynamic communication between components.

By integrating this pattern, the tech store system now supports real-time updates, enhancing its functionality and user experience.

---

### References

[1](https://refactoring.guru/design-patterns/behavioral-patterns) Refactoring Guru: Behavioral Patterns  
[2](https://refactoring.guru/design-patterns/observer) Refactoring Guru: Observer Pattern  
[3](https://en.wikipedia.org/wiki/Observer_pattern) Wikipedia: Observer Pattern
