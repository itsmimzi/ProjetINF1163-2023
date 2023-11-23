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
    private ArrayList<Activite> activites = new ArrayList<>();
    private ArrayList<Projet> projets = new ArrayList<>();
    
    public int getIdEmploye() {
        return idEmploye;
    }
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public String getPrenomEmploye() {
        return prenomEmploye;
    }
    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }
    public String getNomEmploye() {
        return nomEmploye;
    }
    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }
    public int getNas() {
        return nas;
    }
    public void setNas(int nas) {
        this.nas = nas;
    }
    public Date getDateEmbauche() {
        return dateEmbauche;
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public Date getDateDepart() {
        return dateDepart;
    }
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
    public Poste getPoste() {
        return poste;
    }
    public void setPoste(Poste poste) {
        this.poste = poste;
    }
    public TauxHoraire getTauxHoraire() {
        return tauxHoraire;
    }
    public void setTauxHoraire(TauxHoraire tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
    
        
}
