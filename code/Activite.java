import java.sql.Time;
import java.util.ArrayList;

public class Activite {

    private String idActivite;
    private Time heureDebut;
    private Time heureFin;
    ArrayList<Discipline> disciplines = new ArrayList<>(1);


    public Activite(String idActivite, Time heureDebut, Time heureFin, ArrayList<Discipline> disciplines) {
        this.idActivite = idActivite;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.disciplines = disciplines;
    }

    public float obtenirTempsEcouler(){
        float tempsEcouler = heureFin.getTime() -heureDebut.getTime();
        return (float)tempsEcouler / 3600000.0f;
    }

    
}
