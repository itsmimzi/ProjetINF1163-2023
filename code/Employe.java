import java.util.Date;


/*
 * Classe EMPLOYE : 
 * 
 * Un employé doit être caractérisé par un numéro identifiant ID; un nom;
 * une date d’embauche, et possible date de départ, 
 * un numéro d’assurance social, 
 * poste.
 * un historique de ses taux horaires de base ; 
 * et un autre taux pour temps supplémentaire;  
 * 
 * */


public class Employe {

    private static int dernierIdAttribue = 0;

    private int idEmploye;
    private String nomEmploye;
    private Date embauche;
    private Date depart;
    private String NAS; 
    private String poste;
    private Activite activiteEnCours = null;
    private double tauxBase;
    private double tauxSupp;
    // Seuil à partir duquel le taux d'heures supplémentaires est appliqué.
    private static int LIMITE_HEURES_SUPP = 46; 


    /**
     * Constructeur de Classe
     * 
     * @param nomEmploye
     * @param NAS
     * @param poste
     * @param embauche
     * @param depart
     * @param tauxBase
     * @param tauxSupp
     */

    public Employe(String nomEmploye, int NAS, String poste, Date embauche, Date depart, double tauxBase, double tauxSupp){
        this.idEmploye = ++dernierIdAttribue;        
        this.nomEmploye = nomEmploye;
        this.NAS = NAS;
        this.poste = poste;
        this.embauche = embauche;
        this.depart = depart;
        this.tauxBase = tauxBase;
        this.tauxSupp = tauxSupp;
    }

    /*********************************************************************************************************
     **************************************** ACCESSEURS & MODIFICATEURS *************************************
     *********************************************************************************************************/

    public int getIdEmploye(){
        return idEmploye;
    }
    public String getNomEmploye(){
        return nomEmploye;
    }
    public String getPosteEmploye(){
        return poste;
    }
    public String getNAS(){
        return NAS;
    }
    public Date getEmbauche(){
        return embauche;
    }
    public Date getDepart(){
        return depart;
    }
    public double getTauxBase(){
        return tauxBase;
    }
    public double getTauxSupp(){
        return tauxSupp;
    }

    /** 
     * Obtenir la liste des projets sur lesquels l'employé a travaillé.
     * 
     * @return Une liste de projets associés à l'employé.
     */
    public List<Projet> getProjetsTravailles(){
        List<Projet> projetsTravailles = new ArrayList<>();

        for(Activite activite : activites){
            Projet projet = activite.getProjet();
            if(projet != null && !projetsTravailles.contains(projet)){
                projetsTravailles.add(projet);
            }
        }

        return projetsTravailles;
    }

    /**
     * Calculer le salaire de l'employé.
     * 
     * @param heuresTravaillees Total des heures travaillées par l'employé. 
     * @return Le salaire correspondant aux total des heures travaillées.
     */
    public double calculerSalaire(double heuresTravaillees) {
        double salaire = 0;

        salaire += tauxBase * heuresTravaillees;

        // Si les heures travaillées dépassent une certaine limite, appliquer le taux supplémentaire
        if (heuresTravaillees > LIMITE_HEURES_SUPP) {
            double heuresSupplementaires = heuresTravaillees - LIMITE_HEURES_SUPP;
            salaire += tauxSupp * heuresSupplementaires;
        }
        return salaire;
    }
}
