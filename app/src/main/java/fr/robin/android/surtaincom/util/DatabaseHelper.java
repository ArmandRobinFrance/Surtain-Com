package fr.robin.android.surtaincom.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import fr.robin.android.surtaincom.data.Article;
import fr.robin.android.surtaincom.models.bo.Administration;
import fr.robin.android.surtaincom.models.dao.AdministrationDAO;
import fr.robin.android.surtaincom.models.dao.ArticleDAO;


public class DatabaseHelper {

    /**
     * Version de la BDD
     */
    private static final int VERSION_BDD = 1;

    /**
     * Nom de la BDD
     */
    private static final String NOM_BDD = "mairie_bdd1.db";

    /**
     * Objets représentant la BDD
     */
    private SQLiteDatabase bdd;
    private BaseSQLite maBaseSQLite;

    /**
     * Création de la BDD et de ses tables
     */
    public DatabaseHelper(Context context) {
        maBaseSQLite = new BaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    /**
     * Ouverture de la BDD en écriture
     */
    public void openWritable() {
        bdd = maBaseSQLite.getWritableDatabase();
    }

    /**
     * Ouverture de la BDD en lecture
     */
    public void openReadable() {
        bdd = maBaseSQLite.getReadableDatabase();
    }

    /**
     * Fermeture de la BDD
     */
    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    /**
     * Getter de la BDD
     */
    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public void createTables() {
        if (maBaseSQLite != null) {
            maBaseSQLite.onUpgrade(maBaseSQLite.getWritableDatabase(), maBaseSQLite.getWritableDatabase().getVersion(), VERSION_BDD);
        }
    }

    public void deleteTables() {
        if (maBaseSQLite != null) {
            maBaseSQLite.deleteTables(bdd);
        }
    }

    /**
     * Opérations sur la BDD liées à l'administration
     */
    public List<Administration> selectAllAdministration() {
        AdministrationDAO administrationDAO = new AdministrationDAO(this);
        return administrationDAO.getAllAdministration();
    }

    public Administration selectAdministration(String libelle) {
        AdministrationDAO administrationDAO = new AdministrationDAO(this);
        return administrationDAO.getAdministrationByLibelle(libelle);
    }

    public void insertArticle(Article data){
        ArticleDAO dao = new ArticleDAO(this);
        fr.robin.android.surtaincom.models.bo.Article entity = new fr.robin.android.surtaincom.models.bo.Article(1,data.getTitle(),data.getContent(),data.getCategories());
        dao.insert(entity);
    }

    /**
     * Tous les articles
     */
    public List<fr.robin.android.surtaincom.models.bo.Article> selectAllArticle() {
        ArticleDAO dao = new ArticleDAO(this);
        return dao.getAll();
    }

    /**
     * Tous les articles d'une catégorie
     */
    public List<fr.robin.android.surtaincom.models.bo.Article> selectArticle(int categorie) {
        ArticleDAO dao = new ArticleDAO(this);
        return dao.getByCategorie(categorie);
    }

    /**
     * Tous les articles d'une catégorie
     */
    public List<fr.robin.android.surtaincom.models.bo.Article> selectArticles(int categorie,String Titre) {
        ArticleDAO dao = new ArticleDAO(this);
        return dao.getByCategorieTitreLike(categorie,Titre);
    }

    /**
     * Tous les articles d'une catégorie
     */
    public fr.robin.android.surtaincom.models.bo.Article selectArticle(int categorie, String titre) {
        if(categorie == 0){
            Log.e("MAIRIE DatabaseHelper Exception", "Non synchronisé");
            return null;
        }
        ArticleDAO dao = new ArticleDAO(this);
        return dao.getByCategorieTitre(categorie,titre);
    }

    public void deleteArticles(){
        ArticleDAO dao = new ArticleDAO(this);
        dao.deleteAll();
    }

    public void deleteArticles(int categorie){
        ArticleDAO dao = new ArticleDAO(this);
        dao.deleteAll(categorie);
    }
}