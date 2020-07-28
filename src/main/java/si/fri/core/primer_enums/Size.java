package si.fri.core.primer_enums;

public enum Size {
    XS("XS"),
    S("S"),
    M("M"),
    NONE("No value"),
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
        if (name == null || name.equalsIgnoreCase(""))
            return NONE;
        for (Size o : Size.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}