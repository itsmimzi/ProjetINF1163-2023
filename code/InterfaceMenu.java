import java.util.Scanner;

public class InterfaceMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            // Afficher le menu
            System.out.println("=== Menu TimeLog ===");
            System.out.println("1. Rapport d'etat d'un projet choisi");
            System.out.println("2. Rapport d'etat global");
            System.out.println("3. Rapport des salaires d'un employe");
            System.out.println("4. Talon de paie d'un employe");
            System.out.println("5. Totaux des salaires bruts et nets");
            System.out.println("6. Quitter");
            System.out.print("Choisissez une option : ");

            // Lire le choix de l'utilisateur
            choix = scanner.nextInt();

            // Exécuter l'action correspondante
            switch (choix) {
                case 1:
                    rapportEtatProjet();
                    break;
                case 2:
                    rapportEtatGlobal();
                    break;
                case 3:
                    rapportSalairesEmploye();
                    break;
                case 4:
                    talonPaieEmploye();
                    break;
                case 5:
                    totauxSalaires();
                    break;
                case 6:
                    System.out.println("Programme termine.");
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez choisir une option valide.");
            }

        } while (choix != 6);

        scanner.close();
    }

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
}
