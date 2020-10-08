package fr.robin.android.surtaincom.util;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import fr.robin.android.surtaincom.data.Article;
import fr.robin.android.surtaincom.data.Categorie;
import fr.robin.android.surtaincom.data.SiteClient;
import fr.robin.android.surtaincom.data.Utilisateur;

public class Synchronisation {
    private Context mContext;
    //Serveur gestion et client
    private String server_port;
    private SiteClient siteClient;

    /**
     * Récupère les informations
     * @param context
     * @param  _siteClient
     */
    public Synchronisation(Context context, SiteClient _siteClient) {
        //this.dbHelper = db;
        this.mContext = context;
        this.siteClient = _siteClient;
        this.server_port = "80";//prefs.getString(Constantes.PORT_SERVEUR, "8080");
    }

    /**
     *
     * @return
     */
    public boolean isDisponible(){
        boolean result = false;
        try {
            String data = getJSON("/wp-json/wp/v2/categories?_fields=id,name,description");
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     *  Liste des catégories
     * @throws IOException
     */
    public void getCategories() throws IOException {
        try {
            String data = getJSON("/wp-json/wp/v2/categories?_fields=id,name,description");
            Log.d("MAIRIE COM - Reception JSON", "Reception de la liste des catégories : " + data);
            if(data == null || data.length()==0){
                return;
            }
            JSONArray dataList = new JSONArray(data);
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject jsonObject = dataList.getJSONObject(i);
                jsonObject.remove("type");
                ObjectMapper mapper = new ObjectMapper();
                Categorie entity = mapper.readValue(jsonObject.toString(), Categorie.class);
                Log.w("MAIRIE COM - Reception Catégorie", entity.getName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("MAIRIE COM - Catégories Exception", e.getMessage());
        }
    }

    /**
     *  Liste des articles d'une catégorie
     * @throws IOException
     */
    public void getArticle(int idCategorie) throws IOException {
        try {
            String data = getJSON("/wp-json/wp/v2/posts?categories="+idCategorie+"&_fields=title");
            Log.d("MAIRIE COM - Reception JSON", "Reception de la liste des articles : " + data);
            if(data == null || data.length()==0){
                return;
            }
            JSONArray dataList = new JSONArray(data);
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject jsonObject = dataList.getJSONObject(i);
                //jsonObject.remove("type");
                ObjectMapper mapper = new ObjectMapper();
                Article entity = mapper.readValue(jsonObject.toString(), Article.class);
                Log.w("MAIRIE COM - Reception Article", entity.getTitle());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("MAIRIE COM - Articles Exception", e.getMessage());
        }
    }

    /**
     *  Liste des articles d'une catégorie
     * @throws IOException
     */
    public List<Article>  getArticles(int idCategorie) throws IOException {
        List<Article> entities = null;
        try {
            String data = getJSON("/wp-json/wp/v2/posts?categories="+idCategorie+"&_fields=title,content");
            Log.d("MAIRIE COM - Reception JSON", "Reception de la liste des articles : " + data);
            if(data == null || data.length()==0){
                return entities;
            }
            entities = new ArrayList<Article>();
            JSONArray dataList = new JSONArray(data);
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject jsonObject = dataList.getJSONObject(i);
                //jsonObject.remove("type");
                ObjectMapper mapper = new ObjectMapper();
                Article entity = mapper.readValue(jsonObject.toString(), Article.class);
                entity.setCategorie(idCategorie);
                entities.add(entity);
                Log.w("MAIRIE COM - Reception Article", entity.getTitle());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("MAIRIE COM - Articles Exception", e.getMessage());
        }
        return entities;
    }

    /**
     *   Liste des utilisateurs qui ont publié au moins une fois !
     * @throws IOException
     */
    public void getUtilisateurs() throws IOException {
        try {
            String data = getJSON("/wp-json/wp/v2/users?_fields=id,name,description");
            Log.d("MAIRIE COM - Reception JSON", "Reception de la liste des utilisateurs : " + data);
            if(data == null || data.length()==0){
                return;
            }
            JSONArray dataList = new JSONArray(data);
            for (int i = 0; i < dataList.length(); i++) {
                JSONObject jsonObject = dataList.getJSONObject(i);
                jsonObject.remove("type");
                ObjectMapper mapper = new ObjectMapper();
                Utilisateur entity = mapper.readValue(jsonObject.toString(), Utilisateur.class);
                Log.w("MAIRIE COM - Reception Utilisateur", entity.getName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("MAIRIE JSON Utilisateurs Exception", e.getMessage());
        }
    }


    /**
     *
     * @param url
     * @return
     */
    private String getJSON(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(this.siteClient.getUrl() + url);
            c = (HttpURLConnection) u.openConnection();
            String userpass = this.siteClient.getLogin()+":"+this.siteClient.getPassword();
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            c.setRequestProperty ("Authorization", basicAuth);
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();
            //Log.d("MAIRIE COM - getJSON", "Return status -> " + c.getResponseMessage());
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }
        } catch (Exception ex) {
            Log.e("MAIRIE COM - JSON IO exception", ex.getMessage());
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Log.e("MAIRIE COM Disconnect json url", ex.getMessage());
                }
            }
        }
        return null;
    }
}
