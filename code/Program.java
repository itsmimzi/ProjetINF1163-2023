import java.io.Console;
import java.sql.Time;
import java.util.ArrayList;
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
        timeLog.ajouterEmployer(new Employe(1, "employe1", "Nemploye1", 111222333, new Date(), null));
        timeLog.ajouterEmployer(new Employe(2, "employe2", "Nemploye2", 111222334, new Date(), null));
        timeLog.ajouterEmployer(new Employe(3, "employe3", "Nemploye3", 111222335, new Date(), null));
        timeLog.menuTimeLog();
    }

    
}
