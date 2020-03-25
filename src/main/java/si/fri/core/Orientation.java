package si.fri.core;

public enum Orientation {
    FORWARD("Forward"),
    REVERSE("Reverse");

    private final String name;

    Orientation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}