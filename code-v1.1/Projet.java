import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Classe PROJET :
 *
 * Un projet doit comprendre un nom, un identifiant unique,
 * une date de début et une date de fin,
 * un nombre d’heures budgétées pour chacune des disciplines,
 * enfin une liste d'activités associées au projet.
 *
 * */


public class Projet {

    private static int dernierIdAttribue = 0;

    private int idProjet;
    private String nomProjet;
    private Date dateDebut;
    private Date dateFin;
    private List<Activite> activitesDansProjet;
    private Map<String, Double> heuresBudgetParDiscipline;


    /**
     * Constructeur de Classe
     *
     * @param nomProjet                 Nom du projet.
     * @param dateDebut                 Date de début du projet.
     * @param dateFin                   Date de fin du projet.
     */
    public Projet(String nomProjet, Date dateDebut, Date dateFin){
        this.idProjet = ++dernierIdAttribue;
        this.nomProjet = nomProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.activitesDansProjet = new ArrayList<>();
        this.heuresBudgetParDiscipline = new HashMap<>();
    }

    /*********************************************************************************************************
     **************************************** ACCESSEURS & MODIFICATEURS *************************************
     *********************************************************************************************************/

    public int getIdProjet() {
        return idProjet;
    }

    public String getNomProjet() {
       return nomProjet;
   }

   public Date getDateDebut() {
       return dateDebut;
   }

   public Date getDateFin() {
       return dateFin;
   }
   public List<Activite> getActivitesDansProjet(){
       return activitesDansProjet;
   }

   public void setNomProjet(String nomProjet) {
       this.nomProjet = nomProjet;
   }
   public void setDateDebut(Date dateDebut) {
       this.dateDebut = dateDebut;
   }
   public void setDateFin(Date dateFin) {
       this.dateFin = dateFin;
   }
   /**************************************** FONCTIONS DE CLASSE *******************************************
    *********************************************************************************************************/

    public void ajusterHeureBudgetParDiscipline(String discipline,double montant){
        heuresBudgetParDiscipline.replace(discipline, montant);
   }

    /**
     * Ajouter une activité a la liste des activités associée au Projet.
     *
     * @param activite L'activité à ajouter.
     */
    public void ajouterActivite(Activite activite) {

        activitesDansProjet.add(activite);

        String discipline = activite.getDiscipline().getLabel();
        double heuresBudget = activite.getHeuresBudget();

        if(heuresBudgetParDiscipline.containsKey(discipline)){
            double heuresActuelles = heuresBudgetParDiscipline.get(discipline);
            heuresBudgetParDiscipline.put(discipline, heuresActuelles + heuresBudget);
        }
        else {
            heuresBudgetParDiscipline.put(discipline, heuresBudget);
        }

    }

    /**
    * Calculer le total des heures travaillees pour un projet.
    *
    * @param activites La liste activites qui appartiennent au projet.
    * @return le total des heures travaillees de toutes les activites du projet.
    */
   public double getTotalHeuresTravailleesInProjet(){
       double totalHeuresTravaillees = 0.0;

       for(Activite activite : activitesDansProjet){
           totalHeuresTravaillees += activite.calculerHeuresTravaillees();
       }
       return totalHeuresTravaillees ;
   }

   /**
    * Obtenir le nombre d'heures budgétées pour une discipline donnée.
    *
    * @param discipline Discipline pour laquelle on souhaite obtenir le nombre d'heures budgétées.
    * @return Le nombre d'heures budgétées pour la discipline spécifiée.
    */
   public double getHeureBudgetParDiscipline(String discipline){
        return heuresBudgetParDiscipline.get(discipline);
   }

   /**
    * Obtenir le nombre total d'heures budgétées pour le projet, et toutes ses disciplines.
    *
    * @return Le nombre total d'heures budgétées.
    */
   public double getTotalHeuresBudgetInProjet() {
       return heuresBudgetParDiscipline.values().stream().mapToDouble(Double::doubleValue).sum();
   }

   /**
    * Retourne un mapping des heures travaillées par discipline pour ce projet.
    *
    * @return Mapping des heures travaillées par discipline.
    */
   public Map<String, Double> getHeuresTravailleesParDiscipline(){
       Map<String, Double> heuresParDiscipline = new HashMap<>();

       for (Activite activite : activitesDansProjet){

           String discipline = activite.getDiscipline().getLabel();
           double heuresTravaillees = activite.calculerHeuresTravaillees();

           if(heuresParDiscipline.containsKey(discipline)){
               double heuresActuelles = heuresParDiscipline.get(discipline);
               heuresParDiscipline.put(discipline, heuresActuelles + heuresTravaillees);
           }
           else{
               heuresParDiscipline.put(discipline, heuresTravaillees);
           }
       }
       return heuresParDiscipline;
   }

   

}
