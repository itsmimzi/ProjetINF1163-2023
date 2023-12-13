import java.util.Objects;

/**
 * La classe Discipline représente une discipline associée à un projet.
 */
public class Discipline {
    private String nom;

    public Discipline(String nom) {
        this.nom = nom;
    }

    // Getter et setter pour le nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Méthode equals pour la comparaison d'objets
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(nom, that.nom);
    }

    // Méthode hashCode pour la comparaison d'objets
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "Discipline{" +
                "nom='" + nom + '\'' +
                '}';
    }
}
