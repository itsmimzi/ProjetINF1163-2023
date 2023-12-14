/**
* cette classe PayInfo est maintenant équipée de constructeurs, getters, setters, 
** et d'une méthode calculerSalaireBrut pour effectuer un calcul simple du salaire brut 
** en fonction des heures de base et supplémentaires avec les taux horaires correspondants. 
**/

import java.util.Date;

public class PayInfo {
    private int idEmploye;
    private float nbrHeureBase;
    private float nbrHeureSupp;
    private float tauxBase;
    private float tauxSupplementaire;
    private Date date;

    // Constructeurs par défaut
      public PayInfo() {
        // Initialiser les attributs avec des valeurs par défaut ou null si nécessaire
        this.idEmploye = 0; // Vous pouvez définir une autre valeur par défaut
        this.nbrHeureBase = 0;
        this.nbrHeureSupp = 0;
        this.tauxBase = 0;
        this.tauxSupplementaire = 0;
        this.date = null; // Ou initialisez avec une date par défaut si nécessaire
    }

// Constructeurs
    public PayInfo(int idEmploye, float nbrHeureBase, float nbrHeureSupp, float tauxBase,
                   float tauxSupplementaire, Date date) {
        this.idEmploye = idEmploye;
        this.nbrHeureBase = nbrHeureBase;
        this.nbrHeureSupp = nbrHeureSupp;
        this.tauxBase = tauxBase;
        this.tauxSupplementaire = tauxSupplementaire;
        this.date = date;
    }

    // Getters
    public int getIdEmploye() {
        return idEmploye;
    }

    public float getNbrHeureBase() {
        return nbrHeureBase;
    }

    public float getNbrHeureSupp() {
        return nbrHeureSupp;
    }

    public float getTauxBase() {
        return tauxBase;
    }

    public float getTauxSupplementaire() {
        return tauxSupplementaire;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public void setNbrHeureBase(float nbrHeureBase) {
        this.nbrHeureBase = nbrHeureBase;
    }

    public void setNbrHeureSupp(float nbrHeureSupp) {
        this.nbrHeureSupp = nbrHeureSupp;
    }

    public void setTauxBase(float tauxBase) {
        this.tauxBase = tauxBase;
    }

    public void setTauxSupplementaire(float tauxSupplementaire) {
        this.tauxSupplementaire = tauxSupplementaire;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Méthode utile pour calculer le salaire brut
    public float calculerSalaireBrut() {
        return (nbrHeureBase * tauxBase) + (nbrHeureSupp * tauxSupplementaire);
    }

    
}
