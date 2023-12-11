import java.sql.Time;
import java.util.ArrayList;

public class Activite {

    private String idActivite;
    private Time heureDebut;
    private Time heureFin;
    private Employe employe // L'employé associé à cette activité
    ArrayList<Discipline> disciplines = new ArrayList<>(1);


    public Activite(String idActivite, Time heureDebut, Time heureFin, ArrayList<Discipline> disciplines) {
        this.idActivite = idActivite;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.disciplines = disciplines;
    }
     public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public Employé getEmployé() {
        return employé;
    }

    public void setEmployé(Employé employé) {
        this.employé = employé;
    }

    // Méthode pour obtenir le temps écoulé en millisecondes
    public float obtenirTempsEcouler(){
        float tempsEcouler = heureFin.getTime() -heureDebut.getTime();
        return (float)tempsEcouler / 3600000.0f;
    }

    
}
