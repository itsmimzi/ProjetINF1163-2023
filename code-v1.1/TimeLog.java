import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorCompletionService;

public class TimeLog {

    private ArrayList<Employe> listeEmployes = new ArrayList<>();
    private ArrayList<Projet> listeProjets = new ArrayList<>();
    private ArrayList<Administrateur> listeAdministrateurs = new ArrayList<>();
    private Administrateur administrateur = new Administrateur("admin", 999999999, "Admin", new Date(), null, 25, 30);
    private Rapport rapport = new Rapport();
    
    


    /*********************************************************************************************************
    **************************************** ACCESSEURS & MODIFICATEURS **************************************
    *********************************************************************************************************/

    public List<Employe> getListeEmployes(){
        return listeEmployes;
    }

    public List<Projet> getListeProjets(){
        return listeProjets;
    }

    public void setListeEmployes(ArrayList<Employe> newListeEmployes){
        this.listeEmployes = newListeEmployes;
        //sauvegarderDonnees();
    }

    public void setListeProjets(ArrayList<Projet> newListeProjets){
        this.listeProjets = newListeProjets;
        //sauvegarderDonnees();
    }

    /*********************************************************************************************************
    ***************************************** FONCTIONS DE CLASSE ********************************************
    *********************************************************************************************************/

    /**
    * Ajoute un employé à la liste des employés si celui-ci n'existe pas déjà.
    * <p>
    * Cette méthode vérifie d'abord si l'employé spécifié est déjà présent dans la liste des employés.
    * Si l'employé est déjà présent, un message est affiché pour indiquer que l'employé existe déjà dans le système.
    * Sinon, l'employé est ajouté à la liste.
    * </p>
    * 
    * @param employe L'objet Employe à ajouter à la liste des employés.
    */
    public void ajouterEmployer(Employe employe){
        if(listeEmployes.contains(employe)){
            System.out.println("Employer existant dans le systeme.");
        }else{
            listeEmployes.add(employe);
        }
    }

    /**
    * Ajoute un projet à la liste des projets si celui-ci n'existe pas déjà.
    * <p>
    * Cette méthode vérifie d'abord si le projet spécifié est déjà présent dans la liste des projets.
    * Si le projet est déjà présent, un message est affiché pour indiquer que le projet existe déjà dans le système.
    * Sinon, le projet est ajouté à la liste.
    * </p>
    * 
    * @param projet L'objet Projet à ajouter à la liste des projets.
    */
    public void ajouterProjet(Projet projet){
        if(listeProjets.contains(projet)){
            System.out.println("Projet deja existant dans le systeme.");
        }else{
            listeProjets.add(projet);
        }
    }

    /**
    * Vérifie si un employé avec un identifiant et un nom spécifiques existe dans la liste des employés.
    * <p>
    * Cette méthode parcourt la liste des employés et compare l'identifiant et le nom de chaque employé 
    * avec les valeurs fournies en paramètres. Si un employé correspondant est trouvé, la méthode renvoie vrai.
    * Dans le cas contraire, elle renvoie faux.
    * </p>
    * 
    * @param idEmploye L'identifiant de l'employé à rechercher.
    * @param nomEmploye Le nom de l'employé à rechercher.
    * @return true si un employé correspondant aux critères fournis est trouvé, false sinon.
    */
    public boolean employeExistant(int idEmploye, String nomEploye){
        int id = idEmploye;
        String nom = nomEploye;
        for (Employe employe : listeEmployes) {
            if(employe.getIdEmploye() == id  && nom.equals(employe.getNomEmploye())){
                return true;
            }
        }
        return false;
    }

    /**
    * Démarre une activité pour un employé basé sur l'ID utilisateur donné.
    *
    * @param idUtilisateur L'identifiant de l'utilisateur (employé) qui démarre l'activité.
    *
    * Ce méthode effectue les étapes suivantes :
    * 1. Demande le nom du projet et la discipline via l'interface de menu.
    * 2. Récupère les informations de l'employé courant en utilisant son ID.
    * 3. Tente de récupérer le projet actuel en utilisant le nom du projet.
    * 4. Si le projet existe :
    *    a. Vérifie si l'employé courant a déjà une activité en cours.
    *    b. Si aucune activité n'est en cours pour cet employé :
    *       i. Crée une nouvelle activité avec l'ID de l'utilisateur et les informations de l'employé.
    *       ii. Signale le début de l'activité avec le nom du projet et la discipline.
    *       iii. Ajoute la nouvelle activité au projet courant.
    *       iv. Affiche un message confirmant le début de l'activité.
    *       v. Met à jour l'activité courante de l'employé avec la nouvelle activité.
    *    c. Si une activité est déjà en cours pour cet employé, affiche un message indiquant qu'une activité est déjà en cours.
    * 5. Si le projet n'existe pas, affiche un message indiquant que le projet n'existe pas.
    */
    public void demarerActivite(int idUtilisateur){
        String nomProjet = InterfaceMenu.demandeNomProjet();
        String discipline = InterfaceMenu.demandeDiscipline();

        Employe employeCourant = obtenirObjetEmployeSelonID(idUtilisateur);

        Projet projetCourant = obtenirObjetProjetSelonNom(nomProjet);

        if(projetCourant != null){
            // Verifier si activite deja 
            if(employeCourant.getActiviteCourant() == null){
                Activite nouvelleActivite = new Activite(employeCourant);
                nouvelleActivite.signalerDebutActivite(projetCourant, discipline);
                projetCourant.ajouterActivite(nouvelleActivite);
                System.out.println("Activite commencer "+nouvelleActivite.getIdActivite());
                employeCourant.setActiviteCourant(nouvelleActivite);
            }
            else{
                System.out.println("Activite deja en cours pour cette employe.");
            }
            
        }else{
            System.out.println("Projet non existant.");
        }
    }

    /**
    * Termine l'activité en cours pour un employé spécifié par son ID utilisateur.
    *
    * @param idUtilisateur L'identifiant de l'utilisateur (employé) dont l'activité doit être terminée.
    *
    * Cette méthode effectue les opérations suivantes :
    * 1. Récupère les informations de l'employé courant en utilisant son ID.
    * 2. Vérifie si l'employé courant a une activité en cours.
    * 3. Si aucune activité n'est en cours pour cet employé :
    *    a. Affiche un message indiquant qu'il n'y a aucune activité en cours.
    * 4. Si une activité est en cours :
    *    a. Signale la fin de l'activité en cours.
    *    b. Réinitialise l'activité courante de l'employé à null.
    *    c. Affiche un message confirmant la fin de l'activité en cours.
    */
    public void terminerActivite(int idUtilisateur){
        Employe employeCourant = obtenirObjetEmployeSelonID(idUtilisateur);
        if(employeCourant.getActiviteCourant()==null){
            System.out.println("Aucune activite en cours.");
        }
        else{
            employeCourant.getActiviteCourant().signalerFinActivite();
            employeCourant.setActiviteCourant(null);
            System.out.println("Activite en cours terminer.");
        }
    }

    /**
    * Affiche le menu principal du programme et gère la navigation de l'utilisateur.
    * Cette méthode affiche un menu de console répétitif jusqu'à ce que l'utilisateur choisisse de quitter.
    * À chaque itération, elle invite l'utilisateur à faire un choix, traite ce choix via la méthode
    * {@link #traitementMenuPrincipal(int)} et vérifie si l'utilisateur a choisi de quitter le programme.
    */
    public  void interfaceMenuPrincipal(){
        System.out.println("-- Bienvenue sur le programme TimeLog --\n");
        int choixMenu = -1;
        while (choixMenu != 4) {  
            System.out.println("Veuillez faire un choix et entre le numero du menu (1-4): ");
            choixMenu = InterfaceMenu.choixMenu();
            traitementMenuPrincipal(choixMenu);
        }
    }

    
    /**
    * Traite le choix de l'utilisateur dans le menu principal.
    * <p>
    * Cette méthode prend un entier en tant que paramètre, qui représente le choix de l'utilisateur
    * dans le menu principal. Selon la valeur de ce paramètre, différentes méthodes sont appelées :
    * <ul>
    * <li>Si choixMenu est 1, la méthode 'menuEmploye' est appelée.</li>
    * <li>Si choixMenu est 2, la méthode 'menuAdmin' est appelée. Cette partie est actuellement en cours de développement.</li>
    * <li>Si choixMenu est 3, un rapport sans connexion est supposé être généré. Cette fonctionnalité est également en cours de développement.</li>
    * <li>Si choixMenu est 4, un message de fermeture est affiché et le programme se termine.</li>
    * </ul>
    * Après l'exécution de l'action, un saut de ligne est imprimé.
    * </p>
    * 
    * @param choixMenu Le choix de l'utilisateur dans le menu principal, représenté par un entier.
    */    
    public  void traitementMenuPrincipal(int choixMenu){
        if(choixMenu == 1){
            menuEmploye();
        }else if(choixMenu == 2){
            menuAdministrateur();
        }else if(choixMenu == 3){

        }else if(choixMenu == 4){
            System.out.println("Au revoir !");
        }
        System.out.println("\n");
    }

    /**
    * Gère le processus de connexion pour un administrateur et redirige vers le menu d'administration.
    *
    * Cette méthode demande d'abord le nom d'utilisateur et le mot de passe de l'administrateur. Elle 
    * vérifie ensuite si les informations de connexion sont correctes en utilisant la méthode 
    * 'administrateurVerificationMotDePasse'. Si la connexion est établie avec succès (les informations 
    * de connexion sont correctes), elle appelle 'menuAdministrateurConnecter' pour afficher et gérer le 
    * menu administrateur. Si les informations de connexion ne sont pas correctes, le menu administrateur 
    * n'est pas affiché et une erreur de connexion est signalée.
    *
    * Note: 'administrateurVerificationMotDePasse' doit être une méthode qui retourne un booléen 
    * indiquant si le mot de passe fourni correspond à celui de l'administrateur.
    */
    public void menuAdministrateur(){
        String nomUtilisateur = InterfaceMenu.demandeNomUtilisateur();
        String motPasse = InterfaceMenu.demandeMotPasse();
        boolean connectionEtablie = administrateurVerificationMotDePasse(motPasse, nomUtilisateur);
        menuAdministrateurConnecter(motPasse, connectionEtablie);
    }


    /**
    * Gère le processus de connexion d'un employé et redirige vers le menu employé.
    *
    * Cette méthode demande d'abord l'identifiant et le nom d'utilisateur de l'employé. Elle vérifie ensuite
    * si l'employé existe dans le système à l'aide de la méthode 'employeExistant'. Si la connexion est 
    * établie avec succès (l'employé existe), elle appelle 'menuEmployeConnecter' pour afficher et gérer le 
    * menu des employés. Si l'employé n'existe pas, le menu des employés n'est pas affiché et une erreur de 
    * connexion est signalée.
    *
    * Note: 'employeExistant' doit être une méthode qui retourne un booléen indiquant si l'employé avec 
    * l'identifiant et le nom d'utilisateur spécifiés existe dans le système.
    */
    public void menuEmploye(){
        int idUtilisateur = InterfaceMenu.demandeID();
        String nomUtilisateur = InterfaceMenu.demandeNomUtilisateur();
        boolean connectionEtablie = employeExistant(idUtilisateur, nomUtilisateur);
        menuEmployeConnecter(idUtilisateur,connectionEtablie);  
    }

    /**
    * Affiche et gère le menu d'employé après une connexion réussie.
    *
    * Si la connexion est établie (indiquée par 'connectionEtablie'), cette méthode affiche le menu d'employé
    * et exécute l'action choisie. Les actions disponibles incluent le démarrage d'une activité, la 
    * terminaison d'une activité, et la demande d'informations sur le salaire de l'employé. Si la connexion 
    * n'est pas établie, un message d'erreur de connexion est affiché.
    *
    * Note: Les méthodes 'demarerActivite' et 'terminerActivite' doivent être implémentées pour gérer 
    * respectivement le début et la fin des activités d'un employé. La méthode 'demandeSalaire' doit 
    * retourner les informations de salaire de l'employé.
    *
    * @param idUtilisateur L'identifiant de l'employé connecté.
    * @param connectionEtablie Un booléen indiquant si la connexion a été établie avec succès.
    */
    public void menuEmployeConnecter(int idUtilisateur,boolean connectionEtablie){
        if(connectionEtablie){
            InterfaceMenu.menuEmployeConnecter();
            int choix = InterfaceMenu.demandeChoix();
            if(choix == 1){
                demarerActivite(idUtilisateur);
            }else if(choix == 2){
                terminerActivite(idUtilisateur);
            }else if(choix == 3){
                System.out.println(demandeSalaire(idUtilisateur));
            }
        }else{
            System.out.println("\n\n Erreur de connection \n\n");
        }
    }

    /**
    * Affiche et gère le menu d'administration une fois que la connexion est établie.
    *
    * Si la connexion est établie (indiquée par le paramètre 'connectionEtablie'), cette méthode
    * affiche le menu d'administration et exécute l'action choisie par l'administrateur. Les actions 
    * comprennent la modification du mot de passe de l'administrateur, l'assignation de projets à des 
    * employés, la modification du nombre maximal de projets en cours (NPE) d'un employé, la modification 
    * du nom d'un employé, et la modification de la date de départ d'un employé. Si d'autres choix sont 
    * effectués (choix 6 à 10), aucune action n'est encore définie. Si la connexion n'est pas établie, 
    * un message d'erreur de connexion est affiché.
    *
    * @param motPasse Le mot de passe actuel de l'administrateur, utilisé pour la modification du mot de passe.
    * @param connectionEtablie Un booléen indiquant si la connexion a été établie avec succès.
    */
    public void menuAdministrateurConnecter(String motPasse,boolean connectionEtablie){
        if(connectionEtablie){
            InterfaceMenu.menuAdminConnecter();
            int choix = InterfaceMenu.demandeChoix();
            if(choix == 1){
                modificationMotPasseAdministrateur(motPasse);
            }else if(choix == 2){
                assignerProjetAEmploye();
            }else if(choix == 3){
                modifierNPEEmploye();
            }else if(choix == 4){
                modifierNomEmploye();
            }else if(choix == 5){
                modifierDateDepartEmploye();
            }else if(choix == 6){
                modifierTauxBaseEmploye();
            }else if(choix == 7){
                modifierPosteEmploye();
            }else if(choix == 8){
                modifierNomProjet();
            }else if(choix == 9){
                modifierHeureBudgetProjet();
            }else if(choix == 10){
                modifierDateFinProjet();
            }
            System.out.println("\n");
        }else{
            System.out.println("\n\n Erreur de connection \n\n");
        }
    }

    public void modifierDateFinProjet() {
        System.out.println("Veuillez entrer le nom du projet");
        Projet projet = obtenirObjetProjetSelonNom(InterfaceMenu.demandeNomProjet());
        if(projet != null){
            Date dateFin = InterfaceMenu.demanderDate(new Scanner(System.in));
            if(dateFin.after(projet.getDateDebut())){
                projet.setDateFin(dateFin);
                System.out.println("Date de projet changer pour "+projet.getDateFin()+" pour "+projet.getNomProjet());
            }
        }else{
            System.out.println("\nErreur de projet.");
        }
    }

    /**
    * Modifie le nombre d'heures budgétaires pour un projet spécifié.
    * <p>
    * Cette méthode permet à l'utilisateur de modifier le budget en heures pour un projet existant.
    * Elle commence par demander à l'utilisateur de saisir le nom du projet. Si le projet est trouvé,
    * l'utilisateur est ensuite invité à saisir le nouveau nombre d'heures budgétaires pour ce projet.
    * Après la mise à jour, un message de confirmation est affiché, indiquant le nouveau nombre d'heures
    * budgétaires du projet.
    * </p>
    * 
    * 
    */
    public void modifierHeureBudgetProjet() {
        System.out.println("Veuillez entrer le nom du projet");
        Projet projet = obtenirObjetProjetSelonNom(InterfaceMenu.demandeNomProjet());
        if(projet != null){
            System.out.println("Veuillez entrer le nouveau nombre d'heure projet.");
            float nbHeure = InterfaceMenu.demandeNbHeureBudget();
            projet.setNbrHeuresBudgetDiscipline(nbHeure);
            System.out.println("Projet heure budgter: "+projet.getNbrHeuresBudgetDiscipline()+" pour "+projet.getNomProjet());
        }else{
            System.out.println("\nErreur de projet.");
        }
    }

    /**
    * Modifie le nom d'un projet existant.
    * 
    * Cette méthode permet à l'utilisateur de changer le nom d'un projet. L'utilisateur est d'abord invité
    * à entrer le nom actuel du projet. Si le projet est trouvé, l'utilisateur est ensuite invité à entrer
    * le nouveau nom pour ce projet. Si le changement est réussi, un message de confirmation est affiché.
    * Si le projet avec le nom spécifié n'existe pas, un message d'erreur est affiché.
    * 
    * 
    */
    public void modifierNomProjet() {
        System.out.println("Veuillez entrer le nom du projet");
        Projet projet = obtenirObjetProjetSelonNom(InterfaceMenu.demandeNomProjet());
        if(projet != null){
            System.out.println("Veuillez entrer le nouveau nom de projet.");
            String nouveauNom = InterfaceMenu.demandeNomProjet();
            projet.setNom(nouveauNom);
            System.out.println("\nNom de projet "+projet.getNomProjet()+" mis a jour.");
        }else{
            System.out.println("\nErreur de projet.");
        }
    }

    /**
    * Modifie le poste d'un employé.
    *
    * @throws NullPointerException si 'obtenirObjetEmployeSelonID' ne gère pas les cas où aucun employé n'est trouvé.
    */
    public void modifierPosteEmploye() {
        System.out.println("Veuillez entrer le ID de l'employe");
        int idEmploye = InterfaceMenu.demandeID();
        Employe employe = obtenirObjetEmployeSelonID(idEmploye);
        if(employe != null){
            String poste = InterfaceMenu.demandeDiscipline();
            employe.setPoste(poste);
            System.out.println("\n Poste modifier: "+employe.getPosteEmploye()+" pour "+employe);
        }else{
            System.out.println("Erreur de modification de poste.");
        }
    }

    /**
    * Modifie le taux de base d'un employé.
    *
    * Cette méthode interagit avec l'utilisateur pour obtenir l'ID de l'employé dont le taux de base doit être modifié.
    * Après avoir récupéré l'ID, elle recherche l'employé correspondant. Elle demande ensuite à l'utilisateur
    * de saisir le nouveau taux de base. Si le nouveau taux de base est positif, le taux de base de l'employé
    * est mis à jour avec cette nouvelle valeur et un message de confirmation est affiché. Si le nouveau taux 
    * de base n'est pas positif, un message d'erreur est affiché.
    *
    * @throws NullPointerException si aucun employé correspondant à l'ID fourni n'est trouvé.
    */
    public void modifierTauxBaseEmploye() {
        System.out.println("Veuillez entrer le ID de l'employe");
        int idEmploye = InterfaceMenu.demandeID();
        Employe employe = obtenirObjetEmployeSelonID(idEmploye);
        if(employe != null){
            double nouveautauxBase = InterfaceMenu.demandeTauxBaseEmploye();
            if(nouveautauxBase > 0){
                employe.setTauxBase(nouveautauxBase);
                System.out.println("Nouveau taux de: "+employe.getTauxBase()+" pour "+employe);
            }else{
                System.out.println("\nErreur de taux de base.");
            }
        }else{
            System.out.println("Erreur de modification de taux.");
        }
        
    }

    /**
    * Modifie la date de départ d'un employé.
    *
    * Cette méthode interagit avec l'utilisateur pour obtenir l'ID de l'employé dont la date de départ doit être modifiée.
    * Après avoir récupéré l'ID, elle recherche l'employé correspondant. Ensuite, elle demande à l'utilisateur
    * de saisir la nouvelle date de départ. La date de départ de l'employé est mise à jour si la nouvelle date est 
    * postérieure à la date d'embauche de l'employé. Dans le cas contraire, un message d'erreur est affiché.
    *
    * Si aucun employé correspondant à l'ID fourni n'est trouvé, ou si la date d'embauche de l'employé n'est pas 
    * définie, la méthode pourrait générer une NullPointerException. Il est recommandé d'ajouter une vérification
    * de nullité pour l'objet 'employe' et sa date d'embauche avant de procéder à la modification.
    */
    public void modifierDateDepartEmploye() {
        System.out.println("Veuillez entrer le ID de l'employe");
        int idEmploye = InterfaceMenu.demandeID();
        Employe employe = obtenirObjetEmployeSelonID(idEmploye);
        if(employe != null){
            System.out.println("Veuillez entrer la date de depart: ");
            Date dateDepart = InterfaceMenu.demanderDate(new Scanner(System.in));
            if(dateDepart.after(employe.getEmbauche())){
                employe.setDateDepart(dateDepart);
                System.out.println("\nDate de depart ajuster pour "+employe);
            }else{
                System.out.println("\nErreur de date.");
            }
        }else{
            System.out.println("Erreur de modification de date.");
        }
    }

    /**
    * Modifie le nom d'un employé.
    *
    * Cette méthode interagit avec l'utilisateur pour obtenir l'ID de l'employé dont le nom doit être modifié.
    * Après avoir récupéré l'ID, elle recherche l'employé correspondant. Ensuite, elle demande à l'utilisateur
    * de saisir le nouveau nom. Le nom de l'employé est alors mis à jour avec cette nouvelle valeur.
    * 
    * Si aucun employé correspondant à l'ID fourni n'est trouvé, la méthode pourrait générer une 
    * NullPointerException. Il est recommandé d'ajouter une vérification de nullité pour l'objet 'employe'
    * avant de procéder à la modification.
    */
    public void modifierNomEmploye() {
        System.out.println("Veuillez entrer le ID de l'employe");
        int idEmploye = InterfaceMenu.demandeID();
        Employe employe = obtenirObjetEmployeSelonID(idEmploye);
        if(employe != null){
            System.out.println("Veuillez entrer le nouveau nom: ");
            String nouveauNom = InterfaceMenu.demandeNomUtilisateur();
            employe.setNom(nouveauNom);
            System.out.println("Nom modifier: "+employe); 
        }else{
            System.out.println("Erreur de modification de nom.");
        }
        
    }

    /**
    * Modifie le nombre maximal de projets en cours (NPE) pour un employé spécifique.
    *
    * Cette méthode demande d'abord l'identifiant de l'employé, puis le nouveau nombre NPE à affecter.
    * Après avoir récupéré ces informations, elle recherche l'employé correspondant. Si le nouveau NPE 
    * est inférieur au nombre de projets actuellement assignés à l'employé, un message d'erreur est affiché,
    * indiquant que le nouveau NPE doit être supérieur au nombre de projets en cours. Si la condition est 
    * respectée, le NPE de l'employé est mis à jour avec la nouvelle valeur.
    *
    * Note: La méthode suppose que la méthode 'obtenirObjetEmployeSelonID' retourne un objet Employe valide
    * ou null si aucun employé correspondant n'est trouvé. Elle utilise également 'obtenirProjetsEnCoursParEmploye'
    * pour déterminer le nombre actuel de projets assignés à l'employé.
    */
    public void modifierNPEEmploye() {
        System.out.println("Veuillez entrer le ID de l'employe");
        int idEmploye = InterfaceMenu.demandeID(); 
        System.out.println("Veuillez entrer le nouveau nombre NPE: ");
        int nouveauNPE = InterfaceMenu.demandeChoix();
        Employe employe = obtenirObjetEmployeSelonID(idEmploye);
        if(employe != null){
            if(nouveauNPE < obtenirProjetsEnCoursParEmploye(employe).size() ){
                System.out.println("Le nouveau NPE doit etre plus grand que le nombre de projet associer a l'employer.");
            }else{
                System.out.println("\nNPE ajuster pour employer "+employe);
                employe.setNPE(nouveauNPE);
            }
        }else{
            System.out.println("Erreur de modification de NPE.");
        }
        
    }

    /**
    * Assigner un projet à un employé.
    *
    * Cette méthode interagit d'abord avec l'utilisateur pour obtenir l'ID de l'employé et le nom
    * du projet à assigner. Elle recherche ensuite l'employé et le projet correspondants. Si l'employé
    * peut être assigné à un nouveau projet (c'est-à-dire, si le nombre de projets en cours sur lesquels
    * l'employé travaille est inférieur à son nombre maximum de projets en cours autorisé), le projet 
    * est ajouté à sa liste. Sinon, un message d'erreur est affiché indiquant que l'employé ne peut 
    * plus être assigné à un nouveau projet.
    *
    * Note: La méthode suppose que les méthodes 'obtenirObjetEmployeSelonID' et 'obtenirObjetProjetSelonNom'
    * retournent des objets valides ou null si aucun objet correspondant n'est trouvé. De plus, 
    * 'obtenirProjetsEnCoursParEmploye' est utilisée pour obtenir la liste des projets actuels de l'employé.
    */
    public void assignerProjetAEmploye() {
        System.out.println("Veuillez entrer le ID de l'employe");
        int idEmploye = InterfaceMenu.demandeID(); 
        System.out.println("Veuillez entrer le ID du projet a assigner");
        String nomProjet = InterfaceMenu.demandeNomProjet();
        Projet projet = obtenirObjetProjetSelonNom(nomProjet);
        Employe employe = obtenirObjetEmployeSelonID(idEmploye);
        if(employe != null || projet != null){
            if(obtenirProjetsEnCoursParEmploye(employe).size() < employe.getNPE()){
                projet.ajouterEmployer(employe);
                System.out.println("\nProjet "+nomProjet+" ajouter a employe "+idEmploye);
            }else{
                System.out.println("\nL'employer ne peut plus etre assigner a un projet.");
            }
        }else{
            System.out.println("\nErreur d'assignation.");
        }   
    }


    /**
    * Modifie le mot de passe de l'administrateur.
    *
    * Cette méthode demande à l'utilisateur de saisir un nouveau mot de passe pour l'administrateur.
    * Une fois le nouveau mot de passe reçu, elle met à jour le mot de passe de l'administrateur avec
    * cette nouvelle valeur. La méthode utilise 'InterfaceMenu.demandeMotPasse()' pour obtenir le nouveau
    * mot de passe.
    */
    public void modificationMotPasseAdministrateur(String motPasse) {
        System.out.println("Veuillez entre votre nouveau mot passe: ");
        motPasse = InterfaceMenu.demandeMotPasse();
        administrateur.setMotPasse(motPasse);
        System.out.println("\nMot de passe Administrateur modifier.");
    }


    /**
    * Recherche et renvoie un objet Employe correspondant à l'identifiant utilisateur fourni.
    *
    * Cette méthode parcourt la liste des employés et renvoie l'objet Employe dont l'identifiant
    * correspond à celui passé en paramètre. Si aucun employé avec l'identifiant spécifié n'est 
    * trouvé dans la liste, la méthode renvoie null.
    *
    * @param idUtilisateur L'identifiant de l'employé à rechercher.
    * @return L'objet Employe correspondant à l'identifiant fourni, ou null si aucun employé
    *         correspondant n'est trouvé.
    */
    public Employe obtenirObjetEmployeSelonID(int idUtilisateur){
        for (Employe employe : listeEmployes) {
            if(employe.getIdEmploye() == idUtilisateur){
                return employe;
            }
        }
        return null;
    }

    /**
    * Recherche et renvoie un objet Projet correspondant au nom de projet fourni.
    *
    * Cette méthode parcourt la liste des projets et renvoie l'objet Projet dont le nom
    * correspond à celui passé en paramètre. Si aucun projet avec le nom spécifié n'est 
    * trouvé dans la liste, la méthode renvoie null.
    *
    * @param nomProjet Le nom du projet à rechercher.
    * @return L'objet Projet correspondant au nom fourni, ou null si aucun projet
    *         correspondant n'est trouvé.
    */
    public Projet obtenirObjetProjetSelonNom(String nomProjet){
        for (Projet projet : listeProjets) {
            if(projet.getNomProjet().equals(nomProjet)){
                return projet;
            }
        }
        return null;
    }

    public String demandeSalaire(int idUtilisateur){
        Date debut;
        Date fin; 
        Scanner scanner = new Scanner(System.in);
        debut = InterfaceMenu.demanderDate(scanner);
        fin = InterfaceMenu.demanderDate(scanner);
        long montant = obtenirSalaireEmployePourActivites(idUtilisateur, debut, fin);
        return "Salaire de :"+montant+" entre "+debut+" et "+" fin. ";
    }

    

    /**
    * Calcule le salaire total d'un employé pour les activités réalisées sur des projets
    * entre deux dates spécifiées.
    *
    * Cette méthode parcourt tous les projets. Pour chaque projet dont la période se situe
    * entre les dates de début et de fin spécifiées, elle calcule le total des heures travaillées
    * par l'employé (identifié par son ID) sur les activités de ce projet. Le salaire total est
    * la somme de ces heures travaillées. Il est important de noter que seuls les projets dont
    * la période complète se situe entre les dates fournies sont pris en compte.
    *
    * @param idUtilisateur L'identifiant de l'employé pour lequel le salaire est calculé.
    * @param debut La date de début de la période pour laquelle calculer le salaire.
    * @param fin La date de fin de cette période.
    * @return Le salaire total calculé comme la somme des heures travaillées sur les activités
    *         des projets qui se déroulent entièrement entre les dates spécifiées.
    */
    public long obtenirSalaireEmployePourActivites(int idUtilisateur,Date debut,Date fin){
        long montant =0;
        for (Projet projet : listeProjets) {
            if(projet.getDateFin() !=null){
                if(projet.getDateDebut().after(debut) && projet.getDateFin().before(fin)){
                for (Activite activite : projet.listeActivites) {
                    if(activite.getEmploye().getIdEmploye() == idUtilisateur){
                        montant += activite.calculerHeuresTravaillees();
                    }
                }
            }
            }
        }
        return montant;
    }

    /**
    * Vérifie si le mot de passe fourni correspond au mot de passe de l'administrateur.
    *
    * Cette méthode compare le mot de passe passé en paramètre avec celui de l'administrateur
    * stocké dans le système. Elle retourne true si les mots de passe correspondent, sinon false.
    * 
    * @param motPasse Le mot de passe à vérifier.
    * @param nomUtilisateur Le nom d'utilisateur de l'administrateur. Actuellement, ce paramètre
    *                       n'est pas utilisé dans la méthode.
    * @return true si le mot de passe correspond à celui de l'administrateur, false sinon.
    */
    public boolean administrateurVerificationMotDePasse(String motPasse,String nomUtilisateur){
        return administrateur.getMotDePasse().equals(motPasse);
    }
    

    /**
    * Obtient une liste de projets en cours sur lesquels un employé spécifié travaille.
    *
    * Cette méthode parcourt tous les projets et leurs activités respectives. Elle vérifie si 
    * une activité est attribuée à l'employé spécifié et si cette activité n'est pas encore terminée 
    * (c'est-à-dire que l'heure de fin est nulle). Si ces conditions sont remplies, le projet 
    * correspondant est ajouté à la liste des projets en cours de l'employé, en évitant les doublons.
    *
    * @param employe L'employé pour lequel les projets en cours sont recherchés.
    * @return Une ArrayList de {@link Projet}, représentant les projets sur lesquels l'employé 
    *         travaille actuellement. Cette liste peut être vide si aucun projet en cours 
    *         n'est trouvé pour cet employé.
    */
    public ArrayList<Projet> obtenirProjetsEnCoursParEmploye(Employe employe){
        ArrayList<Projet> projetsEmployes = new ArrayList<>();      
        for (Projet projet : listeProjets) {
            for (Activite activite : projet.listeActivites) {
                if (activite.getEmploye().equals(employe) && activite.getHeureFin() == null) {
                    if (!projetsEmployes.contains(projet)) {
                        projetsEmployes.add(projet);
                    }
                    break; 
                }
            }
        }
        return projetsEmployes;
    }
    
}
