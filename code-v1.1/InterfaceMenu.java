import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class InterfaceMenu {

    /*********************************************************************************************************
        ***************************************** Menu ********************************************
    *********************************************************************************************************/

    /**
    * Affiche le menu pour un administrateur connecté.
    * <p>
    * Cette méthode affiche différentes options pour l'administrateur après sa connexion. 
    * Elle offre un menu avec les choix suivants :
    * <ol>
    * <li>Modification de mot de passe personnel.</li>
    * <li>Assigner un projet.</li>
    * <li>Modifier des paramètres.</li>
    */
    public static void menuAdminConnecter(){
       System.out.println("\n\nVeuillez entrer voitre choix: ");
        System.out.println("1. Modification de mot de passe personnel.");
        System.out.println("2. Assigner Projet.");
        System.out.println("3. Modifier parametre.\n"); 
    }

    /**
    * Affiche le menu pour un employé connecté.
    * 
    * Le menu propose les options suivantes :
    * 1. Démarrer une activité.
    * 2. Terminer une activité.
    * 3. Imprimer le salaire pour une période donnée.
    * 
    * Cette méthode est uniquement destinée à l'affichage des options.
    */
    public static void menuEmployeConnecter(){
        System.out.println("\n\nVeuillez entrer voitre choix: ");
        System.out.println("1. Demarer une activite.");
        System.out.println("2. Terminer une activite.");
        System.out.println("3. Imprimer salaire pour une periode.\n");
    }

    /**
     * Affiche les options de menu à l'utilisateur et lit son choix.
     * Cette méthode affiche un menu de quatre options et attend que l'utilisateur entre un choix.
     * Le choix est lu à partir de l'entrée standard et renvoyé.
     *
     * @return Le choix de l'utilisateur sous forme d'entier.
     */
    public static int choixMenu(){
        System.out.println("    1. Connection Employe");
        System.out.println("    2. Connection Administrateur");
        System.out.println("    3. Option");
        System.out.println("    4. Quiter");
        System.out.print("Votre choix en entier: ");
        int choix = obtenirInt();
        return choix;
    }

    /*********************************************************************************************************
     ********************************************** DEMANDE INPUT ********************************************
    *********************************************************************************************************/

    /**
    * Demande et retourne le nom d'un projet.
    *  <p>
    * Cette méthode invite l'utilisateur à entrer le nom d'un projet.
    * Elle utilise la méthode {@code obtenirString()} pour lire l'entrée utilisateur.
    * </p>
    * @return Le nom du projet saisi par l'utilisateur.
    */
    public static String demandeNomProjet(){
        System.out.println("Veuillez entrer le nom du projet: ");
        return obtenirString();
    }

    /**
    * Demande à l'utilisateur de saisir une discipline et valide son entrée.
    * <p>
    * Cette méthode invite l'utilisateur à entrer une discipline. Elle vérifie ensuite si 
    * l'entrée correspond à une valeur valide définie dans l'énumération {@code Poste}.
    * En cas d'entrée invalide, elle demande à l'utilisateur de réessayer jusqu'à ce qu'une 
    *  discipline valide soit saisie.
    * </p>
    * <p>
    * La comparaison des disciplines est insensible à la casse, grâce à la conversion de 
    * l'entrée utilisateur en minuscules.
    * </p>
    * @return La discipline validée saisie par l'utilisateur.
    */
    public static String demandeDiscipline(){
        System.out.println("Veuillez entrer une discipline valide: ");
        String discipline;
        while(true){
            discipline = obtenirString().toLowerCase();
            try{
                Poste.valueOf(discipline);
                break;
            }catch(IllegalArgumentException e){
                System.out.println("Discipline invalide. Veuillez réessayer: ");
            }
        }
        return discipline;
    }

    /**
    * Demande à l'utilisateur de saisir une date dans un format spécifique.
    * <p>
    * Cette méthode utilise un {@code Scanner} fourni en argument pour lire l'entrée de l'utilisateur.
    * Elle attend que l'utilisateur saisisse une date au format "JJ/MM/AAAA".
    * En cas de format invalide, elle informe l'utilisateur et demande de réessayer.
    * </p>
    * <p>
    * La méthode continue de demander une date jusqu'à ce qu'une entrée valide soit fournie.
    * </p>
    * @param scanner Le {@code Scanner} à utiliser pour lire l'entrée de l'utilisateur.
    * @return La date saisie par l'utilisateur, convertie en objet {@code Date}.
    * @throws ParseException Si l'analyse de la date échoue.
    */
    public static Date demanderDate(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        while (date == null) {
            try {
                System.out.println("Entrez une date (format JJ/MM/AAAA) : ");
                date = sdf.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Format de date invalide. Veuillez réessayer.");
            }
        }
        return date;
    }

    /**
    * Demande à l'utilisateur de saisir un choix sous forme d'entier.
    * <p>
    * Cette méthode invite l'utilisateur à entrer un choix numérique. 
    * Elle utilise la méthode {@code obtenirInt()} pour lire et retourner l'entrée utilisateur 
    * en tant qu'entier.
    * </p>
    * @return Le choix de l'utilisateur sous forme d'entier.
    */
    public static int demandeChoix(){
        System.out.print("Votre choix en entier: ");
        return obtenirInt();
    }

    /**
    * Demande et retourne le nom d'utilisateur.
    * <p>
    * Cette méthode invite l'utilisateur à saisir son nom d'utilisateur.
    * Elle utilise la méthode {@code obtenirString()} pour lire l'entrée de l'utilisateur
    * et retourne cette saisie.
    * </p>
    * @return Le nom d'utilisateur saisi par l'utilisateur.
    */
    public static String demandeNomUtilisateur(){
        System.out.println("Veuillez entrer votre nom d'utilisateur: ");
        return obtenirString();
    }

    /**
    * Demande à l'utilisateur de saisir un identifiant (ID).
    * <p>
    * Cette méthode invite l'utilisateur à entrer un ID, qui est un entier.
    * Elle utilise la méthode {@code obtenirInt()} pour lire et retourner l'entrée utilisateur 
    * sous forme d'entier.
    * </p>
    * @return L'ID saisi par l'utilisateur sous forme d'entier.
    */
    public static int demandeID(){
        System.out.println("Veuillez entrer le ID: ");
        return obtenirInt();
    }

    /**
    * Demande à l'utilisateur de saisir son mot de passe.
    * <p>
    * Cette méthode invite l'utilisateur à entrer son mot de passe. 
    * Elle utilise la méthode {@code obtenirString()} pour lire l'entrée de l'utilisateur
    * et retourne le mot de passe saisi.
    * </p>
    * <p>
    * Note : Cette méthode retourne le mot de passe sous forme de chaîne de caractères 
    * sans masquer les caractères saisis.
    * </p>
    * @return Le mot de passe saisi par l'utilisateur.
    */
    public static String demandeMotPasse(){
        System.out.println("Veuillez entrer votre mot de passe: ");
        return obtenirString();
    }


    private static String obtenirString(){
        return new Scanner(System.in).nextLine();
    }

    private static int obtenirInt(){
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }
    

}