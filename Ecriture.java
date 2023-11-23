import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Ecriture {
    /**
     * Permet d'écrire les resulat des erreurs d'un Membre.
     * @param membre
     * @param fichier
     * @throws IOException
     */
    public static void ecrireJsonResultat(Membre membre, String fichier) throws IOException {
        JSONArray erreurs = new JSONArray();
        erreurs.addAll(membre.getErreurs());
        JSONObject resultat = new JSONObject()
                .accumulate("complet",membre.isComplet())
                .accumulate("erreurs",erreurs);
        System.out.println(resultat.toString());
        FileWriter ecriture = new FileWriter(fichier);
        ecriture.write(resultat.toString());
        ecriture.close();

    }
}
