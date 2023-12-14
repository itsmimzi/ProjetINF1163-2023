/*
 * Classe RAPPORT : 
 * 
 * Permet de générer 
 *    un rapport d’état de chaque projet choisi, 
 *    un rapport d’état global de l’ensemble des projets,
 *    un rapport des valeurs (en salaire) des heures travaillées de l’employé; 
 *       avec par défaut depuis le début de la dernière semaine impaire. 
 *       Ce rapport donne le salaire brut pour la période choisie, pour chacun des projets sur lesquels 
 *       l’employé a travaillé durant la période choisie.
 *    un rapport  des totaux des salaires (bruts et nets) de tous les employés,
 *       il fournit donc seulement deux valeurs; total des salaires brutes et total des salaires nets.
 * 
 * */

public class Rapport {

    //     private String nomProjet;
    // private List<Activité> activités;

    // public Rapport(String nomProjet, List<Activité> activités) {
    //     this.nomProjet = nomProjet;
    //     this.activités = activités;
    // }

    // // Getter et setter pour le nom du projet
    // public String getNomProjet() {
    //     return nomProjet;
    // }

    // public void setNomProjet(String nomProjet) {
    //     this.nomProjet = nomProjet;
    // }

    // // Getter et setter pour la liste des activités
    // public List<Activité> getActivités() {
    //     return activités;
    // }

    // public void setActivités(List<Activité> activités) {
    //     this.activités = activités;
    // }

    // // Méthode pour calculer la durée totale des activités en millisecondes
    // public long obtenirDuréeTotale() {
    //     long duréeTotale = 0;
    //     for (Activité activité : activités) {
    //         duréeTotale += activité.obtenirTempsÉcoulé();
    //     }
    //     return duréeTotale;
    // }

    

    public void genererRapportProjet(Projet projet) {
        
        
    }

    /* Méthode qui donne une vue d'ensemble complète de l'état actuel de tous les projets dans le système.
    * Cela impliquerait des informations agrégées concernant chaque projet individuel:
    **Total des heures travaillées sur tous les projets.
    **Total des heures budgétées sur tous les projets.
    **Pourcentage global d'avancement basé sur les heures travaillées et les heures budgétées.
    */
    
    public void genererRapportGlobal(List<Projet> projets) {
    // Affichage des rapports sur la console
    System.out.println("Rapport d'état global :");

    // Total des heures travaillées sur tous les projets
    double totalHeuresTravaillées = 0;

    // Total des heures budgétées sur tous les projets
    double totalHeuresBudgetées = 0;

    // Parcours de tous les projets
    for (Projet projet : projets) {
        System.out.println("Projet : " + projet.getNom());
        System.out.println("Date de début : " + projet.getHeureDebut());
        System.out.println("Date de fin : " + projet.getHeureFin());

        // Ajoutez d'autres informations spécifiques du projet si nécessaire

        System.out.println("Activités associées :");

        // Total des heures travaillées sur le projet
        double heuresTravailléesProjet = 0;

        // Total des heures budgétées sur le projet
        double heuresBudgetéesProjet = 0;

        // Parcours de toutes les activités associées au projet
        for (Activité activité : activités) {
            if (activité.getProjet().equals(projet)) {
                System.out.println(" - " + activité.getNom());

                // Ajoutez d'autres détails des activités si nécessaire
                System.out.println("    Discipline : " + activité.getDiscipline());
                System.out.println("    Durée : " + activité.obtenirTempsÉcoulé() + " ms");

                // Mise à jour des totaux
                heuresTravailléesProjet += activité.obtenirTempsÉcoulé();
                heuresBudgetéesProjet += projet.getHeuresBudgetées();
            }
        }

        System.out.println("Total des heures travaillées sur le projet : " + heuresTravailléesProjet);
        System.out.println("Total des heures budgétées sur le projet : " + heuresBudgetéesProjet);

        // Mise à jour des totaux globaux
        totalHeuresTravaillées += heuresTravailléesProjet;
        totalHeuresBudgetées += heuresBudgetéesProjet;

        System.out.println();
    }

    System.out.println("Total des heures travaillées sur tous les projets : " + totalHeuresTravaillées);
    System.out.println("Total des heures budgétées sur tous les projets : " + totalHeuresBudgetées);
}

    }

    public void genererRapportSalaireEmploye(Projet projet, Employe employe) {
       
    }

    public void genererRapportTotauxSalaires(List<Employe> employes) {
       
    }

     public void genererTousLesRapports(List<Projet> projets, List<Employe> employes) {
         
        for (Projet projet : projets) {
            genererRapportProjet(projet);
        }

        genererRapportGlobal(projets);

        for (Employe employe : employes) {
            for (Projet projet : employe.getProjetsTravailles()) {
                genererRapportSalaireEmploye(projet, employe);
            }
        }
         
        genererRapportTotauxSalaires(employes);
    }

    
    
}
