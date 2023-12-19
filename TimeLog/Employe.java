import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
    private int NAS; 
    private String poste;
    private double tauxBase;
    private double tauxSupp;
    private Activite activiteEnCours = null;
    // Seuil à partir duquel le taux d'heures supplémentaires est appliqué.
    private static int LIMITE_HEURES_SUPP = 46; 
    private List<Activite> activites;


    /**
     * Constructeur de Classe
     * 
     * @param nomEmploye                Nom de l'employé
     * @param NAS                       Num. Assurance Sociale de l'employé
     * @param poste                     Poste occupé
     * @param embauche                  Date d'embauche de l'employé
     * @param depart                    Date de départ de l'employé (default NULL)
     * @param tauxBase                  Taux de rémunération heures de base
     * @param tauxSupp                  Taux de rémunération heures supplémentaires
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
        this.activites = new ArrayList<>();
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
    public int getNAS(){
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
    public List<Activite> getActivites(){
        return activites;
    }
    public Activite getActiviteCourant(){
        return activiteEnCours;
    }

    public void setActiviteCourant(Activite activite){
        activiteEnCours = activite;
    }



    /**************************************** FONCTIONS DE CLASSE *******************************************
    *********************************************************************************************************/
    
    
    /** 
     * Obtenir la liste des projets sur lesquels l'employé a travaillé.
     * 
     * @return Une liste de projets associés à l'employé.
     */
    public List<Projet> getProjetsTravaillesParEmploye(){
        
        List<Projet> projetsTravailles = new ArrayList<>();             

        for(Activite activite : activites){
            Projet projet = activite.getProjet();
            if(projet != null && !projetsTravailles.contains(projet))
                projetsTravailles.add(projet);
        }
        return projetsTravailles;
    }

    /**
     * Calculer le nombre total des heures travaillées par l'employé, 
     * dans tous les projets sur lequel il a effectué une ou plusieurs activités.
     * 
     * @return le nombre total des heures travaillées.
     */
    public double getTotalHeuresTravailleesParEmploye(){
        double totalHeuresTravailleesparEmploye = 0.0;
        var totalProjetsTravailles = getProjetsTravaillesParEmploye();
        for ( Projet projet : totalProjetsTravailles){
            totalHeuresTravailleesparEmploye += projet.getTotalHeuresTravailleesInProjet();
        }
        return totalHeuresTravailleesparEmploye;
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

        /**
     * Vérifier si l'employé a travaillé sur un projet depuis une date précise.
     * 
     * @param projetVerif Le projet à vérifier.
     * @param dateVerif La date a partir de laquelle on veut verifier.
     * @return vrai si l'employé a travaillé sur le projet depuis la date précisé ; faux sinon.
     */
    public boolean aTravailleDepuisDate(Projet projetVerif, Date dateVerif) {

        List<Projet> projetsTravailles = getProjetsTravaillesParEmploye();

        for (Projet projet : projetsTravailles ) {
            if(projet.getIdProjet() == projetVerif.getIdProjet()){    
                if (projetVerif.getDateDebut().after(dateVerif)) 
                    return true;
            }
        }
        return false;
    }

    /**
     * Générer un talon de paie pour la dernière période de paie ou une date spécifiée.
     *
     * @param datePaie La date de la période de paie pour laquelle générer le talon.
     */
    public void genererTalonDePaie(Date datePaie) {
  
        if (datePaie == null) {
            datePaie = new Date();
        }

        // récupère la liste des projets sur lesquels l'employé a travaillé depuis la dernière période de paie
        List<Projet> projetsTravaillesDepuisPaie = new ArrayList<Projet>();
        for (Projet projet : getProjetsTravaillesParEmploye()){
            if (aTravailleDepuisDate(projet, datePaie)){
                projetsTravaillesDepuisPaie.add(projet);
            }
        }
        // calcul le salaire brut total pour la période spécifiée
        double totalHeuresTravaillees = projetsTravaillesDepuisPaie.stream()
                .mapToDouble(Projet::getTotalHeuresTravailleesInProjet)
                .sum();
        double salaireBrut = calculerSalaire(totalHeuresTravaillees);
        double salaireNet = salaireBrut * 0.6;          // applique le facteur de 0.6 pour obtenir le salaire net


        String talonDePaie = String.format("Talon de paie pour %s - Date de paie : %s\n" +
                        "Salaire brut : %.2f\nSalaire net : %.2f",
                getNomEmploye(), new SimpleDateFormat("dd/MM/yyyy").format(datePaie), salaireBrut, salaireNet);

        System.out.println(talonDePaie);
    }



}

