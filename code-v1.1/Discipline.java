public enum Discipline {

    DESIGN1("design1"),
    DESIGN2("design2"),
    IMPLEMENTATION("implementation"),
    TEST("test"),
    DEPLOIEMENT("deploiement");

    private String label;

    Discipline(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String labelEnString){
        this.label = labelEnString;
    }
}