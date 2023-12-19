import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Classe PROGRAM :
 * 
 * Cette classe coordonne l'exécution du programme TimeLog, 
 * s'assurant que les données nécessaires sont chargées et initialisées avant 
 * de permettre à l'utilisateur d'interagir avec le programme via le menu.
 */


public class Program {

    private TimeLog timeLog = new TimeLog();
    private Lecture lecture = new Lecture();
    private Ecriture ecriture = new Ecriture();

    public static void main(String[] args) {
        
        Program program = new Program();
        program.executionProgramm();
    }

    /**
     * Point d'entrée principal du programme.
     * Cette méthode lance une boucle de menu de console qui permet à l'utilisateur de 
     * choisir parmi différentes options jusqu'à ce qu'il décide de quitter.
     * 
     * Elle commence par charger les données employes et projets existants.
     * Sinon, on ajoute des donnees de test si les deux listes sont vides.
     *
     */
    public void executionProgramm(){

        List<Employe> employes = lecture.lireEmployes();
        timeLog.setListeEmployes(new ArrayList<>(employes));
        if (employes.isEmpty()) {
            timeLog.ajouterEmployer(new Employe("employe1", 111222330, "design2", new Date(), null, 0.0, 0.0));
            timeLog.ajouterEmployer(new Employe("employe2", 111222333, "design1", new Date(), null, 0.0, 0.0));
            timeLog.ajouterEmployer(new Employe("employe3", 111222334, "deploiement", new Date(), null, 0, 0));
            timeLog.ajouterEmployer(new Employe("employe4", 111222335, "implementation", new Date(), null, 0, 0));
        }
        ecriture.ecrireProjets(timeLog.getListeProjets());

        List<Projet> projets = lecture.lireProjets();
        timeLog.setListeProjets(new ArrayList<>(projets));
        if (projets.isEmpty()) {
            timeLog.ajouterProjet(new Projet("projet1", new Date(), null));
            timeLog.ajouterProjet(new Projet("projet2", new Date(), null));
            timeLog.ajouterProjet(new Projet("projet3", new Date(), null));
            timeLog.ajouterProjet(new Projet("projet4", new Date(), null));
        }
        ecriture.ecrireProjets(timeLog.getListeProjets());

        timeLog.menuTimeLog();
    }

    
}