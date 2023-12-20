
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test pour la fonctionnalité #13
    /* 
     * 13. Un employé se connecte au système en fournissant son nom d’usager et son ID.
     * 
     * Une simulation des fonction sera fait afin de tester le code puisque le tout se fait a 
    * l'aide de l'entree a la console. Chaque fonction principal utiliser lors de cette 
    * operation seront tester.
    */
public class test_13 {
    TimeLog timeLog = new TimeLog();
    

    /**
    * Teste la méthode {@code employeExistant} de la classe {@code TimeLog}.
    * 
    * <p>Ce test vérifie si la méthode {@code employeExistant} peut correctement identifier
    * un employé existant dans le système. Il ajoute d'abord un nouvel employé à la liste
    * des employés du système et utilise ensuite cette méthode pour vérifier si l'employé
    * avec l'identifiant et le nom donnés existe.</p>
    *
    * <p>Le test est considéré comme réussi si la méthode {@code employeExistant} retourne
    * {@code true}, indiquant que l'employé ajouté est reconnu comme existant dans le système.</p>
    */
    @Test
    public void testConnectionEmployer(){
        Employe employe = new Employe("employe1", 111222333, "design1", new Date(), null, 100.0, 0.0);
        timeLog.ajouterEmployer(employe);
        assertTrue(timeLog.employeExistant(employe.getIdEmploye(), employe.getNomEmploye()));
    }
}
