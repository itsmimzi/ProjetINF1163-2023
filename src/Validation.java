import net.sf.json.JSONObject;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe Validation.
 * Cette classe permet de faire la validation sur de nombreux objets. Elle permet de valider les données d'une
 * classe Membre et les donnée d'une Activité.
 *
 * @version 10-08-2021
 * @author Ken Lessard-Gerber
 * @version 1.1
 */
public class Validation {

    // Constante de classe permettant la comparaison de données.
    public static final String ACTIVITE_COURS = "cours";
    public static final String ACTIVITE_ATELIER = "atelier";
    public static final String ACTIVITE_SEMINAIRE = "séminaire";
    public static final String ACTIVITE_COLLOQUE = "colloque";
    public static final String ACTIVITE_CONFERENCE = "conférence";
    public static final String ACTIVITE_LECTURE_DIRIGÉE = "lecture dirigée";
    public static final String ACTIVITE_PRÉSENTATION = "présentation";
    public static final String ACTIVITE_GROUPE_DISCUSSION = "groupe de discussion";
    public static final String ACTIVITE_PROJET_RECHERCHE = "projet de recherche";
    public static final String ACTIVITE_REDACTION_PROFESSIONNELLE = "rédaction professionnelle";
    public static final String CYCLE_LIMITE = "2020-2022";
    public static final String DATE_MINIMUM = "2020-04-01";
    public static final String DATE_MAXIMUM = "2022-04-01";
    public static final String CYCLE_LIMITE_ARCHITECTES_UN = "2018-2020";
    public static final String DATE_MINIMUM_ARCHITECTES_UN = "2018-04-01";
    public static final String DATE_MAXIMUM_ARCHITECTES_UN = "2020-04-01";
    public static final String CYCLE_LIMITE_ACHITECTES_DEUX = "2016-2018";
    public static final String DATE_MINIMUM_ARCHITECTES_DEUX = "2016-04-01";
    public static final String DATE_MAXIMUM_ARCHITECTES_DEUX = "2018-07-01";
    public static final int HEURE_ACTIVITE_MIN = 1;
    public static final int HEURE_MIN =0;
    public static final int HEURE_MAX =7;
    public static final int HEURE_TOTAL = 40;
    public static final int HEURE_TOTAL_ARCHITECTES = 42;
    public static final int HEURE_CATEGORIE_MULTIPLE_MAX = 17;
    public static final String ORDRE_ARCHITECTES = "architectes";
    public static final String ORDRE_GEOLOGUES = "géologues";
    public static final String ORDRE_PSYCHOLOGUES = "psychologues";
    public static final String ORDRE_PODIATRES = "podiatres";
    public static final int MAX_HEURES_PAR_JOUR = 10;


    /**
     * Cette méthode prend en paramètre un nombre et va vérifier si le nombre est négatif.
     * S'il est n'égatif, la valeur de ce nombre sera de 0.
     * @param nombre à faire la vérification.
     * @return nombre vérifié retourné.
     */
    public static int validerHeureNegative(int nombre){
        if(nombre < 0){
            nombre =0;
        }
        return nombre;
    }

    /**
     * Cette méthode prend en paramètre un nombre et va vérifier si le nombre est plus grand ou égal à
     * la valeur minimum que le nombre d'heure qu'une activité peut avoir.
     * @param nombre à faire la vérification.
     * @return vrais si le nombre est plus grand ou égale à HEURE_ACTIVITE_MIN, faux si le contraire.
     */
    public static boolean validerHeureActiviteMinimum(int nombre){return nombre >= HEURE_ACTIVITE_MIN;}

    /**
     * Cette méthode prend en paramètre un nombre et va vérifier si le nombre est plus grand ou égal à
     * la valeur du nombre d'heure total du cycle d'un membre(HEURE_TOTAL).
     * @param nombreAVerifier à faire la vérification.
     * @return vrais si le nombre est plus grand ou égale à HEURE_TOTAL, faux si le contraire.
     */
    public static boolean validerHeureTOTAl(int nombreAVerifier){return nombreAVerifier >= HEURE_TOTAL;}

    /**
     * Cette méthode prend en paramètre un nombre et va vérifier si le nombre est plus grand ou égal à
     * la valeur du nombre d'heure total du cycle d'un membre(HEURE_TOTAL_ARCHITECTES) de l'ordre des architectes.
     * @param nombreAVerifier à faire la vérification.
     * @return vrais si le nombre est plus grand ou égale à HEURE_TOTAL, faux si le contraire.
     */
    public static boolean validerHeureTOTALArchitectes(int nombreAVerifier){ return nombreAVerifier >= HEURE_TOTAL_ARCHITECTES;}

    /**
     * Cette méthode prend en paramètre un cycle au format String et va vérifier si
     * le cycle est égale au cycle valide.
     * @param cycle à faire la vérification.
     * @return Elle retourne vrais si le cycle est valide, faux si le contraire.
     */
    public static boolean validerCycle(String cycle){return cycle.equals(CYCLE_LIMITE);}


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
     * Cette méthode prend en paramètre un Membre(m) et elle va y ajouter les
     * heure combinés dans la section heure combiné de se Membre. Elle va ajouter seulement les heures
     * des activités combiné au calcul. De plus, elle va renvoyer un boolean pour valider si les heure combiné
     * sont valide ou non.
     * @param m Membre m à faire la vérification.
     * @return Elle retourne vrais les heures combiné sont valides, faux si le contraire.
     */
    public static boolean validerActiviteCombine(Membre m){
        boolean resultat = false;
        int nombreHeureCombine = 0;
        ArrayList<Activite> listTMP = m.getActivitees();
        listTMP.removeIf((l)->l.getCategorie().equals(ACTIVITE_PRÉSENTATION)||l.getCategorie().equals(ACTIVITE_GROUPE_DISCUSSION)||
                    l.getCategorie().equals(ACTIVITE_PROJET_RECHERCHE)||l.getCategorie().equals(ACTIVITE_REDACTION_PROFESSIONNELLE));
        for(Activite ac:listTMP){
            nombreHeureCombine += ac.getHeures();
        }
        resultat = ajouterHeureCombine(nombreHeureCombine,m,resultat);
        return resultat;
    }

    /**
     * Cette méthode prend en paramètre un nombre, un Membre et un boolean. Elle faire la
     * vérification sur se nombre, l'ajout d'erreur sur se membre et le la valeur boolean
     * en consequence du resultat.
     * @param nombreHeureCombine à faire la vérification et l'ajout.
     * @param m Le Membre à ajouter les heures combinées.
     * @param resultat Retourne vrais si le nombre d'heure combinéés est plus grand ou
     *                 égale à son minimum(HEURE_CATEGORIE_MULTIPLE_MAX).
     * @return resultat vrais si le nombreHeureCombine est plus grand ou
     *         égale au nombre d'heure de la catégorie d'activité multiple.
     */
    public static boolean ajouterHeureCombine(int nombreHeureCombine, Membre m, boolean resultat){
        if(nombreHeureCombine>=HEURE_CATEGORIE_MULTIPLE_MAX){
            resultat = true;
        }
        m.ajouterHeuresCombinees(nombreHeureCombine);
        return resultat;
    }

    /**
     * Cette méthode prend en paramètre une date au format String et elle va
     * vérifier si cette date est belle et biens au format ISO 8601 (AAAA-MM-JJ)
     * et si elle est bien dans l'intervalle de date donnée.
     * @param dateVerifier à faire la vérification.
     * @return valeur boolean vrais si la date est valide, faux si le contraire.
     */
    public static boolean validerDate(String dateVerifier,String ordre,String cyle){
        Date date = null;
        boolean resultat = true;
        try{
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateVerifier);
            resultat = validerIntervalleDateOrdre(date,ordre,cyle);
        }catch (ParseException p){
           resultat = false;
        }
        return  resultat;
    }

    /**
     * Permet de valider l'intervalle de date selon l'ordre envoyer en parametre.
     *
     * @param date
     * @param ordre
     * @param cycle
     * @return
     * @throws ParseException
     */
    public static boolean validerIntervalleDateOrdre(Date date,String ordre,String cycle)throws ParseException{
        boolean valide = false;
        if(ValidationArchitectes.validerOrdreArchitectes(ordre)){
            valide = ValidationArchitectes.validerIntervalleDateOrdreArchitectes(date,cycle);
        }
        else if(ValidationGeologue.validerOrdreGeologue(ordre)){
              valide = ValidationGeologue.validerIntervalleDateOrdreGeologue(date);
        }
        return valide;
    }




    /**
     * Permet de creer une Date avec le parametre recu.
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date creerDate(String date) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }



    /**
     * Cette méthode prend en paramètre list de ArrayList<String> et elle y ajoute tout
     * les activités qui sont valide dans un cycle.
     * @param list la list à y ajouter les activités.
     * @return  la list avec les éléments ajouté.
     */
    public static ArrayList<String> remplirListeActivie(ArrayList<String> list){
        list.addAll(Arrays.asList(ACTIVITE_COURS,ACTIVITE_ATELIER,ACTIVITE_SEMINAIRE,ACTIVITE_COLLOQUE
                ,ACTIVITE_CONFERENCE,ACTIVITE_LECTURE_DIRIGÉE,ACTIVITE_PRÉSENTATION
                ,ACTIVITE_GROUPE_DISCUSSION,ACTIVITE_PROJET_RECHERCHE,ACTIVITE_REDACTION_PROFESSIONNELLE));
        return list;
    }

    /**
     * Cette méthode prend en paramètre une catégorie d'activité  au format de String et
     * elle va vérifier si elle fais partie des activité valide pour un cycle.
     * @param activie
     * @return Vrais si l'activité passé en paramètre est valide, faux si le contraire.
     */
    public static boolean validerActivite(String activie){
        boolean reultat = false;
        ArrayList<String> list = new ArrayList<>();
        remplirListeActivie(list);
        if(list.contains(activie)){
            reultat = true;
        }
        return reultat;
    }

    /**
     * Cette méthode prend en paramètre un membre et elle va vérifier si le nombre d'heure
     * des activité combiné est dans l'intervalle d'heure voulu.
     * @param membre à faire la vérification.
     * @return Vrais si le nombre d'heure est valide, faux si le contraire.
     */
    public static boolean validerHeureTransfere(Membre membre){
        boolean resultat = false;
        int nombreHeure = membre.getHeureTransferees();
        if (nombreHeure >= HEURE_MIN && nombreHeure <= HEURE_MAX){
            resultat = true;
        }else if(nombreHeure > HEURE_MAX){
            membre.setHeureTransferees(Validation.HEURE_MAX);
        }else {
            membre.setHeureTransferees(0);
        }
        return resultat;
    }


    /**
     * Permet de valider si un ordre est valide, donc est
     * Architectes, Geologues uo psychologues.
     * @param ordre
     * @return
     */
    public static boolean validerOrdreMembre(String ordre){
        return ordre.equals(ORDRE_ARCHITECTES) || ordre.equals(ORDRE_GEOLOGUES)
                || ordre.equals(ORDRE_PSYCHOLOGUES) || ordre.equals(ORDRE_PODIATRES);
    }

    public static boolean validerMaxParJour(Activite act){
        boolean res = true;
        if(act.getHeures()>MAX_HEURES_PAR_JOUR){
            act.setHeures(MAX_HEURES_PAR_JOUR);
            res = false;
        }
        return res;
    }



    public static  void validerDescription(Activite act, String fichierSortie) {
        boolean res = true;
        if (act.getDescription().length() <= 20) {
            System.err.println("Fichier d'entré invalide, sortie du programme.");
            try {
                FileWriter fileWriter = new FileWriter(fichierSortie);
                fileWriter.write("{\n   \"erreur\": \"Le fichier d'entré est invalide, le cycle de validation est" +
                        "incomplet!\" \n}");
                fileWriter.close();
             } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }


    /**
     * Permet de trouver si un champs n'est pas existant dans le fichier
     * Json fournit.
     *
     * @param objetJson fichier Json (JSONObject)
     * @return True ou False. Selon la validation.
     */
    public static boolean trouverChampsInValideObjetJson(JSONObject objetJson){
        boolean valRetour = true;
        if(!objetJson.containsKey("ordre") ||
                !objetJson.containsKey("numero_de_permis")||
                !objetJson.containsKey("activites")||
                !objetJson.containsKey("cycle")||
                !objetJson.containsKey("nom")||
                !objetJson.containsKey("prenom")||
                !objetJson.containsKey("sexe")){

            valRetour = false;
        }
        return valRetour;
    }

    /**
     * Permet de valider le sexe d'un membre.
     *
     * @param codeSexe
     * @return
     */
    public static boolean validerSexeMembre(int codeSexe){
        return (codeSexe == 0 || codeSexe == 1 || codeSexe ==2);
    }


}
