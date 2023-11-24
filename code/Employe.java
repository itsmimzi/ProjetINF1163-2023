import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Employe {
    private int idEmploye;
    private String prenomEmploye;
    private String nomEmploye;
    private int nas;
    private Date dateEmbauche;
    private Date dateDepart;
    private Poste poste;
    private TauxHoraire tauxHoraire;
    private int NPE = 2;
    private ArrayList<Activite> activites = new ArrayList<>();
    private ArrayList<Projet> projets = new ArrayList<>();
    

    public Employe(int idEmploye, String prenomEmploye, String nomEmploye, int nas, Date dateEmbauche, Date dateDepart,
            Poste poste, TauxHoraire tauxHoraire, ArrayList<Activite> activites, ArrayList<Projet> projets) {
        this.idEmploye = idEmploye;
        this.prenomEmploye = prenomEmploye;
        this.nomEmploye = nomEmploye;
        this.nas = nas;
        this.dateEmbauche = dateEmbauche;
        this.dateDepart = dateDepart;
        this.poste = poste;
        this.tauxHoraire = tauxHoraire;
        this.activites = activites;
        this.projets = projets;
    }
    
        
}
