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

    public void genererRapportProjet(Projet projet) {
        
    }

    public void genererRapportGlobal(List<Projet> projets) {
       
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
