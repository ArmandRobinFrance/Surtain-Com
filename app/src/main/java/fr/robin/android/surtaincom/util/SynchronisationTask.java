package fr.robin.android.surtaincom.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fr.robin.android.surtaincom.data.Article;
import fr.robin.android.surtaincom.data.Cache;
import fr.robin.android.surtaincom.data.SiteClient;

public class SynchronisationTask extends AsyncTask<Object, Integer, Integer> {
        private DatabaseHelper databaseHelper;
        private Context context;
        //SERVEUR
        private Synchronisation synchroGestion = null;
        //CLIENT
        private Synchronisation synchroCLIENT = null;

        @Override
        protected Integer doInBackground(Object... params) {
            Log.d("MAIRIE COM - SynchronisationTask", "Start");
            // for debug worker thread
            if(android.os.Debug.isDebuggerConnected()) {
                android.os.Debug.waitForDebugger();
            }
            this.context = (Context) params[0];
            this.databaseHelper = (DatabaseHelper) params[1];
            try {
                    //On regarde en BDD s'il la configuration existe !
                    //...
                    //SERVEUR GESTION Pilote pour tous les clients
                    if (Cache.siteClient == null) {
                        SiteClient siteAuthorisation = new SiteClient("Lemaitre-robin", AuthorisationClient.LOGIN_SERVEUR_GESTION, AuthorisationClient.PASSWORD_SERVEUR_GESTION, AuthorisationClient.URL_SERVEUR_GESTION, AuthorisationClient.CATEGORIE_SERVEUR_ANDROID);
                        Synchronisation synchroGestion = new Synchronisation(context, siteAuthorisation);
                        lireArticle(synchroGestion, AuthorisationClient.CATEGORIE_SERVEUR_ANDROID);
                        fr.robin.android.surtaincom.models.bo.Article articleAndroidAuthorisation = this.databaseHelper.selectArticle(AuthorisationClient.CATEGORIE_SERVEUR_ANDROID, Cache.ANDROID_AUTHORISATION);
                        HashMap<String, SiteClient> listeClient = Data.getDataAuthorisation(articleAndroidAuthorisation.getCorps());
                        //Liste des clients
                        for (Map.Entry<String, SiteClient> entry : listeClient.entrySet()) {
                            String key = entry.getKey();
                            SiteClient value = entry.getValue();
                            Log.d("MAIRIE COM - SynchronisationTask client", key);
                        }
                        //URL pour le client
                        Cache.siteClient = listeClient.get("SURTAINVILLE");
                    }
                    //VERIFICATION
                    if (Cache.siteClient == null) {
                        //Cache.siteClient = new SiteClient("SURTAINVILLE",params );
                        return null;
                    }
                    //Acces DISTANT au site CLIENT ...
                    synchroCLIENT = new Synchronisation(context, Cache.siteClient);
                    //VERIFICATION
                    if (!synchroCLIENT.isDisponible()) {
                        Log.d("MAIRIE COM - SynchronisationTask client", "site " + Cache.siteClient.getNom() + " indisponible !");
                        return null;
                    } else {
                        Log.d("MAIRIE COM - SynchronisationTask client", "site " + Cache.siteClient.getNom() + " disponible.");
                    }
                    //Les articles web client de la catégorie ANDROID
                    lireArticle(synchroCLIENT, Cache.siteClient.getCategorieAndroid());
                    //Synchro ok
                    Cache.synchroniser = true;
                    Thread.sleep(30000);
                    while(true){
                        lireArticle(synchroCLIENT, Cache.siteClient.getCategorieAndroid());
                        Thread.sleep(30000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("MAIRIE JSON Exception", e.getMessage());
                } finally {
                    //databaseHelper.close();
                }
            Log.d("MAIRIE COM - SynchronisationTask", "fin");
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
                Iterator<Article> articles_iterator = articles.iterator();
                while (articles_iterator.hasNext()) {
                    databaseHelper.insertArticle(articles_iterator.next());
                }
            }else{
                Log.e("MAIRIE JSON Exception", "Acces impossible aux articles ...");
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
