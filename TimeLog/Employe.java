import java.util.Date;

public class Employe {

    private int idEmploye;
    private String nomEmploye;
    private String NAS; 
    private String poste;
    private Date embauche;
    private Date depart;
    private double tauxBase;
    private double tauxSupp;

    public Employe(int idE, String nomE, String NAS, String poste, Date embauche, Date depart, double tauxBase, double tauxSupp){
        this.idEmploye = idE;
        this.nomEmploye = nomE;
        this.NAS = NAS;
        this.poste = poste;
        this.embauche = embauche;
        this.depart = depart;
        this.tauxBase = tauxBase;
        this.tauxSupp = tauxSupp;
    }

    public int getIdEmploye(){
        return idEmploye;
    }
    public String getNomEmploye(){
        return nomEmploye;
    }
    public String getPosteEmploye(){
        return poste;
    }
    public String getNAS(){
        return NAS;
    }
    public Date getEmbauche(){
        return embauche;
    }
    public Date getDepart(){
        return depart;
    }
    public double getTauxBase(){
        return tauxBase;
    }
    public double getTauxSupp(){
        return tauxSupp;
    }
    public void setIdEmploye(int idE){
        this.idEmploye = idE;
    }
}