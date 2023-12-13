import java.util.Date;

/*
 * Classe Projet :
 * Un projet doit comprendre une date de début, date de fin, 
 * nbre d’heures budgétées pour chacune des disciplines, un nomProjet et un numéro d’idProjetentification.
 */

public class Projet {
    private int idProjet;
    private String nomProjet;
    private Date dateDebut;
    private Date dateFin;
    private Discipline discipline;
    private double nbrHeuresBudgetDiscipline;
 


    // Constructeur de classe
    public Projet(int idProjet, String nomProjet, Date dateDebut, Date dateFin, Discipline discipline,
            double heuresBudgetDiscipline){
        this.idProjet = idProjet;
        this.nomProjet = nomProjet;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.discipline = discipline;
        this.nbrHeuresBudgetDiscipline = heuresBudgetDiscipline;
 
    }

    public int getidProjet() {
        return idProjet;
    }

    public void setidProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public String getnomProjet() {
        return nomProjet;
    }

    public void setnomProjet(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public double getHeuresBudgetDiscipline() {
        return nbrHeuresBudgetDiscipline;
    }

    public void setHeuresBudgetDesign1(double heuresBudgetDiscipline) {
        this.nbrHeuresBudgetDiscipline = heuresBudgetDiscipline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
}
