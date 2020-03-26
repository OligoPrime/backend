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
}