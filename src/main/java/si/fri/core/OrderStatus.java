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

    public static OrderStatus fromString(String name) {
        for (OrderStatus o : OrderStatus.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}