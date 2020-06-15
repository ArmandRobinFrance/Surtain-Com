package fr.robin.android.surtain_com.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import fr.robin.android.surtain_com.data.Article;
import fr.robin.android.surtain_com.data.Cache;
import fr.robin.android.surtain_com.data.Categorie;
import fr.robin.android.surtain_com.data.SiteClient;

import static android.content.Context.MODE_PRIVATE;

public class SynchronisationTask extends AsyncTask<Object, Integer, Integer> {
        private DatabaseHelper databaseHelper;
        private Context context;
        //SERVEUR
        private Synchronisation synchroGestion = null;
        //CLIENT
        private Synchronisation synchroCLIENT = null;
        private SiteClient siteClient = null;

        @Override
        protected Integer doInBackground(Object... params) {
            // for debug worker thread
            if(android.os.Debug.isDebuggerConnected()) {
                android.os.Debug.waitForDebugger();
            }
            Log.v("MAIRIE COM - SynchronisationTask", "Start");
            this.context = (Context) params[0];
            this.databaseHelper = (DatabaseHelper) params[1];
            try {
                //On regarde en BDD s'il la configuration existe !
                //...
                //SERVEUR GESTION Pilote pour tous les clients
                if(siteClient == null) {
                    SiteClient siteAuthorisation = new SiteClient(AuthorisationClient.LOGIN_PASSWORD_SERVEUR_GESTION,AuthorisationClient.URL_SERVEUR_GESTION);
                    Synchronisation synchroGestion = new Synchronisation(context,siteAuthorisation);
                    lireArticle(synchroGestion, Categorie.WORDPRESS_CATEGORIE_ANDROID);
                    fr.robin.android.surtain_com.models.bo.Article articleAndroidAuthorisation = this.databaseHelper.selectArticle(Categorie.WORDPRESS_CATEGORIE_ANDROID, Cache.ANDROID_AUTHORISATION);
                    HashMap<String, SiteClient> listeClient = Data.getDataAuthorisation(articleAndroidAuthorisation.getCorps());
                    //JURL pour le client
                    siteClient = listeClient.get("SURTAINVILLE");
                }
                //VERIFICTAION
                if(siteClient == null){
                    return null;
                }
                //Acces DISTANT au site CLIENT ...
                synchroCLIENT = new Synchronisation(context, siteClient);
                //CLIENT : Liste des catégories
                synchroCLIENT.getCategories();
                //Liste des utilisateurs
                //synchroCLIENT.getUtilisateurs();
                //Liste des articles catégorie notification
                //lireArticle(syncro, Categorie.WORDPRESS_CATEGORIE_NOTIFICATION);
                //Liste ARTICLES HORAIRE
                lireArticle(synchroCLIENT, Categorie.WORDPRESS_CATEGORIE_HORAIRE);
                //Liste ARTICLES Vie Scolaire
                lireArticle(synchroCLIENT, Categorie.CATEGORIE_VIE_SCOLAIRE);
                //
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace();
                Log.e("MAIRIE JSON Exception", e.getMessage());
            }finally {
                //databaseHelper.close();
            }
            Log.v("MAIRIE COM - SynchronisationTask", "fin");
            return null;
        }

    /**
     * Lire article sur le web
     * Enregistrement en base;
     */
    private void lireArticle(Synchronisation syncro,int categorie) {
        try {
            databaseHelper.openWritable();
            List<Article> articles = syncro.getArticles(categorie);
            if (articles != null && articles.size() > 0) {
                databaseHelper.deleteArticles(categorie);
            }
            Iterator<Article> articles_iterator = articles.iterator();
            while (articles_iterator.hasNext()) {
                databaseHelper.insertArticle(articles_iterator.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MAIRIE JSON Exception", e.getMessage());
        }finally {
            //databaseHelper.close();
        }
    }

        protected void onPostExecute(String result) {
            Toast toast = Toast.makeText(this.context, "Synchronisation terminée.", Toast.LENGTH_SHORT);
            toast.show();
        }

}
