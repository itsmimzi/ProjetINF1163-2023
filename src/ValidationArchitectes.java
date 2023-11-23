import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationArchitectes extends Validation{

    /**
     * Permet de valider si une date d'un activite du cycle des architectes
     * est dans l'intervalle de ce cycle.
     *
     * @param date
     * @param cycle
     * @return
     * @throws ParseException
     */
    public static boolean validerIntervalleDateOrdreArchitectes(Date date, String cycle)throws ParseException{
        boolean resultat = false;
        Date dateMinUn = creerDate(DATE_MINIMUM_ARCHITECTES_UN);
        Date dateMaxUn =  creerDate(DATE_MAXIMUM_ARCHITECTES_UN);
        Date dateMinDeux = creerDate(DATE_MINIMUM_ARCHITECTES_DEUX);
        Date dateMaxDeux =  creerDate(DATE_MAXIMUM_ARCHITECTES_DEUX);
        if(validerCycleArchitectesUN(cycle)){
            resultat = date.after(dateMinUn) && date.before(dateMaxUn) || date.equals(dateMinUn)  || date.equals(dateMaxUn);
        }else if(validerCycleArchitectesDEUX(cycle)){
            resultat = date.after(dateMinDeux) && date.before(dateMaxDeux)||date.equals(dateMinDeux)||date.equals(dateMaxDeux);
        }
        return resultat;
    }

    /**
     * Permet de valider le cycle de formation d'un Architectes.
     * Renvoie vrais si le cycle est valide, faux si le contraire.
     * @param cycle
     * @return
     */
    public static boolean validerCycleArchitectes(String cycle){
        return validerCycleArchitectesDEUX(CYCLE_LIMITE_ACHITECTES_DEUX) || validerCycleArchitectesUN(CYCLE_LIMITE_ARCHITECTES_UN);
    }


    /**
     * Permet de valider le cycle de formation d'un Architectes
     * entre 2018-2020.
     * Renvoie vrais si le cycle est valide, faux si le contraire.
     * @param cycle
     * @return
     */
    public static boolean validerCycleArchitectesUN(String cycle){return cycle.equals(CYCLE_LIMITE_ARCHITECTES_UN);}

    /**
     * Permet de valider le cycle de formation d'un Architectes
     * entre 2016-2018.
     * Renvoie vrais si le cycle est valide, faux si le contraire.
     * @param cycle
     * @return
     */
    public static boolean validerCycleArchitectesDEUX(String cycle){return cycle.equals(CYCLE_LIMITE_ACHITECTES_DEUX);}

    /**
     * Permet de valider si un Membre passé en paramètre est de
     * l'ordre des architectes.
     * @param  ordre
     * @return
     */
    public static boolean validerOrdreArchitectes(String ordre){
        return ordre.equals(ORDRE_ARCHITECTES);
    }


    /**
     * Permet de valider le permis d'membre Architectes.
     *
     * @param membre
     */
    public static void validerPermisArchitectes(Membre membre){
        Pattern pattern = Pattern.compile("[AT]{1}\\d{4}");
        System.out.println(membre.getPermis());
        Matcher matcher = pattern.matcher(membre.getPermis());
        if(!matcher.matches()){
            membre.ajouterErreur("Le permis est invalide, il doit contenir A ou T suivi de 4 chiffres.");
        }
    }

}
