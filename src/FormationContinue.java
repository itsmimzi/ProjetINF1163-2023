import java.io.IOException;

/**
 * Classe FormationContinue.
 * Cette classe permet la validation des déclarations d'activités de
 * formation continue pour les membres d'un ordre professionnel. Chaque ordre professionnel impose à
 * ses membres d'effectuer un certain nombre d'heures d'activité de formation pour une période donnée.
 * La période en question est appelée un cycle. Dans ce cas-ci,
 * le cycle dure 2 ans et les membres de l'Ordre doivent effectuer un minimum de 40 heures de
 * formation continue durant le cycle. Le résultat de cette validation sera envoyé vers un fichier
 * json avec tout ses messages d'erreurs.
 *
 * @version 11-11-2021
 * @author Ken Lessard-Gerber
 * @version 2.1
 */
public class FormationContinue {

    // Constance de classe -> Message d'erreur.
    public static final String CATEGORIE_NON_RECONNU = " est dans une catégorie non reconnue. Elle sera ignorée.";
    public static final String HEURE_MANQUANT_PARTIE_1 = "Il manque ";
    public static final String HEURE_MANQUANT_PARTIE_2 = " heures de formation pour complèter le cycle";
    public static final String CYCLE_INVALIDE = "Le cycle entrée est invalide";
    public static final String DATE_INVALIDE = "La date a un format invalide pour l'activité suivante: ";
    public static final String HEURE_TRANSFERE_INVALIDE = "Le nombre d'heure transféré est invalide ";
    public static final String HEURE_COMBINE = " pour atteindre le min de 17h pour la liste des 6 categorie";
    public static final String HEURE_ACTIVITE = " a un nombre d'heure d'activité invalide";
    public static final String HEURE_ACTIVITE_MINIMUM = "le nombre d'heure pour une activite doit etre superieur a 1.";
    public static final String ORDRE_INVALIDE = "L'ordre professionnelle entrée n'est pas valides.";
    public static final String ORDRE_ABSENT = "Le champ ordre est vide.";

    /**
     * Cette méthode est la méthode principal du programme. Elle va appel à
     * une méthode pour lire un fichier et y faire ses validation. En second lieux,
     * elle va écrire ses message d'erreur dans un fichier json.
     * @param args tableau de String d'ou le premier élément sera le nom du fichier d'entrée et
     *             le deuxième élément sera le nom du fichier de sortie.
     */
    public static void main(String[] args) throws IOException {
        Membre membre = lectureDonnee(args[0],args[1]);
        if(membre != null){
            ecritureDonnee(membre,args[1]);
        }
    }

    /**
     * Cette méthode permet la lecture des donnée du fichier json, donc le nom est passé en
     * paramètre. Elle va créer un membre et ses activité pour ensuite y faire les vérification
     * voulu. Des messages d'erreurs sont envoyer au membres en liens avec les erreurs des données
     * dans le fichier json en entrée.
     * @param fichier nom du fichier d'entrée.
     * @return Le Membre à retourner.
     */
    private static Membre lectureDonnee(String fichier, String fichierRetour){
        Membre m = Lecture.lireFichierJson(fichier);
        traiterDonneeMembre(m,fichierRetour);
        return m;
    }

    /**
     * Permet de traiter les donnees selon le différent ordre et valider si
     * l'ordre du membre est valide.
     *
     * @param m
     */
    public static void traiterDonneeMembre(Membre m,String fichierRetour){
        if(m.getErreurs().size() == 0){
            if(ValidationArchitectes.validerOrdreArchitectes(m.getOrdre())){
                traiterDonneeArchitectes(m,fichierRetour);
            }
            else if(ValidationGeologue.validerOrdreGeologue(m.getOrdre())){
                traiterDonneeGeologue(m,fichierRetour);
            }
            else if(ValidationPsychologue.validerOrdrePsychologue(m.getOrdre())){
                traiterDonneePsychologue(m,fichierRetour);
            }else if(ValidationPodiatres.validerOrdrePodiatres(m.getOrdre())){
                traiterDonneePodiatres(m,fichierRetour);
            }
        }
    }

    /**
     * Permet de traiter les donnee d'un Architectes. Elle va valider
     * ses activitees et ajouter les erreurs en conséquence.
     *
     * @param membre
     */
    public static void traiterDonneeArchitectes(Membre membre, String fichierSortie){
        if(!ValidationArchitectes.validerCycleArchitectes(membre.getCycle())){
            membre.ajouterErreur(CYCLE_INVALIDE+" pour l'ordre: "+membre.getOrdre());
        }else {
            ValidationArchitectes.validerPermisArchitectes(membre);
            ajouterErreurSexeInvalide(membre);
            //ajoutErreurActiviteValide(membre,fichierSortie);
            ajoutErreurActiviteValideArchitectes(membre,fichierSortie);
            enleverActiviteInvalideList(membre);
            ajouterErreurHeureTransfere(membre);
            ajoutErreurActiviteCombineHeure(membre);
            calculHeureTotal(membre,Validation.HEURE_TOTAL_ARCHITECTES);
        }
    }

    /**
     * Permet de traiter les donnee d'un Geologue. Elle va valider
     * ses activitees et ajouter les erreurs en conséquence.
     *
     * @param membre
     */
    public static void traiterDonneeGeologue(Membre membre, String fichierSortie){
        if(!ValidationGeologue.validerCycleOrdreArchitectes(membre.getCycle())){
            membre.ajouterErreur(CYCLE_INVALIDE+" pour l'ordre: "+membre.getOrdre());
        }else if(membre.getErreurs().size() == 0) {
            ValidationGeologue.validerPermisGéologues(membre);
            ajouterErreurSexeInvalide(membre);
            ajoutErreurActiviteValideGeologues(membre,fichierSortie);
            enleverActiviteInvalideList(membre);
            calculHeureTotalGeologue(membre);
        }
    }


    public static void traiterDonneePsychologue(Membre membre, String fichierSortie){
        if(!ValidationPsychologue.validerCycleOrdrePsychologue(membre.getCycle())){
            membre.ajouterErreur(CYCLE_INVALIDE+" pour l'ordre: "+membre.getOrdre());
        }else {
            ValidationPsychologue.validerPermisPsychologues(membre);
            ajouterErreurSexeInvalide(membre);
            ajoutErreurActiviteValidePsychologues(membre,fichierSortie);
            enleverActiviteInvalideList(membre);
            calculHeureTotalPsychologue(membre);
        }
    }

    public static void traiterDonneePodiatres(Membre membre,String fichierRetour){
        if(!ValidationGeologue.validerCycleOrdreArchitectes(membre.getCycle())){
            membre.ajouterErreur(CYCLE_INVALIDE+" pour l'ordre: "+membre.getOrdre());
        }else if(membre.getErreurs().size() == 0) {
            ValidationPodiatres.validerPermisPodiatres(membre);
            ajouterErreurSexeInvalide(membre);
            ajoutErreurActiviteValideGeologues(membre,fichierRetour);
            enleverActiviteInvalideList(membre);
            calculerHeureTotalPodiatres(membre);
        }
    }

    /**
     * Cette méthode permet l'écriture des erreurs d'un Membre prit en paramètre, elle écrit ses erreurs
     * sur un fichier json de sortie.
     * @param membre Le Membre prit en paramètre ou on y prend les erreurs.
     * @param
     */
    private static void ecritureDonnee(Membre membre, String fichierRetour) throws IOException {
        Ecriture.ecrireJsonResultat(membre,fichierRetour);
    }


    private static void ajoutErreurActiviteValideArchitectes(Membre membre,String fichierSortie){
        membre.getActivitees().forEach((l)->{
            validerListArchitectes(membre,l, fichierSortie);
        });
    }

    private static void ajoutErreurActiviteValideGeologues(Membre membre,String fichierSortie){
        membre.getActivitees().forEach((l)->{
            validerListArchitectes(membre,l, fichierSortie);
        });
    }

    private static void ajoutErreurActiviteValidePsychologues(Membre membre,String fichierSortie){
        membre.getActivitees().forEach((l)->{
            ValidationPsychologue.validerListPsychologues(membre,l, fichierSortie);
        });
    }



    /**
     * Cette méthode va prendre en paramètre un membre et va un liste d'activité en
     * paramètre. Elle va ensuite filtrer la liste des activité de se membre pour que les
     * activité y sois valide selon des critère voulu.
     * @param m le Membre à ajuster la liste d'activité.
     */
    public static void enleverActiviteInvalideList(Membre m){
        if(ValidationGeologue.validerOrdreGeologue(m.getOrdre())||
                ValidationPodiatres.validerOrdrePodiatres(m.getOrdre())){
            m.setActivitees(Activite.creerListActiviteValideOrdreGeologue(m));
        }else {
            m.setActivitees(Activite.creerListActiviteValideOrdre(m));
        }
    }

    /**
     * Permet de valider la list des activité d'un architectes.
     *
     * @param m
     * @param l
     */
    public static void validerListArchitectes(Membre m,Activite l, String fichierSortie){
        if(!Validation.validerActivite(l.getCategorie())){
            m.ajouterErreur("L'activité "+l.getDescription()+ CATEGORIE_NON_RECONNU);
        }if(!Validation.validerHeureActiviteMinimum(l.getHeures())){
            m.ajouterErreur(l.getCategorie()+HEURE_ACTIVITE+"\n"+HEURE_ACTIVITE_MINIMUM);
        }if(!Validation.validerDate(l.getDate(),m.getOrdre(),m.getCycle())){
            m.ajouterErreur("La date de l'activité "+l.getDescription()+" est hors de l'intervalle");
        }if(!Validation.validerMaxParJour(l)){
            m.ajouterErreur("L'activité "+l.getDescription()+" dépasse le nombre maximum d'heures par jour" +
                    "pour une activité.");
        }
        Validation.validerDescription(l,fichierSortie);
    }

    /**
     * Cette méthode prend en paramètre une Activité et un Membre.
     * Elle va ajouter les heures de l'Activité au Membre en lien
     * avec sa catégorie.
     * @param l l'Activité à faire les validations.
     * @param m Le Membre à ajouter les heures.
     */
    public static void ajouterHeureActiviteMembre(Activite l, Membre m){
        if(l.getCategorie().equals(Validation.ACTIVITE_PRÉSENTATION)){
            m.ajouterHeuresPresentations(Validation.validerHeureNegative(l.getHeures()));
        }else if(l.getCategorie().equals(Validation.ACTIVITE_GROUPE_DISCUSSION)){
            m.ajouterHeuresDiscussion(Validation.validerHeureNegative(l.getHeures()));
        }else if(l.getCategorie().equals(Validation.ACTIVITE_PROJET_RECHERCHE)){
            m.ajouterHeuresProjet(Validation.validerHeureNegative(l.getHeures()));
        }else if(l.getCategorie().equals(Validation.ACTIVITE_REDACTION_PROFESSIONNELLE)){
            m.ajouterHeuresRedaction(Validation.validerHeureNegative(l.getHeures()));
        }
    }

    /**
     * Cette méthode prend en paramètre un Membre et va faire une validation
     * des heure transferes de se Membre et y ajouter un erreur en lien avec le
     * resultat.
     * @param membre Le Membre à ajouter les erreurs selon le resultat.
     */
    public static void ajouterErreurHeureTransfere(Membre membre){
        if(!Validation.validerHeureTransfere(membre)){
            membre.ajouterErreur(HEURE_TRANSFERE_INVALIDE);
        }
    }

    /**
     * Cette méthode prend en paramètre un Membre et nombre d'heure total du cycle en cours.
     * Elle va vérifier si se nombre d'heure est valide et ajouter les erreurs en lien avec
     * le résultat.
     * @param nombreTotal Le nombre d'heure total à faire la vérification.
     * @param m Le Membre à y ajouter les erreurs.
     */
    public static void ajoutErreurHeureTotal(int nombreTotal, Membre m,int heureMax){
        if(!Validation.validerHeureTOTAl(nombreTotal)){
            int diffrence = heureMax - nombreTotal;
            m.ajouterErreur(HEURE_MANQUANT_PARTIE_1+(diffrence)+HEURE_MANQUANT_PARTIE_2);
        }
    }

    /**
     * Permet de calculer les heure total du cycle d'un Podiatres.
     *
     * @param membre Le membre a faire les calcul.(Membre)
     */
    public static void calculerHeureTotalPodiatres(Membre membre){
        int nombre =0;
        for(int i=0;i<membre.getActivitees().size();i++){
            nombre += membre.getActivitees().get(i).getHeures();
        }
        ajoutErreurHeureTotal(nombre,membre,ValidationGeologue.HEURE_TOTAL_GEOLOGUES);
        membre.setHeuresTotal(nombre);
        membre.setComplet(Validation.validerHeureTOTALArchitectes(membre.getHeuresTotal()));
    }


    /**
     * Permet de calculer les heure total du cycle d'un Géologues.
     *
     * @param membre Le membre permettant de faire les calcul. (Membre)
     */
    public static void calculHeureTotalGeologue(Membre membre){
        int nombre =0;
        for(int i=0;i<membre.getActivitees().size();i++){
            nombre += membre.getActivitees().get(i).getHeures();
        }
        ajoutErreurHeureTotal(nombre,membre,ValidationGeologue.HEURE_TOTAL_GEOLOGUES);
        membre.setHeuresTotal(nombre);
        membre.setComplet(Validation.validerHeureTOTALArchitectes(membre.getHeuresTotal()));
    }

    /**
     * Cette méthode prend en paramètre un Membre et elle va y ajouter le nombre d'heure total
     * dans se cycle selon le calcul résultant.
     *
     * @param m Le membre passé en paramètre.
     */
    private static void calculHeureTotal(Membre m,int heureMax){
        int nombreTotal =0;
        m.getActivitees().forEach(ligne->{
            ajouterHeureActiviteMembre(ligne,m);
        });
        nombreTotal += m.getHeureTransferees()
                    +m.getHeuresCombinees()
                    +m.getHeuresPresentations()+m.getHeuresDiscussion()+m.getHeuresProjet()+m.getHeuresRedaction();
        ajoutErreurHeureTotal(nombreTotal,m,heureMax);
        m.setHeuresTotal(nombreTotal);
        m.setComplet(Validation.validerHeureTOTALArchitectes(m.getHeuresTotal()));
    }

    /**
     * Permet de calculer les heure total du cycle d'un Psychologues.
     *
     * @param membre Le membre permettant de faire les calcul. (Membre)
     */
    private static void calculHeureTotalPsychologue(Membre membre){
        int nombreTotal =0;
        int heuresConf = 0;
        for(int i=0 ; i<membre.getActivitees().size();i++){
            if(membre.getActivitees().get(i).getCategorie() == "conférence"){
                heuresConf += membre.getActivitees().get(i).getHeures();
                if(heuresConf >= ValidationPsychologue.HEURE_MAXIMUM_ACTIVITE_CONFERENCE){
                    heuresConf =ValidationPsychologue.HEURE_MAXIMUM_ACTIVITE_CONFERENCE;
                }
            }else{
                nombreTotal += membre.getActivitees().get(i).getHeures();
            }
        }
        nombreTotal += heuresConf;
        ajoutErreurHeureTotal(nombreTotal,membre,ValidationPsychologue.HEURE_TOTAL_PSYCHOLOGUES);
        membre.setHeuresTotal(nombreTotal);
        membre.setComplet(ValidationPsychologue.validerHeureTotalPsychologue(membre.getHeuresTotal()));
    }

    /**
     * Cette méthode prend en paramètre un Membre et va y ajouter un erreur selon le
     * résultat de la vérification du nombre d'heure Activité combiné.
     * @param m Le membre passé en paramètre.
     */
    private static void ajoutErreurActiviteCombineHeure(Membre m){
        if(!Validation.validerActiviteCombine(m)){
            int nombreManquant = Validation.HEURE_CATEGORIE_MULTIPLE_MAX-m.getHeuresCombinees();
            m.ajouterErreur(HEURE_MANQUANT_PARTIE_1 +nombreManquant+HEURE_COMBINE);
        }
    }

    /**
     * Permet d'ajouter un erreur au Membre quand la valeur dans le champs Sexe est invalide.
     *
     * @param membre membre a ajouter l'erreur. (Membre)
     */
    private static void ajouterErreurSexeInvalide(Membre membre){
        if(!Validation.validerSexeMembre(membre.getSexe())){
            membre.ajouterErreur("Le champ sexe contiendra une valeur numérique, uniquement " +
                    "les valeurs 0, 1 ou 2 sont acceptées dans ce champ. ");
        }
    }

}
