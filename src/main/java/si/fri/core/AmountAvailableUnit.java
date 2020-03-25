package si.fri.core;

public enum AmountAvailableUnit {
    NANOMOL("nmol"),
    MIKROL("µL"),
    MIKROM("µM"),
    NANOM("nM");

    private final String name;

    AmountAvailableUnit(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}