import java.util.ArrayList;

/**
 * La classe TauxHoraire représente les taux horaires de base et supplémentaires
 * pour une certaine catégorie d'employés.
 */
public class TauxHoraire {
    private ArrayList<Float> tauxBase; // Liste des taux horaires de base.
    private ArrayList<Float> tauxSupplementaire; // Liste des taux horaires supplémentaires.

    /**
     * Construit un nouvel objet TauxHoraire avec les taux horaires de base et supplémentaires spécifiés.
     *
     * @param tauxBase          La liste des taux horaires de base.
     * @param tauxSupplementaire La liste des taux horaires supplémentaires.
     */
    public TauxHoraire(ArrayList<Float> tauxBase, ArrayList<Float> tauxSupplementaire) {
        this.tauxBase = tauxBase;
        this.tauxSupplementaire = tauxSupplementaire;
    }

    /**
     * Modifie le taux horaire de base courant en ajoutant un nouveau taux.
     *
     * @param taux Le nouveau taux horaire de base à ajouter.
     */
    public void modifierTauxBaseCourant(float taux) {
        tauxBase.add(taux);
    }

    /**
     * Obtient le taux horaire de base courant, c'est-à-dire le dernier taux horaire de base ajouté.
     *
     * @return Le taux horaire de base courant.
     */
    public float obtenirTauxBaseCourant() {
        return tauxBase.getLast();
    }

    /**
     * Obtient le taux horaire supplémentaire courant, c'est-à-dire le dernier taux horaire supplémentaire ajouté.
     *
     * @return Le taux horaire supplémentaire courant.
     */
    public float obtenirTauxSupplementaireCourant() {
        return tauxSupplementaire.getLast();
    }
}
