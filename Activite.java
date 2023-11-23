import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Activite {

    private String description;
    private String categorie;
    private int heures;
    private String date;

    public String getDate() {
        return date;
    }

    public int getHeures() {
        return heures;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setHeures(int heures){ this.heures = heures;}

    public Activite(String arg1, String arg2, int arg3, String arg4){

        description = arg1;
        categorie = arg2;
        heures = arg3;
        date = arg4;

    }

    /**
     * Permet de creer un liste d'activite valide pour un ordre non specifique.
     *
     * @param m
     * @return
     */
    public static ArrayList<Activite> creerListActiviteValideOrdre(Membre m){
        ArrayList<Activite> listActiviteTmp = m.getActivitees();
        listActiviteTmp.stream()
                .filter(list -> Validation.validerActivite(list.getCategorie())
                        && Validation.validerHeureActiviteMinimum(list.getHeures())
                        && (Validation.validerDate(list.getDate(),m.getOrdre(),m.getCycle())))
                .collect(Collectors.toCollection(ArrayList::new));
        return listActiviteTmp;
    }


    /**
     * Permet de creer un liste d'activite valide pour l'ordre des Architectes.
     *
     * @param m
     * @return
     */
    public static ArrayList<Activite> creerListActiviteValideOrdreGeologue(Membre m){
        ArrayList<Activite> listActiviteTmp = m.getActivitees().stream()
                .filter(list -> Validation.validerActivite(list.getCategorie())
                        && Validation.validerHeureActiviteMinimum(list.getHeures())
                        && (Validation.validerDate(list.getDate(),m.getOrdre(),m.getCycle())))
                .collect(Collectors.toCollection(ArrayList::new));
        ajouterErreurActiviteProjetRechercheNonPresentGeologue(m);
        ajouterErreurActiviteCoursNonPresentGeologue(m);
        ajouterErreurActiviteGroupeDiscussionNonPresentGeologue(m);
        return listActiviteTmp;
    }

    public static void ajouterErreurActiviteGroupeDiscussionNonPresentGeologue(Membre membre){
        boolean contientProjetRecherche = membre.getActivitees()
                .stream()
                .anyMatch(ligne-> ligne.getCategorie()
                        .equals(Validation.ACTIVITE_PROJET_RECHERCHE));
        if(!contientProjetRecherche){
            membre.ajouterErreur("Le cycle doit avoir une activite groupe de discussion. Cette activite"
                    +ValidationGeologue.HEURE_GROUPE_DISCUSSION_INVALIDE);
        }
    }

    /**
     * Permet d'ajout un erreur a un membre donc, l'activite
     * projet de recherche n'est pas present dans la liste des activitees.
     * @param membre
     */
    public static void ajouterErreurActiviteProjetRechercheNonPresentGeologue(Membre membre){
        boolean contientProjetRecherche = membre.getActivitees()
                .stream()
                .anyMatch(ligne-> ligne.getCategorie()
                        .equals(Validation.ACTIVITE_PROJET_RECHERCHE));
        if(!contientProjetRecherche){
            membre.ajouterErreur("Le cycle doit avoir une activite projet de recherche. Cette activite"
                    +ValidationGeologue.HEURE_PROJET_RECHERCHE_INVALIDE);
        }
    }

    /**
     * Permet d'ajout un erreur a un membre donc, l'activite cours n'est pas present dans la liste des activitees.
     *
     * @param membre
     */
    public static void ajouterErreurActiviteCoursNonPresentGeologue(Membre membre){
        boolean contientProjetRecherche = membre.getActivitees()
                                                .stream()
                                                .anyMatch(ligne-> ligne.getCategorie()
                                                .equals(Validation.ACTIVITE_COURS));
        if(!contientProjetRecherche){
            membre.ajouterErreur("Le cycle doit avoir une activite cours. Cette activite"
                    +ValidationGeologue.HEURE_COURS_INVALIDE);
        }
    }

}
