package fr.robin.android.surtaincom.models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import fr.robin.android.surtaincom.models.bo.Article;
import fr.robin.android.surtaincom.models.tables.ArticleTable;
import fr.robin.android.surtaincom.util.DatabaseHelper;

public class ArticleDAO extends EntityDAO {

	/**
	 * Constructeur
	 */
	public ArticleDAO(DatabaseHelper dbHelper){
		super(dbHelper, ArticleTable.TABLE_NOM, ArticleTable.TABLE_ID);
	}

	/**
	 * INSERT Article
	 */
	public void insert(Article entity){
		Log.i("ArticleDAO","> insert");
		ContentValues values = new ContentValues();
		// Libelle
		values.put(ArticleTable.PARAM_LIBELLE, entity.getTitre());
		// Valeur
		values.put(ArticleTable.PARAM_VALEUR, "'" + entity.getCorps() + "'");
		// Catégorie
		values.put(ArticleTable.PARAM_CATEGORIE, entity.getCategorie());
		// Insertion de l'objet en BDD via le content value
		insert(values);
	}

	/**
	 * UPDATE Article
	 */
	public void update(Article entity) {
		Log.d("ArticleDAO", "> update");
		ContentValues values = new ContentValues();
		// Libelle
		values.put(ArticleTable.PARAM_LIBELLE, entity.getTitre());
		// Valeur
		values.put(ArticleTable.PARAM_VALEUR, entity.getCorps());
		// Categorie
		values.put(ArticleTable.PARAM_CATEGORIE, entity.getCategorie());
		// Modification de l'objet en BDD via le content value
		update(values, String.valueOf(entity.getId()));
	}

	/**
	 * Se charge d'ajouter les objets résultant du curseur
	 * dans un tableau de un ou plusieurs objets du même type
	 */
	private ArrayList<Article> cursorToEnties(Cursor c) {
		// Si la requête ne renvoie pas de résultat
		if (c.getCount() == 0) {
			return null;
		}
		ArrayList<Article> entities = new ArrayList<Article>(c.getCount());
		c.moveToFirst();
		do {
			Article entity = new Article();
			entity.setId(c.getInt(c.getColumnIndex(ArticleTable.TABLE_ID)));
			entity.setTitre(c.getString(c.getColumnIndex(ArticleTable.PARAM_LIBELLE)));
			entity.setCorps(c.getString(c.getColumnIndex(ArticleTable.PARAM_VALEUR)));
			entity.setCategorie(c.getInt(c.getColumnIndex(ArticleTable.PARAM_CATEGORIE)));
			entities.add(entity);
		} while (c.moveToNext());
		// Ferme le curseur pour libérer les ressources
		c.close();
		return entities;
	}

	/**
	 * Select de tous les enregistrement de la table
	 */
	public ArrayList<Article> getAll(){
		ArrayList<Article> entities = null;
		Cursor c = null;
		try{
			String request = "";
			c = bdd.query(ArticleTable.TABLE_NOM, new String[] {
							ArticleTable.TABLE_ID,
							ArticleTable.PARAM_LIBELLE,
							ArticleTable.PARAM_VALEUR,
							ArticleTable.PARAM_CATEGORIE
					},
					request,
					null, null, null, ArticleTable.TABLE_ID +" ASC");
			//
			entities =  cursorToEnties(c);
		}catch(Exception e){
			Log.e("ArticleDAO getAll()",e.getMessage());
		}finally {
			if(c!= null){
				//On ferme le cursor
				c.close();
			}
		}
		return entities;
	}

	/**
	 * Select by categorie
	 */
	public ArrayList<Article> getByCategorie(int _categorie){
		ArrayList<Article> entities = null;
		Cursor c = null;
		try{
			c = bdd.rawQuery(ArticleTable.TABLE_SELECT_CATEGORIE, new String[]{String.valueOf(_categorie)});
			entities = cursorToEnties(c);
		}catch(Exception e){
			Log.e("ArticleDAO", e.getMessage());
		}finally {
			if(c!= null){
				//On ferme le cursor
				c.close();
			}
		}
		return entities;
	}

	/**
	 * Select by categorie
	 */
	public ArrayList<Article> getByCategorieTitreLike(int _categorie,String _titre){
		ArrayList<Article> entities = null;
		Cursor c = null;
		try{
			c = bdd.rawQuery(ArticleTable.TABLE_SELECT_CATEGORIE_TITRE_LIKE, new String[]{String.valueOf(_categorie),_titre});
			entities = cursorToEnties(c);
		}catch(Exception e){
			Log.e("ArticleDAO", e.getMessage());
		}finally {
			if(c!= null){
				//On ferme le cursor
				c.close();
			}
		}
		return entities;
	}

	/**
	 * Select by categorie
	 */
	public Article getByCategorieTitre(int _categorie,String _titre){
		Article entity = null;
		Cursor c = null;
		try{
			c = bdd.rawQuery(ArticleTable.TABLE_SELECT_CATEGORIE_TITRE, new String[]{String.valueOf(_categorie),_titre});
			ArrayList<Article> entities = cursorToEnties(c);
			if(entities!= null) {
				entity = entities.get(0);
			}
		}catch(Exception e){
			Log.e("ArticleDAO", e.getMessage());
		}finally {
			if(c!= null){
				//On ferme le cursor
				c.close();
			}
		}
		return entity;
	}

	/**
	 *
	 */
	public void deleteAll(){
		deleteAll(ArticleTable.TABLE_NOM);
	}

	/**
	 *
	 */
	public void deleteAll(int categorie){
		deleteAll(ArticleTable.TABLE_NOM,categorie);
	}

	/**
	 * DELETE into BDD
	 */
	protected void deleteAll(String _TableId, int _categorie){
		bdd.delete(ArticleTable.TABLE_NOM, ArticleTable.PARAM_CATEGORIE +"="+String.valueOf(_categorie), null);
	}
}
