package si.fri.core;

public enum OrderStatus {
    ORDERED("ordered"),
    RECEIVED("received");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}