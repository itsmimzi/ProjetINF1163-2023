import java.util.ArrayList;
import java.util.Scanner;

public class TimeLog {
    ArrayList<Employe> listeEmployes = new ArrayList<>();
    ArrayList<Projet> listeProjets = new ArrayList<>();
    

    public void ajouterEmployer(Employe employe){
        if(listeEmployes.contains(employe)){
            System.out.println("Employer existant dans le systeme.");
        }else{
            listeEmployes.add(employe);
        }
    }

    public void ajouterProjet(Projet projet){
        if(listeProjets.contains(projet)){
            System.out.println("Projet deja existant dans le systeme.");
        }else{
            listeProjets.add(projet);
        }
    }

    public boolean employeExistant(int idEmploye, String nomEploye){
        for (Employe employe : listeEmployes) {
            if(idEmploye == employe.getID() && nomEploye.equals(employe.getNom())){
                return true;
            }
        }
        return false;
    }

    public void demarerActivite(int idUtilisateur){
        String nomProjet = InterfaceMenu.demandeNomProjet();
        String discipline = InterfaceMenu.demandeDiscipline();
        Employe employeCourant = obtenirEmploye(idUtilisateur);
        
        // Regardersi emplye a deja une activite en cours
        // ajouter activite si non.
        // TODO
    }

    public void terminerActivite(){
        System.out.println("Terminer activite.");
        // Terminer une activite
        // TODO

    }

    public void menuTimeLog(){
        interfaceMenu();
    }

    /**
    * Affiche le menu principal du programme et gère la navigation de l'utilisateur.
    * Cette méthode affiche un menu de console répétitif jusqu'à ce que l'utilisateur choisisse de quitter.
    * À chaque itération, elle invite l'utilisateur à faire un choix, traite ce choix via la méthode
    * {@link #traitementMenu(int)} et vérifie si l'utilisateur a choisi de quitter le programme.
    */
    public  void interfaceMenu(){
        System.out.println("-- Bienvenue sur le programme TimeLog --\n");
        int choixMenu = -1;
        while (choixMenu != 4) {  
            System.out.println("Veuillez faire un choix et entre le numero du menu (1-4): ");
            choixMenu = InterfaceMenu.choixMenu();
            traitementMenu(choixMenu);
        }
    }

     


    public  void traitementMenu(int choixMenu){
        if(choixMenu == 1){
            menuEmploye();
        }else if(choixMenu == 2){
            //menuAdmin();
            // TODO
        }else if(choixMenu == 3){
            // Rapport sans connection
        }else if(choixMenu == 4){
            // Quitter
            System.out.println("Au revoir !");
        }
        System.out.println("\n");
    }

    public void menuEmploye(){
        int idUtilisateur = InterfaceMenu.demandeIDUtilisateur();
        String nomUtilisateur = InterfaceMenu.demandeNomUtilisateur();
        boolean connectionEtablie = employeExistant(idUtilisateur, nomUtilisateur);
        interfaceMenuEmployeConnecter(idUtilisateur,connectionEtablie);  
    }

    public void interfaceMenuEmployeConnecter(int idUtilisateur,boolean connectionEtablie){
        if(connectionEtablie){
            InterfaceMenu.menuEmployeConnecter();
            Scanner choixScan = new Scanner(System.in);
            System.out.print("Votre choix en entier: ");
            int choix = Integer.parseInt(choixScan.nextLine());
            if(choix == 1){
                demarerActivite(idUtilisateur);
            }else if(choix == 2){
                terminerActivite();
            }
            // Ajouter autre choix
            // TODO
            System.out.println("\n");
        }
    }

    public Employe obtenirEmploye(int idUtilisateur){
        for (Employe employe : listeEmployes) {
            if(employe.getID() == idUtilisateur){
                return employe;
            }
        }
        return null;
    }
}
