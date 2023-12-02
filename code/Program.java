import java.io.Console;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Program {

    /**
     * Point d'entrée principal du programme.
     * Cette méthode lance une boucle de menu de console qui permet à l'utilisateur de choisir parmi
     * différentes options jusqu'à ce qu'il décide de quitter.
     *
     * @param args Arguments de ligne de commande non utilisés dans cette application.
     */
    public static void main(String[] args) {
        menu();
    }

    /**
    * Affiche le menu principal du programme et gère la navigation de l'utilisateur.
    * Cette méthode affiche un menu de console répétitif jusqu'à ce que l'utilisateur choisisse de quitter.
    * À chaque itération, elle invite l'utilisateur à faire un choix, traite ce choix via la méthode
    * {@link #traitementMenu(int)} et vérifie si l'utilisateur a choisi de quitter le programme.
    */
    public static void menu(){
        System.out.println("-- Bienvenue sur le programme TimeLog --\n");
        int choixMenu = -1;
        while (choixMenu != 4) {  
            System.out.println("Veuillez faire un choix et entre le numero du menu (1-4): ");
            choixMenu = choixMenu(); 
            traitementMenu(choixMenu);
        }
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
        System.out.println("    2. Option");
        System.out.println("    3. Option");
        System.out.println("    4. Quiter");
        System.out.print("Votre choix en entier: ");
        int choix = Integer.parseInt(choixScan.nextLine());
        return choix;
    }



    public static void traitementMenu(int choixMenu){
        
    }


}
