import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Ecriture {
    private static final String FILE_PATH = "employesData.json";

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

        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            fileWriter.write(jsonArray.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
