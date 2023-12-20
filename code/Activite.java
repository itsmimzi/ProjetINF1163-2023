import java.time.Duration;
import java.time.LocalDateTime;


/*
 * Classe ACTIVITE : 
 * 
 * Tout employé peut se connecter pour signaler qu’il a commencé ou terminé une activité.
 * L’heure et date de début ou d’arrêt d’une activité sont enregistrées par le système.
 * 
 * Une activité est caractérisée :
 *   par le nom du projet auquelle elle est associée,
 *   une discipline de travail (design, implémentation, etc. ),
 *   une seule activité est permise à la fois par employé. 
 * 
 * */

public class Activite {

    // Variable statique qui suit les affectations d'ID aux activités
    private static int dernierIdAttribue = 0;

    private int idActivite;
    private Projet projet;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private Employe employe; 
    private Discipline discipline;
    private double heuresBudget;

    /**
     * Constructeur de Classe
     *
     * @param idActivite L'identifiant unique de l'activité
     * @param employe    L'employé qui a signaler cette activité
     */

     public Activite(Employe employe, double heuresBudget) {
        this.idActivite = ++dernierIdAttribue;
        this.employe = employe;
        this.heuresBudget = heuresBudget; 
        this.discipline = discipline.valueOf(employe.getPosteEmploye());
    }
    
    /*********************************************************************************************************
     **************************************** ACCESSEURS & MODIFICATEURS *************************************
     *********************************************************************************************************/
    
     public int getIdActivite(){
        return idActivite;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Projet getProjet(){
        return projet;
    }

    public LocalDateTime getHeureDebut(){
        return heureDebut;
    }

    public LocalDateTime getHeureFin(){
        return heureFin;
    }

    public Discipline getDiscipline() {
       return discipline;
    }

    public void setHeureFin(LocalDateTime heure){
        this.heureFin = heure;
    }

    public double getHeuresBudget() {
        return heuresBudget;
    }

    /***************************************** FONCTIONS DE CLASSE *******************************************
     *********************************************************************************************************/

    /**
    /**
     * Signaler le debut d'une activité avec l'heure et la date locale du systeme.
     * 
     * @param nomProjet Projet auquel est associée l'activité
     * @param discipline Discipline de l'activité
     */
    public void signalerDebutActivite(Projet projet, Discipline discipline) {
        if (heureDebut == null && heureFin == null) {
            this.projet = projet;
            this.discipline = discipline;
            this.heureDebut = LocalDateTime.now();
        } else {
            System.out.println("L'employé a déjà une activité en cours.");
        }
    }

    /**
     * Signaler la fin d'une activité avec l'heure et la date locale du système.
     */
    public void signalerFinActivite() {
        if (heureDebut != null && heureFin == null) {
            
            this.heureFin = LocalDateTime.now();
        } else {
            System.out.println("L'employé n'a pas d'activité en cours.");
        }
    }

    /**
     * Calculer la durée d'une activité en fonction de son temps de début et de fin.
     * 
     * @return La durée de l'activité
     */
    public long calculerHeuresTravaillees() {
        if (heureDebut != null && heureFin != null) {
            Duration duree = Duration.between(heureDebut, heureFin);
            return duree.toHours();
        } else {
            System.out.println("L'activité n'est pas complète. Impossible de calculer les heures travaillées.");
            return 0;
        }
    }

}