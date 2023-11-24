import java.util.ArrayList;
import java.util.Date;

public class Projet {
    private int idProjet;
    private String nomProjet;
    private Date dateDebut;
    private Date dateFin;
    private Float nbrHeuresBudgetDiscipline;

    ArrayList<Activite> listeActivites;
    ArrayList<Employe> listeEmployes;


    public Projet(int idProjet, String nomProjet, Date dateDebut, Date dateFin, Float nbrHeuresBudgetDiscipline,
            ArrayList<Activite> listeActivites, ArrayList<Employe> listeEmployes) {
        this.idProjet = idProjet;
        this.nomProjet = nomProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbrHeuresBudgetDiscipline = nbrHeuresBudgetDiscipline;
        this.listeActivites = listeActivites;
        this.listeEmployes = listeEmployes;
    }

    public int ajouterEmployer(Employe employe){
        if(listeEmployes.contains(employe)){
           return -1; 
        }
        listeEmployes.add(employe);
        return  listeEmployes.size();
    }

    public int ajouterActivite(Activite activite){
        if(listeActivites.contains(activite)){
           return -1; 
        }
        listeActivites.add(activite);
        return  listeActivites.size();
    }
    
}
