import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Test pour la fonctionnalité #10
    /* 
     * 10. L’administrateur du système se connecte avec le nom d’usager admin et un mot de
     * passe admin qu’il peut changer plus tard.
     * 
     * 
     * Une simulation des fonction sera fait afin de tester le code puisque le tout se fait a 
    * l'aide de l'entree a la console. Chaque fonction principal utiliser lors de cette 
    * operation seront tester.
    */
public class test_10 {
    TimeLog timeLog = new TimeLog();
    
    /**
     * Teste la capacité de vérifier le mot de passe de l'administrateur lors de la connexion.
     * 
     * <p>Ce test vérifie si le système peut correctement authentifier l'administrateur avec son nom d'usager 
     * et son mot de passe. Il utilise le compte administrateur par défaut et vérifie si la méthode 
     * {@code administrateurVerificationMotDePasse} retourne {@code true} pour les informations de connexion 
     * de l'administrateur. Le test est réussi si la méthode renvoie {@code true}, indiquant une authentification 
     * réussie avec les identifiants par défaut de l'administrateur.</p>
     */
    @Test
    public void testerConnectionAdministrateur(){
        Administrateur administrateur = timeLog.administrateur;
        assertTrue(timeLog.administrateurVerificationMotDePasse(administrateur.getMotDePasse(), administrateur.getNomEmploye()));
    }
}
