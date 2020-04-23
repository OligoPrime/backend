package si.fri.core;

public enum Size {
    XS("XS"),
    S("S"),
    M("M"),
    L("L");

    private final String name;

    Size(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Size fromString(String name) {
        for (Size o : Size.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}