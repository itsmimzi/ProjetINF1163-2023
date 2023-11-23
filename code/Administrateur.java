import java.util.ArrayList;
import java.util.Date;

public class Administrateur extends Employe{
    private String motPasse;
    private String nomUtilisateur;

    public Administrateur(int idEmploye, String prenomEmploye, String nomEmploye, int nas, Date dateEmbauche,
            Date dateDepart, Poste poste, TauxHoraire tauxHoraire, ArrayList<Activite> activites,
            ArrayList<Projet> projets) {
        super(idEmploye, prenomEmploye, nomEmploye, nas, dateEmbauche, dateDepart, poste, tauxHoraire, activites, projets);
    }
    
}
