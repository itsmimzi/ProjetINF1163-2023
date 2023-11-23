import java.util.ArrayList;

public class Membre {

    private final int MAX_HEURES_PRES = 23;
    private final int MAX_HEURES_DISC = 17;
    private final int MAX_HEURES_PROJ = 23;
    private final int MAX_HEURES_REDA = 17;

    private boolean complet = false;
    private ArrayList<Activite> activitees;
    private String cycle;
    private String ordre;
    private String permis;
    private int heureTransferees;
    private String nom;
    private String prenom;
    private int sexe;

    private int heuresTotal = 0;
    private int heuresCombinees = 0;
    private int heuresPresentations = 0;
    private int heuresDiscussion = 0;
    private int heuresProjet = 0;
    private int heuresRedaction = 0;
    private ArrayList<String> erreurs = new ArrayList<>();
    private int nbErreurs = 0 ;

    public Membre(String arg1, ArrayList<Activite> arg2, int arg3,String arg4) {
        ordre = arg4;
        cycle = arg1;
        activitees = arg2;
        heureTransferees = arg3;
    }
    public Membre(String arg1, ArrayList<Activite> arg2, int arg3) {
        ordre = null;
        cycle = arg1;
        activitees = arg2;
        heureTransferees = arg3;
    }
    public Membre(String arg1, ArrayList<Activite> arg2,String arg3){
        cycle = arg1;
        activitees =arg2;
        ordre = arg3;
        heureTransferees = 0;
    }
    public  Membre(String arg1,ArrayList<Activite> arg2,String arg3,String arg4,String arg5,String arg6,int arg7){
        cycle = arg1;
        activitees =arg2;
        ordre = arg3;
        heureTransferees = 0;
        permis = arg4;
        prenom = arg5;
        nom = arg6;
        sexe = arg7;
    }
    public Membre(String arg1, ArrayList<Activite> arg2,String arg3,String arg4){
        cycle = arg1;
        activitees =arg2;
        ordre = arg3;
        heureTransferees = 0;
        permis = arg4;
    }
    public Membre(String arg1, ArrayList<Activite> arg2,String arg3,int arg4,String arg5){
        cycle = arg1;
        activitees =arg2;
        ordre = arg3;
        heureTransferees = arg4;
        permis = arg5;
    }
    public Membre(){
        cycle = "";
        activitees = new ArrayList<>();
        ordre = "";
        heureTransferees = 0;
    }

    public void setHeuresTotal(int heuresTotal) {this.heuresTotal = heuresTotal;}
    public void setComplet(Boolean arg1){
        complet = arg1;
    }

    public void setActivitees(ArrayList<Activite> arg1){ activitees = arg1;}

    public void setHeureTransferees(int arg1){ heureTransferees = arg1; }

    public int getSexe(){return sexe;}

    public String getNom() {return nom;}

    public String getPrenom() {return prenom;}

    public String getPermis() {return permis;}

    public String getOrdre(){return ordre;}
    public String getCycle() {
        return cycle;
    }

    public int getHeuresTotal() {
        return heuresTotal;
    }

    public int getHeureTransferees() {
        return heureTransferees;
    }

    public ArrayList<Activite> getActivitees() {

        return new ArrayList<>(activitees);
    }

    public ArrayList<String> getErreurs() {
        return erreurs;
    }

    public boolean isComplet() {
        return complet;
    }

    public int getHeuresCombinees() {
        return heuresCombinees;
    }

    public int getHeuresPresentations() {
        return heuresPresentations;
    }

    public int getHeuresDiscussion() {
        return heuresDiscussion;
    }

    public int getHeuresProjet() {
        return heuresProjet;
    }

    public int getHeuresRedaction() {
        return heuresRedaction;
    }

    public void ajouterHeuresCombinees (int arg1){

        int heuresAjoutees = arg1;

        heuresCombinees += heuresAjoutees;
    }

    public void ajouterHeuresPresentations (int arg1){

        int heuresAjoutees = arg1;

        heuresPresentations += heuresAjoutees;
        heuresTotal += heuresAjoutees;
        if( heuresPresentations > MAX_HEURES_PRES){ heuresPresentations = MAX_HEURES_PRES;}
    }

    public void ajouterHeuresDiscussion (int arg1){

        int heuresAjoutees = arg1;

        heuresDiscussion += heuresAjoutees;
        heuresTotal += heuresAjoutees;
        if( heuresDiscussion > MAX_HEURES_DISC){ heuresDiscussion = MAX_HEURES_DISC;}
    }

    public void ajouterHeuresProjet (int arg1){

        int heuresAjoutees = arg1;

        heuresProjet += heuresAjoutees;
        heuresTotal += heuresAjoutees;
        if( heuresProjet > MAX_HEURES_PROJ){ heuresProjet = MAX_HEURES_PROJ;}
    }

    public void ajouterHeuresRedaction (int arg1){

        int heuresAjoutees = arg1;

        heuresRedaction += heuresAjoutees;
        heuresTotal += heuresAjoutees;
        if( heuresRedaction > MAX_HEURES_REDA){ heuresRedaction = MAX_HEURES_REDA;}
    }

    public void ajouterErreur (String arg1){

        String messErreur = arg1;

        erreurs.add( messErreur );
    }

    public void setHeureTotal(int heuresTotal){
        this.heuresTotal = heuresTotal;
    }


}
