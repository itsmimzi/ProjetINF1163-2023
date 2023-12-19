import java.util.*;

public class TimeLog {

    private List<Projet> projets;
    private List<Employe> employes;
    private Admin admin;

    public TimeLog() {
        // Initialisation des listes de projets et d'employés
        projets = new ArrayList<>();
        employes = new ArrayList<>();
        // Initialisation de l'admin
        admin = new Admin("admin", "admin");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Rapport d'heures travaillées par projet");
            System.out.println("2. Rapport d'heures travaillées par employé");
            System.out.println("3. Calcul des salaires");
            System.out.println("4. Quitter");

            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    genererRapportHeureProjet();
                    break;
                case 2:
                    genererRapportHeureEmploye();
                    break;
                case 3:
                    calculerSalaire();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }
        }
    }

    private void genererRapportHeureProjet() {
        // Implémentez la logique pour générer le rapport par projet
        // ...

        System.out.println("Rapport généré par projet.");
    }

    private void genererRapportHeureEmploye() {
        // Implémentez la logique pour générer le rapport par employé
        // ...

        System.out.println("Rapport généré par employé.");
    }

    public void calculerSalaire() {
        for (Employe employe : employes) {
            double totalSalaire = 0;

            for (Map<String, Double> disciplineWork : employe.getProjectWork().values()) {
                for (double hoursWorked : disciplineWork.values()) {
                    // Supposons un taux horaire de 10$ pour simplifier
                    double hourlyRate = 10.0;
                    totalSalaire += hoursWorked * hourlyRate;
                }
            }

            System.out.println("Salaire brut pour l'employé " + employe.getUsername() + ": $" + totalSalary);
        }
    }


    public static void main(String[] args) {
        TimeLog timeLog = new TimeLog();
        timeLog.run();
    }

}
