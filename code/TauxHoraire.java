import java.util.ArrayList;

public class TauxHoraire {
    private ArrayList<Float> tauxBase;
    private ArrayList<Float> tauxSupplementaire;

    public TauxHoraire(ArrayList<Float> tauxBase, ArrayList<Float> tauxSupplementaire) {
        this.tauxBase = tauxBase;
        this.tauxSupplementaire = tauxSupplementaire;
    }

    public float obtenirTauxBaseCourant(){
        return tauxBase.getLast();
    }

    public float obtenirTauxSupplementaireCourant(){
        return tauxSupplementaire.getLast();
    }
}
