import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static int dernierIdAttribue = 0;

    private int idRapport;
    private String type;
    private String titre;
    private String corps;


    /**
     * Constructeur de classe
     *
     * @param type              Type du rapport : rapportProjet ou rapportSalaire
     * @param titre             Titre du rapport
     */
    public Rapport(){
        this.idRapport = ++dernierIdAttribue;
        this.type = "";
        this.titre = "";
        this.corps = "";
    }


    /*********************************************************************************************************
     **************************************** ACCESSEURS & MODIFICATEURS *************************************
     *********************************************************************************************************/

     public int getIdRapport(){
        return idRapport;
     }
     public String getTypeRapport(){
        return type;
     }
     public String getTitreRapport(){
        return titre;
     }
     public String getCorpsRapport(){
        return corps;
     }
     public void setTypeRapport(String type){
        this.type = type;
     }
     public void setTitreRapport(String titre){
        this.titre = titre;
     }



     /**************************************** RAPPORTS PROJET************************************************
     *********************************************************************************************************/

    /**
     * Générer un rapport d’état de chaque projet choisi.
     *
     * @param projet Le projet dont on veut générer le rapport.
     */
    public void rapportProjet(Projet projet) {

        type = "rapportProjet";
        titre = "RAPPORT ETAT : " + projet.getNomProjet() ;

        // Calculer le nombre d'heures travaillées par discipline
        Map<String, Double> heuresTravailleesParDiscipline = new HashMap<>();
        heuresTravailleesParDiscipline = projet.getHeuresTravailleesParDiscipline();

        // Calculer le pourcentage d'avancement total et par discipline
        for (Map.Entry<String, Double> entry : heuresTravailleesParDiscipline.entrySet()) {
            String discipline = entry.getKey();
            double heuresTravaillees = entry.getValue();
            double heuresBudget = projet.getHeureBudgetParDiscipline(discipline);

            double pourcentageAvancement = (heuresTravaillees / heuresBudget) * 100;

            corps = String.format("Discipline : %s \nHeures travaillées : %.2f \nHeures budgétées : %.2f \nPourcentage d'avancement : %.2f \n\n",
                                    discipline, heuresTravaillees, heuresBudget, pourcentageAvancement);
        }
    }

    /**
     * Générer un rapport d’état global de l’ensemble des projets.
     *
     * @param projets La liste de tous les projets.
     */
    public void genererRapportGlobal(List<Projet> projets) {

        type = "rapportProjet";
        titre = "RAPPORT ETATS GLOBAL";

        double totalHeuresTravaillees = 0.0;
        double totalHeuresBudget = 0.0;
        for (Projet projet : projets){
            totalHeuresTravaillees += projet.getTotalHeuresTravailleesInProjet();
            totalHeuresBudget += projet.getTotalHeuresBudgetInProjet();
        }
        double pourcentageAvancementGlobal = (totalHeuresTravaillees / totalHeuresBudget) * 100;

        corps = String.format("Total des heures travaillées : %.2f\nTotal des heures budgétées : %.2f\nPourcentage d'avancement global : %.2f",
                                totalHeuresTravaillees, totalHeuresBudget, pourcentageAvancementGlobal);
    }



  /**************************************** RAPPORTS SALAIRE ***********************************************
   *********************************************************************************************************/


   /**
    * Générer le rapport des valeurs (en salaire) des heures travaillées de l’employé.
    *
    * @param employe L'employé pour lequel on génère le rapport.
    * @param date La date choisie à partir de laquelle on considère les heures travaillées.
    */
   public void genererRapportSalaireEmploye(Employe employe, Date date) {

       type = "rapportSalaire";
       titre = "Rapport des valeurs en salaire pour : " + employe.getNomEmploye();

       List<Projet> projetsTravailles = employe.getProjetsTravaillesParEmploye();

       for (Projet projet : projetsTravailles) {

           Date dateDebutProjet = projet.getDateDebut();

           if (dateDebutProjet != null && employe.aTravailleDepuisDate(projet, date)) {

               Map<String, Double> heuresTravailleesParDiscipline = projet.getHeuresTravailleesParDiscipline();
               double heuresTravailleesTotal = heuresTravailleesParDiscipline.values().stream().mapToDouble(Double::doubleValue).sum();
               double salaire = employe.calculerSalaire(heuresTravailleesTotal);

               String rapport = String.format("Projet: %s, Salaire brut: %.2f\n", projet.getNomProjet(), salaire);
               corps += rapport + "\n";
           }
       }
   }

   /**
    * Générer un rapport total des salaires brutes et total des salaires nets de tous les employés.
    *
    * @param employes La liste de tous les employés.
    */

   public void genererRapportTotauxSalaires(List<Employe> employes) {

       type = "rapportSalaire";
       titre = "RAPPORT TOTAL DES SALAIRES";


       double totalSalairesBruts = 0.0;
       for (Employe employe : employes){
           double totalHeuresTravailleesParEmploye = employe.getTotalHeuresTravailleesParEmploye();
           totalSalairesBruts += employe.calculerSalaire(totalHeuresTravailleesParEmploye);
       }
       double totalSalairesNets = 0.6 * totalSalairesBruts;

       corps = String.format("Total des salaires bruts : %.2f\nTotal des salaires nets : %.2f", totalSalairesBruts, totalSalairesNets);
   }


   @Override
   public String toString(){
        return "Type : " + this.type + "Titre : " + this.titre + "Corps : " + this.corps;
   }

}
