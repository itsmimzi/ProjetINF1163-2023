import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.io.IOUtils;


/**
 * Classe LectureEcriture.
 * Cette classe permet de faire la lecture d'un fichier JSON y
 * créer un membre et elle permet l'ecriture de ses erreurs
 * dans un fichier JSON.
 *
 * @version 11-11-2021
 * @author inf2050-a21-equipe2
 * @version 1.2
 */
public class Lecture {

	/**
	 * Permet d'obtenir les données de 'nomFichier.json'
	 * et contruit un objet membre avec les données obtenues.
	 *
	 * @param nomFichier le nom du fichier dont on veut obtenir les données
	 * @return un objet de type 'Membre'
	 */
	public static Membre lireFichierJson(String nomFichier) {
		Membre membre = null;
		try	{
			membre = convertirJSONMembreAffectation(nomFichier,membre);
		} catch (FileNotFoundException e) {
			System.err.println("Fichier introuvable");
		} catch (Exception e) {
			System.err.println("Le fichier " + nomFichier + " est invalide");
		}
		return membre;
	}

	/**
	 * Cette méthode va convertire va prendre en paramètre un nom de ficier json et un
	 * Membre et va créer un objet Membre sur lui-même.
	 * @param nomFichier le nom de fichier a créer l'obet json.
	 * @param membre le membre à affecter.
	 * @return
	 * @throws FileNotFoundException Si le fichier n'est pas trouver
	 * @throws IOException	pour la conversion du fichier json.
	 */
	public static Membre convertirJSONMembreAffectation(String nomFichier, Membre membre)
			throws FileNotFoundException,IOException{
		String stringFichierJSon = IOUtils.toString(new FileInputStream(nomFichier),
													StandardCharsets.UTF_8);
		JSONObject objetJson = (JSONObject) JSONSerializer.toJSON(stringFichierJSon);
		membre = creerObjetMembre(objetJson);
		return membre;
	}

	/**
	 * Permet de créer un objet Membre contenant le 'cycle', une 'liste
	 * d'activitée' et 'le nombre d'heures transférées du cycle précédent'.
	 *
	 * @param objetJson objet à partir duquel on extrait les données voulues
	 * @return un objet de type 'Membre'
	 */
	public static Membre creerObjetMembre(JSONObject objetJson) {
		Membre m = null;
		if(!Validation.trouverChampsInValideObjetJson(objetJson)){
			m = new Membre();
			System.err.println("Champs manquant dans le fichier Json.");
		}else if( objetJson.containsKey("ordre")){
			m = creerObjetMembreSelonOrdre(objetJson);
		}else {
			m = new Membre((String) objetJson.get("cycle"), creerListeActivitee(objetJson),
					(int) objetJson.get("heures_transferees_du_cycle_precedent"),(String)objetJson.get("numero_de_permis"));
		}
		return m;
	}

	public static Membre creerObjetMembreSelonOrdre(JSONObject objetJson){
		Membre membre;
		String ordre = (String) objetJson.get("ordre");
		if(ValidationArchitectes.validerOrdreArchitectes(ordre)){
			membre = creerObjetMembreArchitectes(objetJson);
		}else if(ValidationGeologue.validerOrdreGeologue(ordre)){
			membre = creerObjetMembreGeologue(objetJson);
		}else if(ValidationPsychologue.validerOrdrePsychologue(ordre)){
			membre = creerObjetMembrePsychologues(objetJson);
		}else if(ValidationPodiatres.validerOrdrePodiatres(ordre)){
			membre = creerObjetMembrePodiatres(objetJson);
		}
		else {
			membre = creerObjetMembreAvecOrdreSansHeureTransferees(objetJson);
			membre.ajouterErreur(membre.getOrdre()+" est une ordre professionnelle invalide.");
		}
		return membre;
	}

	public static Membre creerObjetMembreAvecOrdreSansHeureTransferees(JSONObject objetJson){
		Membre membre;
		membre = new Membre((String) objetJson.get("cycle"),creerListeActivitee(objetJson),
				(String) objetJson.get("ordre"),(String)objetJson.get("numero_de_permis"),
		(String)objetJson.get("prenom"),(String)objetJson.get("nom"),objetJson.getInt("sexe"));
		if(objetJson.containsKey("heures_transferees_du_cycle_precedent")){
			membre.ajouterErreur("Il ne devrais pas contenir de champs heure transferees dans le fichier JSON");
		}
		return  membre;
	}

	public static Membre creerObjetMembreArchitectes(JSONObject objetJson){
		Membre membre;
		if(!objetJson.containsKey("heures_transferees_du_cycle_precedent")){
			membre = new MembreArchitectes((String) objetJson.get("cycle"), creerListeActivitee(objetJson),
					0,(String) objetJson.get("ordre"));
			membre.ajouterErreur("Le champ heure transferees du cycle precedent est manquant. ");
		}else {
			membre = new Membre((String) objetJson.get("cycle"), creerListeActivitee(objetJson),
					(String) objetJson.get("ordre"),(int) objetJson.get("heures_transferees_du_cycle_precedent"),(String)objetJson.get("numero_de_permis"));
		}
		return membre;
	}

	public static Membre creerObjetMembreGeologue(JSONObject objetJson){
		return creerObjetMembreAvecOrdreSansHeureTransferees(objetJson);
	}

	public static Membre creerObjetMembrePsychologues(JSONObject objetJson){
		return creerObjetMembreAvecOrdreSansHeureTransferees(objetJson);
	}

	public static Membre creerObjetMembrePodiatres(JSONObject objetJson){
		return creerObjetMembreAvecOrdreSansHeureTransferees(objetJson);
	}


	/**
	 * Permet de créer une liste d'activitées en contruisant des objets de type
	 * 'Activitee' pour ensuite insérer chaque objet dans une ArrayList.
	 *
	 * @param objetJson objet à partir duquel on extrait les données voulues
	 * @return une ArrayList de type 'Activitee'
	 */
	public static ArrayList<Activite> creerListeActivitee(JSONObject objetJson) {
		ArrayList<Activite> listeDActivitee = new ArrayList<>();
		JSONArray activites = (JSONArray) JSONSerializer.toJSON(objetJson.get("activites"));
		for(int i=0;i<activites.size();i++) {
			listeDActivitee.add(creerObjetActivitee(activites.getJSONObject(i)));
		}
		return listeDActivitee;
	}

	/**
	 * Permet de créer un objet Activitee contenant la 'description' de
	 * l'activitée, la 'catégorie', le nombre 'd'heures' de formation effectuées
	 * et la 'date' qu'elles ont été effectuées.
	 *
	 * @param objetJson objet à partir duquel on extrait les données voulues
	 * @return un objet de type 'Activitee'
	 */

	public static Activite creerObjetActivitee(JSONObject objetJson)  {
		return new Activite((String) objetJson.get("description"),
				(String) objetJson.get("categorie"),
				(int) objetJson.get("heures"),
				(String)objetJson.get("date"));

	}


}
