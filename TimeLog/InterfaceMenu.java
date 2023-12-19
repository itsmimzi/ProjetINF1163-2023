import java.util.Scanner;

public class InterfaceMenu {

    private static void rapportEtatProjet() {
        // Implémenter la fonctionnalité
        System.out.println("Fonctionnalite non implementee : Rapport d'etat d'un projet choisi");
    }

    private static void rapportEtatGlobal() {
        // Implémenter la fonctionnalité
        System.out.println("Fonctionnalite non implementee : Rapport d'etat global");
    }

    private static void rapportSalairesEmploye() {
        // Implémenter la fonctionnalité
        System.out.println("Fonctionnalite non implementee : Rapport des salaires d'un employe");
    }

    private static void talonPaieEmploye() {
        // Implémenter la fonctionnalité
        System.out.println("Fonctionnalite non implementee : Talon de paie d'un employe");
    }

    private static void totauxSalaires() {
        // Implémenter la fonctionnalité
        System.out.println("Fonctionnalite non implementee : Totaux des salaires bruts et nets");
    }

    public static void menuEmployeConnecter(){
        System.out.println("Veuillez entrer voitre choix: ");
        System.out.println("1. Demarer une activite.");
        System.out.println("2. Terminer une activite.");
    }

    public static String demandeNomUtilisateur(){
        System.out.println("Veuillez entrer votre nom d'utilisateur: ");
        String nomUtilisateur = new Scanner(System.in).nextLine();
        return nomUtilisateur;
    }

    public static int demandeIDUtilisateur(){
        System.out.println("Veuillez entrer votre ID: ");
        int idUtilisateur = Integer.parseInt(new Scanner(System.in).nextLine());
        return idUtilisateur;
    }

    /**
     * Affiche les options de menu à l'utilisateur et lit son choix.
     * Cette méthode affiche un menu de quatre options et attend que l'utilisateur entre un choix.
     * Le choix est lu à partir de l'entrée standard et renvoyé.
     *
     * @return Le choix de l'utilisateur sous forme d'entier.
     */
    public static int choixMenu(){
        Scanner choixScan = new Scanner(System.in);
        System.out.println("    1. Connection Employe");
        System.out.println("    2. Connection Administrateur");
        System.out.println("    3. Option");
        System.out.println("    4. Quiter");
        System.out.print("Votre choix en entier: ");
        int choix = Integer.parseInt(choixScan.nextLine());
        
        return choix;
    }

    public static String demanderNomProjet(){
        System.out.println("Veuillez entrer le nom du projet: ");
        String nomProjet = new Scanner(System.in).nextLine();
        return nomProjet;
    }

    public static String demanderDiscipline(){
        System.out.println("Veuillez entrer la discipline: ");
        String discipline = new Scanner(System.in).nextLine();
        return discipline;
    }
}