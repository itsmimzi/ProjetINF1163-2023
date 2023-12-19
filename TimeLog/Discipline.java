public enum Discipline {

    DESIGN1("Design1"),
    DESIGN2("Design2"),
    IMPLEMENTATION("Implementation"),
    TEST("Test"),
    DEPLOIEMENT("Deploiement");

    private final String label;

    Discipline(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}