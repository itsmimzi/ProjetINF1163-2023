import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationPodiatres {
    public static final String ORDRE_PODIATRES = "podiatres";
    public static final int HEURE_TOTALE_PODIATRES = 60;

    public static boolean validerOrdrePodiatres(String ordre){return ordre.equals(ORDRE_PODIATRES);}

    public static void validerPermisPodiatres(Membre membre){
        String regex = "[0-9]{5}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(membre.getPermis());
        if(!matcher.matches()){
            membre.ajouterErreur("Le permis est invalide, il doit contenir 5 chiffres");
        }
    }
}
