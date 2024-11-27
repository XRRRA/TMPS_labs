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
