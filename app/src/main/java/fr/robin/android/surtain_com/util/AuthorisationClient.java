package fr.robin.android.surtain_com.util;

public class AuthorisationClient {
    public static String LOGIN_PASSWORD_SERVEUR_GESTION = "COM:1111";
    public static String URL_SERVEUR_GESTION = "http://www.lemaitre-robin.fr";
    private String serveur_client = "";

    /**
     * Lecture des URL possibles
     */
    public AuthorisationClient(){

    }

    /**
     *  URL pour le client à l'aide de la clés public
     *  La cle public est définit dans une liste déroulante de paramétrage
     *  Une cle pour chaque client
     *  C'est le controler qui autorise  les accces aux clients via la cles
     * @param cles
     * @return
     */
    public String getUrl(String cles){
        String url = "";
        //Essai
        try {
            url = "";
        }catch(Exception e){

        }
        return url;
    }
}
