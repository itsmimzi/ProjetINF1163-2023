import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class TimeLog {

    private List<Projet> projets;
    private List<Employe> employes;
    private Admin admin;
    private PayrollSystem payrollSystem;

    public TimeLog() {
        // Initialisation des listes de projets et d'employés
        projets = new ArrayList<>();
        employes = new ArrayList<>();
        
        // Initialisation du système de paie
        payrollSystem = new PayrollSystem();
        
     // Initialisation de l'admin
        admin = new Admin("admin","admin");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Rapport d'heures travaillées par projet");
            System.out.println("2. Rapport d'heures travaillées par employé");
            System.out.println("3. Calcul des salaires");
            System.out.println("4. Générer le rapport de déduction");
            System.out.println("5. Imprimer les talons de paie");
            System.out.println("6. Quitter");

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
                    genererRapportDeduction();
                    break;
                case 5:
                    imprimerTalonsPaie();
                    break;
                case 6:
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

    private void calculerSalaire() {
        for (Employe employe : employes) {
            double totalSalaire = 0;

            for (Map.Entry<String, Double> entry : ((Map<String, Double>) employe.getProjetsTravailles()).entrySet()) {
                String discipline = entry.getKey();
                double hoursWorked = entry.getValue();


                // Utilisation de PayrollSystem pour calculer le salaire net
                double salaireNet = payrollSystem.netFromBrute(employe.getIdEmploye(), hoursWorked);
                totalSalaire += salaireNet;
            }

            System.out.println("Salaire total pour l'employé " + employe.getNomEmploye() + ": $" + totalSalaire);
        }
    }

    private void genererRapportDeduction() {

    }

    private void imprimerTalonsPaie() {
 
    }

    public static void main(String[] args) {
        TimeLog timeLog = new TimeLog();
        timeLog.run();
    }
}
