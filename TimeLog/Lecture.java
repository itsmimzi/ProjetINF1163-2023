import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Lecture :
 * Cette classe permet de lire et écrire des données au format JSON en utilisant
 * la bibliothèque Jackson.
 * 
 * @author
 * @version
 */

public class Lecture {

    private static final String FILE_PATH = "employesData.json"; // Chemin du fichier où les données seront lues ou écrites.
    private ObjectMapper objectMapper; // ObjectMapper de Jackson pour sérialiser et désérialiser des objets Java en
                                       // JSON.

    /**
     * Constructeur de la classe Lecture.
     * Initialise l'objet ObjectMapper avec la fonctionnalité d'indentation pour une
     * meilleure lisibilité du JSON.
     */
    public Lecture() {
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Lit les données des employés à partir d'un fichier JSON.
     *
     * @return Une liste d'objets EmployeeData.
     */
    public List<Employe> lireEmployes() {
        try {
            File file = new File(FILE_PATH);
            // Vérifie si le fichier existe avant de tenter de le lire.
            if (file.exists()) {
                // Utilise Jackson pour désérialiser le fichier JSON en une liste d'employés.
                return objectMapper.readValue(file, new TypeReference<List<Employe>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Si une exception se produit ou si le fichier n'existe pas, retourne une liste vide.
        return new ArrayList<>();
    }

     /**
     * Écrit les données des employés dans un fichier JSON.
     *
     * @param employes La liste d'objets donneesEmployes à écrire.
     */
    public void ecrireEmployes(List<Employe> employes) {
        try {
            // Utilise Jackson pour sérialiser la liste d'employés et écrire le JSON dans le fichier.
            objectMapper.writeValue(new File(FILE_PATH), employes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
