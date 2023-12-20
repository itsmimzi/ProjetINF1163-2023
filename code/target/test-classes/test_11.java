
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test pour la fonctionnalité #11
    /* 
     * 11. Le compte admin peut modifier les noms d’usager et le ID de tout employé ainsi que les siens.
     * 
     * 
     * Une simulation des fonction sera fait afin de tester le code puisque le tout se fait a 
    * l'aide de l'entree a la console. Chaque fonction principal utiliser lors de cette 
    * operation seront tester.
    */
public class test_11 {
    TimeLog timeLog = new TimeLog();
    
    /**
     * Teste la capacité de modifier le nom d'un employé.
     * 
     * <p>Ce test vérifie si le système permet la modification correcte du nom d'un employé.
     * Il crée d'abord un employé avec un nom initial, ajoute cet employé au système, puis change 
     * son nom. Le test est réussi si le nom de l'employé après la modification correspond 
     * au nouveau nom fourni.</p>
     */
    @Test
    public void testerModificationNomEmploye(){
        Employe employe = new Employe("employe1", 111222333, "design1", new Date(), null, 100.0, 0.0);
        timeLog.ajouterEmployer(employe);
        String nouveauNom = "employeTest";
        employe.setNom(nouveauNom);
        assertTrue(employe.getNomEmploye().equals(nouveauNom));

    }
}
