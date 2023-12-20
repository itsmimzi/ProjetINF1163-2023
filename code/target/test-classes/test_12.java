
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test pour la fonctionnalité #12
/* 
    * 12. Le compte admin, permet de modifier la liste des projets et leurs caractéristiques, la
    * liste des employés, leurs caractéristiques et leurs assignations aux différents projets.
    * 
    * Une simulation des fonction sera fait afin de tester le code puisque le tout se fait a 
    * l'aide de l'entree a la console. Chaque fonction principal utiliser lors de cette 
    * operation seront tester.
    */
public class test_12 {
    TimeLog timeLog = new TimeLog();

    /**
     * Teste la capacité de changer le nom d'un projet.
     * 
     * <p>Ce test crée un employé et un projet, ajoute l'employé au système, puis change le nom du projet.
     * Le test réussit si le nom du projet après modification correspond au nouveau nom fourni.</p>
     */
    @Test
    public void testerChangementNomProjet(){
        Employe employe = new Employe("employe1", 111222333, "design1", new Date(), null, 100.0, 0.0);
        Projet projet = new Projet("projet1", new Date(), null);
        timeLog.ajouterEmployer(employe);
        String nom="projetTest";
        projet.setNomProjet(nom);
        assertTrue(projet.getNomProjet().equals(nom));
    }

    /**
     * Teste la capacité de changer le nom d'un employé.
     * 
     * <p>Ce test crée un employé et un projet, ajoute l'employé au système, puis change le nom de l'employé.
     * Le test réussit si le nom de l'employé après modification correspond au nouveau nom fourni.</p>
     */
    @Test
    public void testerChangementNomEmploye(){
        Employe employe = new Employe("employe1", 111222333, "DESIGN1", new Date(), null, 100.0, 0.0);
        Projet projet = new Projet("projet1", new Date(), null);
        timeLog.ajouterEmployer(employe);
        String nom="employeTest";
        employe.setNom(nom);
        assertTrue(employe.getNomEmploye().equals(nom));
    }

    /**
     * Teste la capacité d'assigner un employé à un projet.
     * 
     * <p>Ce test crée un employé et un projet, ajoute l'employé au système, puis assigne l'employé au projet.
     * Le test réussit si l'employé est correctement ajouté à la liste des employés du projet.</p>
     */
    @Test
    public void testerAssignationProjet(){
        Employe employe = new Employe("employe1", 111222333, "DESIGN1", new Date(), null, 100.0, 0.0);
        Projet projet = new Projet("projet1", new Date(), null);
        timeLog.ajouterEmployer(employe);
        Activite activite = new Activite(employe, 10);
        projet.ajouterActivite(activite);
        boolean val=false;
        for(int i=0; i< projet.getActivitesDansProjet().size();i++){
            if(projet.getActivitesDansProjet().get(i).getEmploye() == employe){
                val = true;
            }
        }

        assertTrue(val);
    }
}
