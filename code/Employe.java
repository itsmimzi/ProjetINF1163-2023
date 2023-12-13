import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Employe représente un employé d'une entreprise avec des informations telles que son identifiant,
 * son prénom, son nom, son numéro d'assurance sociale (NAS), sa date d'embauche, son poste, son taux horaire,
 * ses activités et les projets auxquels il participe.
 */
public class Employe {
    private int idEmploye;
    private String nomEmploye;
    private int nas;
    private Date dateEmbauche;
    private Date dateDepart;
    private Poste poste;
    private TauxHoraire tauxHoraire = new TauxHoraire(new ArrayList<>(), new ArrayList<>());
    private int NPE = 2;
    private Activite activiteEnCours = null;
    private ArrayList<Activite> activites = new ArrayList<>();
    private ArrayList<Projet> projets = new ArrayList<>();
    
    
    /**
     * Constructeur pour un employé avec des informations complètes.
     *
     * @param idEmploye     L'identifiant unique de l'employé.
     * @param nomEmploye    Le nom de l'employé.
     * @param nas           Le numéro d'assurance sociale de l'employé.
     * @param dateEmbauche  La date d'embauche de l'employé.
     * @param dateDepart    La date de départ de l'employé (peut être nulle).
     * @param poste         Le poste occupé par l'employé.
     * @param tauxHoraire   Le taux horaire de l'employé.
     * @param activites     La liste des activités liées à l'employé.
     * @param projets       La liste des projets auxquels l'employé participe.
     */
    public Employe(int idEmploye, String nomEmploye, int nas, Date dateEmbauche, Date dateDepart,
            Poste poste, TauxHoraire tauxHoraire, ArrayList<Activite> activites, ArrayList<Projet> projets) {
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.nas = nas;
        this.dateEmbauche = dateEmbauche;
        this.dateDepart = dateDepart;
        this.poste = poste;
        this.tauxHoraire = tauxHoraire;
        this.activites = activites;
        this.projets = projets;
    }

    /**
     * Constructeur simplifié pour un employé sans informations complètes.
     *
     * @param idEmploye     L'identifiant unique de l'employé.
     * @param nomEmploye    Le nom de l'employé.
     * @param nas           Le numéro d'assurance sociale de l'employé.
     * @param dateEmbauche  La date d'embauche de l'employé.
     * @param poste         Le poste occupé par l'employé.
     */
    public Employe(int idEmploye, String nomEmploye, int nas, Date dateEmbauche,Poste poste){
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.nas = nas;
        this.dateEmbauche = dateEmbauche;
        this.poste = poste;
        this.dateDepart = null;
    }


    /**
     * Modifie le salaire de base de l'employé en ajoutant un nouveau taux horaire de base.
     *
     * @param taux Le nouveau taux horaire de base à ajouter.
     */
    public void modifierSalaireBase(float taux){
        tauxHoraire.modifierTauxBaseCourant(taux);
    }
    
    /**
     * Obtient l'identifiant unique de l'employé.
     *
     * @return L'identifiant unique de l'employé.
     */
    public int getID(){
        return idEmploye;
    } 

    /**
     * Obtient le nom de l'employé.
     *
     * @return Le nom de l'employé.
     */
    public String getNom(){
        return nomEmploye;
    }

    public Activite getActiviteCourant(){
        return activiteEnCours;
    }

    public void setActiviteCourant(Activite activite){
        this.activiteEnCours = activite;
    }
}
