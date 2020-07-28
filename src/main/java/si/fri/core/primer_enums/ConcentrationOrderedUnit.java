package si.fri.core.primer_enums;

public enum ConcentrationOrderedUnit {
    NANOMOL("nmol"),
    MIKROM("µM"),
    NONE("No value"),
    NANOM("nM");

    private final String name;

    ConcentrationOrderedUnit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ConcentrationOrderedUnit fromString(String name) {
        if (name == null || name.equalsIgnoreCase(""))
            return NONE;
        for (ConcentrationOrderedUnit o : ConcentrationOrderedUnit.values()) {
            if (o.name.equalsIgnoreCase(name)) {
                return o;
            }
        }
        return null;
    }
}