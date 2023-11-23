import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe ValidationPsychologue.
 * Cette classe permet de faire la validation sur de nombreux objets pour les membre ed l'ordre des psychologues.
 * Elle permet de valider les données d'une
 * classe Membre et les données d'une Activité.
 *
 * @version 11-13-2021
 * @author Raphael Bouthillier
 * @version 0.1
 */


public class ValidationPsychologue {

    public static final String CYCLE_LIMITE_PSYCHOLOGUES = "2018-2023";
    public static final String DATE_MIN_CYCLE_PSYCHOLOGUES = "2018-01-01";
    public static final String DATE_MAX_CYCLE_PSYCHOLOGUES = "2023-01-01";
    public static final int HEURE_MINIMUM_ACTIVITE_COURS = 25;
    public static final int HEURE_MINIMUM_ACTIVITE_PROJET_RECHERCHE = 3;
    public static final int HEURE_MINIMUM_ACTIVITE_GROUPE_DISCUSSION = 1;
    public static final int HEURE_TOTAL_PSYCHOLOGUES = 90;
    public static final int HEURE_MAXIMUM_ACTIVITE_CONFERENCE = 15;
    public static final String ORDRE_PSYCHOLOGUES = "psychologues";
    public static final String HEURE_COURS_INVALIDE = " doit avoir un minimum de 25 heures par cycles.";
    public static final String HEURE_PROJET_RECHERCHE_INVALIDE = " doit avoir un minimum de 3 heures par cycle.";
    public static final String HEURE_GROUPE_DISCUSSION_INVALIDE = " doit avoir un minumum de 1 heures par cycle.";


    /**
     * Permet de valider l'intervalle de date de l'ordre des psychologues.
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean validerIntervalleDateOrdrePsychologue(Date date)throws ParseException {
        boolean resultat = false;
        Date dateMin = Validation.creerDate(DATE_MIN_CYCLE_PSYCHOLOGUES);
        Date dateMax =  Validation.creerDate(DATE_MAX_CYCLE_PSYCHOLOGUES);
        resultat = date.after(dateMin) && date.before(dateMax) || date.equals(dateMin)  || date.equals(dateMax);
        return resultat;
    }

    /**
     * Permet de valider si le cycle passe en parametre est le meme
     * que le cycle valide pour l'ordre des arcitectes.
     *
     * @param cycle
     * @return
     */
    public static boolean validerCycleOrdrePsychologue(String cycle){
        return cycle.equals(CYCLE_LIMITE_PSYCHOLOGUES);
    }


    /**
     * Permet de valider les heure d'une activite cours
     * pour l'ordre des architectes.
     *
     * @param nombreHeure
     * @return
     */
    public static boolean validerHeureActiviteCours(int nombreHeure){
        return nombreHeure >= HEURE_MINIMUM_ACTIVITE_COURS;
    }

    /**
     * Permet de valider si un Membre passé en paramètre est de
     * l'ordre des géologue.
     * @param ordre
     * @return
     */
    public static boolean validerOrdrePsychologue(String ordre){return ordre.equals(ORDRE_PSYCHOLOGUES);}

    /**
     * Permet de valider les heure minimum d'une activite Projet de recherche.
     *
     * @param nombre
     * @return
     */
    public static boolean validerHeureActiviteProjetRecherche(int nombre){return nombre >= HEURE_MINIMUM_ACTIVITE_PROJET_RECHERCHE;}

    /**
     * Permet de valider les heures minimum d'une activite groupe de discussion.
     *
     * @param nombre
     * @return
     */
    public static boolean validerHeureActiviteGroupeDiscussion(int nombre){return nombre >= HEURE_MINIMUM_ACTIVITE_GROUPE_DISCUSSION;}

    /**
     * Permet d'ajouter un erreur si le nombre d'heure de l'activite projet de recherche n'est pas respecter.
     *
     * @param membre
     * @param activite
     */
    public static void ajouterErreurHeureNonValideProjetRecherche(Membre membre,Activite activite){
        if(activite.getCategorie().equals("projet de recherche") && !(ValidationPsychologue.validerHeureActiviteProjetRecherche(activite.getHeures()))){
            membre.ajouterErreur("L'activite "+activite.getCategorie()+HEURE_PROJET_RECHERCHE_INVALIDE);
        }
    }

    /**
     * Permet d'ajouter un erreur si le nombre d'heures de l'activite groupe de discussion n'est pas respecter.
     *
     * @param membre
     * @param activite
     */
    public static void ajouterErreurHeureNonValideGroupeDiscussion(Membre membre,Activite activite){
        if(activite.getCategorie().equals("groupe de discussion") && !(validerHeureActiviteGroupeDiscussion(activite.getHeures()))){
            System.out.println();
            membre.ajouterErreur("L'activite "+activite.getCategorie()+HEURE_GROUPE_DISCUSSION_INVALIDE);
        }
    }

    /**
     * Permet de valider la list des activité d'un architectes.
     *
     * @param m
     * @param l
     */
    public static void validerListPsychologues(Membre m,Activite l,String fichierSortie){
        if(!Validation.validerActivite(l.getCategorie())){
            m.ajouterErreur("L'activité "+l.getDescription()+ FormationContinue.CATEGORIE_NON_RECONNU);
        }if(!Validation.validerHeureActiviteMinimum(l.getHeures())){
            m.ajouterErreur("L'activite "+
                    l.getCategorie()+FormationContinue.HEURE_ACTIVITE+" "+FormationContinue.HEURE_ACTIVITE_MINIMUM);
        }if(!Validation.validerDate(l.getDate(),m.getOrdre(),m.getCycle())){
            m.ajouterErreur("La date de l'activité "+l.getDescription()+" est hors de l'intervalle");
        }if(l.getCategorie().equals("cours") && !(validerHeureActiviteCours(l.getHeures()))){
            m.ajouterErreur("L'activite "+l.getCategorie()+HEURE_COURS_INVALIDE);
        }if(!Validation.validerMaxParJour(l)){
            m.ajouterErreur("L'activité "+l.getDescription()+" dépasse le nombre maximum d'heures par jour" +
                    "pour une activité.");
        }
        Validation.validerDescription(l,fichierSortie);
        ajouterErreurActiviteHeureNonValide(m,l);
    }

    /**
     * Permet d'ajouter les erreurs des activite donc les heures minimus ne sont pas respecter.
     *
     * @param membre
     * @param activite
     */
    public static void ajouterErreurActiviteHeureNonValide(Membre membre,Activite activite){
        ajouterErreurHeureNonValideProjetRecherche(membre,activite);
        ajouterErreurHeureNonValideGroupeDiscussion(membre,activite);
        ajouterErreurHeureNonValideGroupeDiscussion(membre,activite);
    }

    public static boolean validerHeureTotalPsychologue(int Total){
        return Total>=HEURE_TOTAL_PSYCHOLOGUES ;
    }

    public static void validerPermisPsychologues(Membre membre){
        String regex = "[0-9]{5}[-][0-9]{2}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(membre.getPermis());
        if(!matcher.matches()){
            membre.ajouterErreur("Le permis est invalide, il doit contenir 5 chiffres suivit d'un trait ");
        }
    }
}
