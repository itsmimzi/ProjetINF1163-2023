public enum Discipline {

    DESIGN1("DESIGN1"),
    DESIGN2("DESIGN2"),
    IMPLEMENTATION("IMPLEMENTATION"),
    TEST("TEST"),
    DEPLOIEMENT("DEPLOIEMENT");

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