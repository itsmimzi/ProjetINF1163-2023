import java.util.Date;

public class PayInfo  {
   
	private Employe employe ;
	private double  nbrHeureBase;
	private double   nbrHeureSupp;
	private Date date;
   

    // Constructeurs
    public PayInfo(Employe employe, double nbrHeureBase, double nbrHeureSupp, Date date) {
        this.employe = employe;
        this.nbrHeureBase = nbrHeureBase;
        this.nbrHeureSupp = nbrHeureSupp;
        this.date = date;
    }

    // Getters
    public Employe employe() {
        return employe;
    }

    public double getNbrHeureBase() {
        return nbrHeureBase;
    }

    public double getNbrHeureSupp() {
        return nbrHeureSupp;
    }

 

    public Date getDate() {
        return date;
    }

    // Setters
    public void setemploye(Employe  employe) {
        this.Employe =employe;
    }

    public void setNbrHeureBase(double nbrHeureBase) {
        this.nbrHeureBase = nbrHeureBase;
    }

    public void setNbrHeureSupp(double nbrHeureSupp) {
        this.nbrHeureSupp = nbrHeureSupp;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    // Methode utile pour calculer le salaire brut
    public double calculerSalaireBrut() {
    	double salaireBrut = (nbrHeureBase * tauxBase) + (nbrHeureSupp * tauxSupp);	  
    }
}