package si.fri.core.primer_enums;

public enum Orientation {
    FORWARD("forward"),
    NONE("No value"),
    REVERSE("reverse");

    private final String name;

    Orientation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Orientation fromString(String name) {
        if (name == null || name.equalsIgnoreCase(""))
            return NONE;
        for (Orientation o : Orientation.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}