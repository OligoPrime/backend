package si.fri.core.primer_enums;

public enum Orientation {
    FORWARD("forward"),
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
        for (Orientation o : Orientation.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}