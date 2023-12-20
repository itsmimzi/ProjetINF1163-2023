import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test pour la fonctionnalité #10
    /* 
     * 9. Avec le compte admin, on peut assigner des employés aux projets. Un employé ne peut
     * travaillez que pour les projets auxquels il est assigné. Une contrainte à implémenter est
     * de ne pas assigner un employé à plus d’un nombre NPE de projets. NPE est par défaut
     * égale à 2, mais l’admin peut modifier la valeur de NPE.
     * 
     * 
     * Une simulation des fonction sera fait afin de tester le code puisque le tout se fait a 
     * l'aide de l'entree a la console. Chaque fonction principal utiliser lors de cette 
     * operation seront tester.
     */
public class test_9 {
    TimeLog timeLog = new TimeLog();
    
    /**
     * Teste la capacité d'assigner un employé à un projet.
     * 
     * <p>Ce test vérifie si un employé peut être correctement assigné à un projet. Un employé et un projet
     * sont créés et l'employé est ajouté au projet. Le test réussit si l'employé apparaît dans la liste
     * des employés du projet, validant ainsi l'assignation correcte.</p>
     */
    @Test 
    public void testerAsignationProjet(){
        Employe employe = new Employe("employe1", 111222333, "DESIGN1", new Date(), null, 100.0, 0.0);
        Projet projet = new Projet("projet1", new Date(), null);
        timeLog.ajouterEmployer(employe);
        
        Activite activite = new Activite(employe, 10);
        projet.ajouterActivite(activite);

        boolean val=false;
        for(int i=0;i<projet.getActivitesDansProjet().size();i++){
            if(projet.getActivitesDansProjet().get(i).getEmploye() == employe){
                val = true;
            }
        }
        assertTrue(val);
    }

    /**
     * Teste la capacité de modifier la valeur NPE d'un employé.
     * 
     * <p>Ce test vérifie si la valeur NPE (Nombre de Projets en cours) d'un employé peut être correctement modifiée.
     * Après la création d'un employé, la valeur NPE est modifiée. Le test est réussi si la nouvelle valeur NPE
     * de l'employé correspond à la valeur définie dans le test.</p>
     */
    @Test
    public void testerModificationNPE(){
        Employe employe = new Employe("employe1", 111222333, "DESIGN1", new Date(), null, 100.0, 0.0);
        Projet projet = new Projet("projet1", new Date(), null);
        timeLog.ajouterEmployer(employe);
        
        
        employe.setNPE(2);
        assertTrue(employe.getNPE() == 2);
    }
}
