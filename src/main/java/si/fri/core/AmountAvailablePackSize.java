package si.fri.core;

public enum AmountAvailablePackSize {
    TUBE("tube"),
    PLATE("plate");

    private final String name;

    AmountAvailablePackSize(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static AmountAvailablePackSize fromString(String name) {
        for (AmountAvailablePackSize o : AmountAvailablePackSize.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}