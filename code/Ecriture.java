import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Ecriture {
    private static final String EMP_FILE_PATH = "employesData.json";
    private static final String PROJ_FILE_PATH = "projetsData.json";

    /**
     * Écrit les données des employés dans le fichier JSON correspondant.
     *
     * @param employes La liste d'objets Employe à écrire.
     */
    public void ecrireEmployes(List<Employe> employes) {
        JSONArray jsonArray = new JSONArray();
        
        for (Employe employe : employes) {
            JSONPObject jsonEmploye = new JSONObject();
            jsonEmploye.put("id", employe.getIdEmploye());
            jsonEmploye.put("nom", employe.getNomEmploye());
            jsonEmploye.put("TauxBase", employe.getTauxBase());
            jsonEmploye.put("TauxSupp", employe.getTauxSupp());
            jsonEmploye.put("dateEmbauche", employe.getEmbauche());
            jsonEmploye.put("dateDepart", employe.getDepart());
            jsonEmploye.put("NAS", employe.getNAS());
            jsonEmploye.put("poste", employe.getPosteEmploye());

            jsonArray.add(jsonEmploye);
        }

        try (FileWriter fileWriter = new FileWriter(EMP_FILE_PATH)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Écrit les données des projets dans le fichier JSON correspondant.
     *
     * @param projets La liste d'objets Projet à écrire.
     */
    public void ecrireProjets(List<Projet> projets) {
        JSONArray jsonArray = new JSONArray();
        
        for (Projet projet : projets) {
            JSONObject jsonProjet = new JSONObject();
            jsonProjet.put("idProjet", projet.getidProjet());
            jsonProjet.put("nomProjet", projet.getnomProjet());
            jsonProjet.put("dateDebut", projet.getDateDebut());
            jsonProjet.put("dateFin", projet.getDateFin());
            jsonProjet.put("discipline", projet.getDiscipline());
            jsonProjet.put("nbrHeuresBudgetDiscipline", projet.getHeuresBudgetDiscipline());

            jsonArray.add(jsonProjet);
        }

        try (FileWriter fileWriter = new FileWriter(PROJ_FILE_PATH)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
