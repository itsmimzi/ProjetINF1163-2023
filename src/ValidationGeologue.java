import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationGeologue {

    public static final String CYCLE_LIMITE_GEOLOGUE = "2018-2021";
    public static final String DATE_MIN_CYCLE_GEOLOGUE = "2018-06-01";
    public static final String DATE_MAX_CYCLE_GEOLOGUE = "2021-06-01";
    public static final int HEURE_MINIMUM_ACTIVITE_COURS = 22;
    public static final int HEURE_MINIMUM_ACTIVITE_PROJET_RECHERCHE = 3;
    public static final int HEURE_MINIMUM_ACTIVITE_GROUPE_DISCUSSION = 1;
    public static final int HEURE_TOTAL_GEOLOGUES = 55;
    public static final String ORDRE_GEOLOGUES = "géologues";
    public static final String HEURE_COURS_INVALIDE = " doit avoir un minimum de 22 heures par cycles.";
    public static final String HEURE_PROJET_RECHERCHE_INVALIDE = " doit avoir un minimum de 3 heures par cycle.";
    public static final String HEURE_GROUPE_DISCUSSION_INVALIDE = " doit avoir un minumum de 1 heures par cycle.";


    /**
     * Permet de valider l'intervalle de date de l'ordre des architectes.
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static boolean validerIntervalleDateOrdreGeologue(Date date)throws ParseException {
        boolean resultat = false;
        Date dateMin = Validation.creerDate(DATE_MIN_CYCLE_GEOLOGUE);
        Date dateMax =  Validation.creerDate(DATE_MAX_CYCLE_GEOLOGUE);
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
    public static boolean validerCycleOrdreArchitectes(String cycle){
        return cycle.equals(CYCLE_LIMITE_GEOLOGUE);
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
    public static boolean validerOrdreGeologue(String ordre){return ordre.equals(ORDRE_GEOLOGUES);}

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
        if(activite.getCategorie().equals("projet de recherche") && !(ValidationGeologue.validerHeureActiviteProjetRecherche(activite.getHeures()))){
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
    public static void validerListGeologues(Membre m,Activite l,String fichierSortie){
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

    public static void validerPermisGéologues(Membre membre){
        char premiereLettre = Character.toUpperCase(membre.getNom().charAt(0));
        char deuxiemeLettre = Character.toUpperCase(membre.getPrenom().charAt(0));
        String regex = "["+(String.valueOf(premiereLettre)+String.valueOf(deuxiemeLettre))+"]"+"\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(membre.getPermis());
        if(!matcher.matches()){
            membre.ajouterErreur("Le permis est invalide, il doit contenir la première lettre du nom du membre" +
                    "et la deuxième lettre doit etre la premiere lettre du prenom du membre");
        }
    }
}
