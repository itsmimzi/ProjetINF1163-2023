
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Classe permettant de tester les fonctionnalite du numero 14.
 * 14. Avec un compte employé (non admin) on peut se connecter pour signaler les débuts et
 *  les terminaisons de ses heures de ses activités;
 * 
 * Elle testera un debut et une fin d'activite.
 * 
 * Une simulation des fonction sera fait afin de tester le code puisque le tout se fait a 
 * l'aide de l'entree a la console. Chaque fonction principal utiliser lors de cette 
 * operation seront tester. 
 */
public class test_14 {
    TimeLog timeLog = new TimeLog();

    /*
     * Simulation d'un test de debut d'activite. 
     * 
     * Si le test retourne true, cela veut dire que l'activite a bien ete debuter par
     * l'employe test.
     * 
     * Teste la fonctionnalité de démarrage d'une activité pour un employé dans la classe {@code TimeLog}.
     *
     * <p>Ce test valide le processus de démarrage d'une activité pour un employé. Il crée un nouvel employé
     * et un projet, puis ajoute cet employé à la liste des employés du système. Ensuite, il crée une nouvelle
     * activité associée à cet employé et démarre cette activité en l'associant au projet et au poste de l'employé.</p>
     *
     * <p>Le test est considéré comme réussi si l'heure de début de la nouvelle activité est non nulle, 
     * indiquant que l'activité a correctement démarré et que l'heure de début a été enregistrée.</p>
     * 
     */
    @Test
    public void testDebutActivite(){
        Employe employe = new Employe("employe1", 111222333, "DESIGN1", new Date(), null, 100.0, 0.0);
        Projet projet = new Projet("projet1", new Date(), null);
        timeLog.ajouterEmployer(employe);
        Activite nouvelleActivite = new Activite(employe,0);
        nouvelleActivite.signalerDebutActivite(projet, Discipline.valueOf(employe.getPosteEmploye()));
        assertTrue(nouvelleActivite.getHeureDebut() !=null);
    }

    /*
     * Simulation d'un test de fin d'activite. 
     * 
     * Si le test retourne true, cela veut dire que l'activite a bien ete fini par
     * l'employe test.
     * 
     * <p>Ce test évalue la capacité du système à enregistrer correctement la fin d'une activité pour un employé.
    * Il commence par créer un nouvel employé et un nouveau projet, puis ajoute cet employé à la liste des employés
    * du système. Après cela, il crée une nouvelle activité, démarre cette activité en l'associant au projet et au
    * poste de l'employé, puis signale la fin de l'activité.</p>
    *
    * <p>Le test est considéré comme réussi si l'heure de fin de l'activité est non nulle, indiquant que la fin de
    * l'activité a été correctement enregistrée dans le système.</p>
    */
    @Test 
    public void testFinActivite(){
        Employe employe = new Employe("employe1", 111222333, "DESIGN1", new Date(), null, 100.0, 0.0);
        Projet projet = new Projet("projet1", new Date(), null);
        timeLog.ajouterEmployer(employe);
        Activite nouvelleActivite = new Activite(employe,0);
        nouvelleActivite.signalerDebutActivite(projet, Discipline.valueOf(employe.getPosteEmploye()));
        nouvelleActivite.signalerFinActivite();
        assertTrue(nouvelleActivite.getHeureFin() !=null);
    }
}
