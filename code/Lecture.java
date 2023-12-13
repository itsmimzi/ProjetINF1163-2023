import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe Lecture.
 * Cette classe permet de faire la lecture d'un fichier JSON.
 *
 * @version 
 * @author 
 * @version 
 */
public class Lecture {
    private static final String EMP_FILE_PATH = "employesData.json";
    private static final String PROJ_FILE_PATH = "projetsData.json"; 
    private ObjectMapper objectMapper;

    /**
     * Constructeur de la classe Lecture.
     */
    public Lecture() {
        this.objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

	
    /**
     * Lit les données des employés à partir d'un fichier JSON.
     *
     * @return Une liste d'objets Employe.
     */
    public List<Employe> lireEmployes() {
        return lireListe(EMP_FILE_PATH, new TypeReference<List<Employe>>() {});
    }

    /**
     * Lit les données des projets à partir d'un fichier JSON.
     *
     * @return Une liste d'objets Projet.
     */
    public List<Projet> lireProjets() {
        return lireListe(PROJ_FILE_PATH, new TypeReference<List<Projet>>() {});
    }


	
   /******************************* METHODES UTILITAIRES ********************************************
   **************************************************************************************************/
	
    /**
     * Méthode générique pour lire une liste d'objets à partir d'un fichier JSON.
     *
     * @param filePath    Le chemin du fichier JSON.
     * @param targetType  Le type de référence pour la désérialisation.
     * @param <T>         Le type d'objet.
     * @return Une liste d'objets du type spécifié.
     */
    private <T> List<T> lireListe(String filePath, TypeReference<List<T>> targetType) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                return objectMapper.readValue(file, targetType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
	
}

	
    
