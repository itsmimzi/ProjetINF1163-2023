import java.util.ArrayList;
import java.util.Date;

public class Administrateur extends Employe{
    private String motPasse = "admin";
    
    public Administrateur(String nomEmploye, int NAS, String poste, Date embauche, Date depart, double tauxBase,
            double tauxSupp) {
        super(nomEmploye, NAS, poste, embauche, depart, tauxBase, tauxSupp);
    }
    

    public String getMotDePasse(){
        return motPasse;
    }
    
    public void setMotPasse(String motPasse){
        this.motPasse = motPasse;
    }

    
}
