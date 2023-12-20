import java.io.Console;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Program {

    TimeLog timeLog = new TimeLog();

    public static void main(String[] args) {
        Program program = new Program();
        program.executionProgramm();
    }

    /**
     * Point d'entrée principal du programme.
     * Cette méthode lance une boucle de menu de console qui permet à l'utilisateur de choisir parmi
     * différentes options jusqu'à ce qu'il décide de quitter.
     *
     * @param args Arguments de ligne de commande non utilisés dans cette application.
     */
    public void executionProgramm(){
        Projet projet1 = new Projet("projet1", new Date(), null);
        Projet projet2 = new Projet("projet2", new Date(), null);
        Projet projet3 = new Projet("projet3", new Date(), null);
        
        
        Employe empoloye1 = new Employe("employe1", 123456789, Discipline.DESIGN1.toString(), new Date(), null, 20, 25);
        Employe employe2 = new Employe("employe2", 123456788, Discipline.DESIGN2.toString(), new Date(), null, 20, 25);
        Employe employe3 = new Employe("employe3", 123456787, Discipline.IMPLEMENTATION.toString(), new Date(), null, 20, 25);

        timeLog.ajouterEmployer(empoloye1);
        timeLog.ajouterEmployer(employe2);
        timeLog.ajouterEmployer(employe3);

        timeLog.ajouterProjet(projet1);
        timeLog.ajouterProjet(projet2);
        timeLog.ajouterProjet(projet3);
        
        timeLog.interfaceMenuPrincipal();
    }

    
}

