import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import net.sf.json.JSONObject;

/**
 * Classe ECRITURE :
 *
 * Cette classe permet d'écrire des données au format JSON en utilisant la bibliothèque JSON-lib.
 */

public class Ecriture {

    private static final String EMP_FILE_PATH = "employesData.json";
    private static final String PROJ_FILE_PATH = "projetsData.json";
    //private static final String RAP_FILE_PATH = "rapports.json";
    private static final String TALON_PAIE_PATH = "./talonsPaieEmployes/talons_de_paie/";

    /**
     * Écrit les données des employés dans un fichier JSON.
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
     * Écrit les données des projets dans un fichier JSON.
     *
     * @param projets La liste d'objets Projet à écrire.
     */
    public void ecrireProjets(List<Projet> projets) {
        JSONArray jsonArray = new JSONArray();

        for (Projet projet : projets) {
            JSONObject jsonProjet = new JSONObject();
            jsonProjet.put("idProjet", projet.getIdProjet());
            jsonProjet.put("nomProjet", projet.getNomProjet());
            jsonProjet.put("dateDebut", projet.getDateDebut());
            jsonProjet.put("dateFin", projet.getDateFin());
            // jsonProjet.put("discipline", projet.getDiscipline());
            jsonProjet.put("nbrHeuresBudgetDiscipline", projet.getHeuresBudgetparDiscipline());

            jsonArray.add(jsonProjet);
        }

        try (FileWriter fileWriter = new FileWriter(PROJ_FILE_PATH)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   // public void ecrireRapport(List<Rapport> rapports){
   //     JSONArray jsonArray = new JSONArray();

   //     for (Rapport rapport : rapports){
   //         JSONObject jsonRapport = new JSONObject();
   //         jsonRapport.put("idRapport", rapport.getIdRapport());
   //         jsonRapport.put("Type", rapport.getTypeRapport());
   //         jsonRapport.put("Titre", rapport.getTitreRapport());
   //         jsonRapport.put("Corpus", rapport.getCorpsRapport());

   //         jsonarray.add(jsonRapport);
   //     }
   //     try (FileWriter fileWriter = new FileWriter(RAP_FILE_PATH)) {
   //         fileWriter.write(jsonArray.toString());
   //     } catch (IOException e) {
   //         e.printStackTrace();
   //     }
   // }

    /**
     * Écrire le talon de paie d'un employé dans un fichier JSON.
     *
     * @param employe L'employé pour lequel générer le talon de paie.
     */
    public void ecrireTalonDePaie(Employe employe) {

        JSONArray talonsDePaie = new JSONArray();
        String talonDePaie = employe.genererTalonDePaie();


        JSONObject jsonTalonDePaie = new JSONObject();
        jsonTalonDePaie.put("employeId", employe.getIdEmploye());
        jsonTalonDePaie.put("Nom : ", employe.getNomEmploye());
        jsonTalonDePaie.put("NAS : ", employe.getNAS());
        jsonTalonDePaie.put("talonDePaie : \n", talonDePaie);

        talonsDePaie.add(jsonTalonDePaie);

        String filePath = TALON_PAIE_PATH + "talon_de_paie_" + employe.getIdEmploye() + ".json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(talonsDePaie.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
