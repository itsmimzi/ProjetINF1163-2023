import java.util.ArrayList;
import java.util.Date;

/**
 * La classe Projet représente un projet avec des attributs tels que son identifiant,
 * son nom, sa date de début, sa date de fin, le nombre d'heures budgétaires par discipline,
 * la liste des activités associées au projet, et la liste des employés affectés au projet.
 */
public class Projet {
    // Attributs
    private int idProjet;
    private String nomProjet;
    private Date dateDebut;
    private Date dateFin;
    private Float nbrHeuresBudgetDiscipline;

    ArrayList<Activite> listeActivites = new ArrayList<>();
    // A enlever puisque chaque activite contient un employe
    // TODO
    ArrayList<Employe> listeEmployes = new ArrayList<>();


    /**
    * Construit un nouvel objet Projet avec les informations spécifiées.
    *
    * @param idProjet               L'identifiant unique du projet.
    * @param nomProjet              Le nom du projet.
    * @param dateDebut              La date de début du projet.
    * @param dateFin                La date de fin du projet.
    * @param nbrHeuresBudgetDiscipline Le nombre d'heures budgétaires par discipline pour le projet.
    * @param listeActivites         Une liste d'objets Activite associés au projet.
    * @param listeEmployes          Une liste d'objets Employe représentant les employés affectés au projet.
    */
    public Projet(int idProjet, String nomProjet, Date dateDebut, Date dateFin, Float nbrHeuresBudgetDiscipline,
            ArrayList<Activite> listeActivites, ArrayList<Employe> listeEmployes) {
        this.idProjet = idProjet;
        this.nomProjet = nomProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrHeuresBudgetDiscipline = nbrHeuresBudgetDiscipline;
        this.listeActivites = listeActivites;
        this.listeEmployes = listeEmployes;
    }

    /**
    * Construit un nouvel objet Projet avec les informations spécifiées.
    *
    * @param idProjet               L'identifiant unique du projet.
    * @param nomProjet              Le nom du projet.
    * @param dateDebut              La date de début du projet.
    * @param dateFin                La date de fin du projet.
    * @param nbrHeuresBudgetDiscipline Le nombre d'heures budgétaires par discipline pour le projet.
    */
    public Projet(int idProjet, String nomProjet, Date dateDebut, Date dateFin, Float nbrHeuresBudgetDiscipline) {
        this.idProjet = idProjet;
        this.nomProjet = nomProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrHeuresBudgetDiscipline = nbrHeuresBudgetDiscipline;
    }

    /**
     * Ajoute un employé à la liste des employés affectés au projet.
     * @param employe L'employé à ajouter.
     * @return Le nombre total d'employés affectés au projet après l'ajout.
     *         Retourne -1 si l'employé est déjà présent dans la liste.
     */
    public int ajouterEmployer(Employe employe){
        if(listeEmployes.contains(employe)){
           return -1; 
        }
        listeEmployes.add(employe);
        return  listeEmployes.size();
    }

    /**
     * Ajoute une activité à la liste des activités associées au projet.
     * @param activite L'activité à ajouter.
     * @return Le nombre total d'activités associées au projet après l'ajout.
     *         Retourne -1 si l'activité est déjà présente dans la liste.
     */
    public int ajouterActivite(Activite activite){
        if(listeActivites.contains(activite)){
           return -1; 
        }
        listeActivites.add(activite);
        return  listeActivites.size();
    }

    /**
    * Recherche un employé par son identifiant et son nom pour déterminer s'il est reconnu.
    *
    * @param idEmployeC L'identifiant de l'employé à rechercher.
    * @param nomEmploye Le nom de l'employé à rechercher.
    * @return 1 si l'employé est reconnu (c'est-à-dire s'il existe dans la liste des employés du projet),
    *         -1 sinon.
    */
    public int reconnaitreEmployer(int idEmployeC,String nomEmploye){
        if(obtenirEmploye(idEmployeC, nomEmploye) != null){
            return 1;
        }
        return -1;
    }


    /**
     * Recherche un employé par son identifiant et son nom.
     * @param idEmployeC L'identifiant de l'employé à rechercher.
     * @param nomEmploye Le nom de l'employé à rechercher.
     * @return L'employé correspondant à l'identifiant et au nom spécifiés, s'il existe,
     *         sinon retourne null.
     */
    public Employe obtenirEmploye(int idEmploye,String nomEmploye){
        Employe employeR = null;
        for(Employe employe:listeEmployes){
            if(employe.getID() == idEmploye && employe.getNom() == nomEmploye){
                employeR = employe;
            }
        }
        return employeR;
    }

    
}

