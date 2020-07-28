package si.fri.core.primer_enums;

public enum AmountAvailablePackType {
    TUBE("tube", "µl"),
    NONE("No value", "No unit"),
    PLATE("plate", "number of wells");

    private final String name;
    private final String unit;

    AmountAvailablePackType(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    @Override
    public String toString() {
        return name;
    }

    public static AmountAvailablePackType fromString(String name) {
        if (name == null || name.equalsIgnoreCase(""))
            return NONE;
        for (AmountAvailablePackType o : AmountAvailablePackType.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}