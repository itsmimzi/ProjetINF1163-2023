import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Classe permettant de tester les fonctionnalite du numero 15.
 * 15. Un employé peut aussi se connecter pour demander le nombre d’heures travaillées de
 * base et supplémentaires pour une période spécifiée.
 * 
 * 
 * 
 * Une simulation des fonction sera fait afin de tester le code puisque le tout se fait a 
 * l'aide de l'entree a la console. Chaque fonction principal utiliser lors de cette 
 * operation seront tester. 
 */
public class test_15 {
    TimeLog timeLog = new TimeLog();

    @Test
    public void testerObtenirHeureTravaillerEmploye(){
        Calendar c1 = Calendar.getInstance(); 
        c1.set(Calendar.MONTH, 12);  
        c1.set(Calendar.DATE, 16);  
        c1.set(Calendar.YEAR, 2023); 

        Calendar c2 = Calendar.getInstance(); 
        c2.set(Calendar.MONTH, 12);  
        c2.set(Calendar.DATE, 18);  
        c2.set(Calendar.YEAR, 2025); 
  
        // creating a date object with specified time. 
        Date dateOne = c1.getTime(); 
        Date dateTwo = c2.getTime();
        Employe employe = new Employe("employe1", 111222333, "DESIGN1", new Date(), null, 1000008.98, 0.0);
        Projet projet = new Projet("projet1", dateOne, dateTwo);
        timeLog.ajouterEmployer(employe);
        Activite nouvelleActivite = new Activite(employe,0);
        nouvelleActivite.signalerDebutActivite(projet, Discipline.valueOf(employe.getPosteEmploye()));
        nouvelleActivite.signalerFinActivite();
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            System.out.println("Le thread a été interrompu pendant le sommeil");
        }
        double montant = employe.calculerSalaire(employe.getTotalHeuresTravailleesParEmploye());
        assertTrue(montant == 0.0);
        // Difficile a tester parce que signaler une activite va debuter une activiter selon le temps present. 
    }
}
