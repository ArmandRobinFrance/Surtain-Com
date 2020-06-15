package fr.robin.android.surtain_com.models.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import fr.robin.android.surtain_com.models.bo.Administration;
import fr.robin.android.surtain_com.models.tables.AdministrationTable;
import fr.robin.android.surtain_com.util.DatabaseHelper;


public class AdministrationDAO extends EntityDAO {

	/**
	 * Constructeur
	 */
	public AdministrationDAO(DatabaseHelper dbHelper){
		super(dbHelper, AdministrationTable.TABLE_NOM, AdministrationTable.TABLE_ID);
	}

	/**
	 * INSERT Adminsitration
	 */
	public void insert(Administration administration){
		Log.i("AdministrationDAO","> insert");
		ContentValues values = new ContentValues();
		// Libelle
		values.put(AdministrationTable.PARAM_LIBELLE, administration.getLibelle());
		// Valeur
		values.put(AdministrationTable.PARAM_VALEUR, administration.getValeur());
		// Insertion de l'objet en BDD via le content value
		insert(values);
	}

	/**
	 * UPDATE Administration
	 */
	public void update(Administration administration) {
		Log.d("AdministrationDAO", "> update");
		ContentValues values = new ContentValues();
		// Libelle
		values.put(AdministrationTable.PARAM_LIBELLE, administration.getLibelle());
		// Valeur
		values.put(AdministrationTable.PARAM_VALEUR, administration.getValeur());
		// Modification de l'objet en BDD via le content value
		update(values, String.valueOf(administration.getId()));
	}

	/**
	 * Se charge d'ajouter les objets résultant du curseur
	 * dans un tableau de un ou plusieurs objets du même type
	 */
	private ArrayList<Administration> cursorToAdministrations(Cursor c) {
		// Si la requête ne renvoie pas de résultat
		if (c.getCount() == 0) {
			return null;
		}
		ArrayList<Administration> administrations = new ArrayList<Administration>(c.getCount());
		c.moveToFirst();
		do {
			Administration entity = new Administration();
			entity.setId(c.getInt(c.getColumnIndex(AdministrationTable.TABLE_ID)));
			entity.setLibelle(c.getString(c.getColumnIndex(AdministrationTable.PARAM_LIBELLE)));
			entity.setValeur(c.getString(c.getColumnIndex(AdministrationTable.PARAM_VALEUR)));

			administrations.add(entity);
		} while (c.moveToNext());
		// Ferme le curseur pour libérer les ressources
		c.close();
		return administrations;
	}

	/**
	 * Select de tous les enregistrement de la table admisnistration
	 */
	public ArrayList<Administration> getAllAdministration(){
		ArrayList<Administration> administrations = null;
		Cursor c = null;
		try{
			String request = "";
			c = bdd.query(AdministrationTable.TABLE_NOM, new String[] {
							AdministrationTable.TABLE_ID,
							AdministrationTable.PARAM_LIBELLE,
							AdministrationTable.PARAM_VALEUR
					},
					request,
					null, null, null, AdministrationTable.TABLE_ID +" ASC");
			//
			administrations =  cursorToAdministrations(c);
		}catch(Exception e){
			Log.e("getAllAppareil()",e.getMessage());
		}finally {
			if(c!= null){
				//On ferme le cursor
				c.close();
			}
		}
		return administrations;
	}

	/**
	 * Select by id
	 */
	public Administration getAdministrationById(String _ID){
		Administration entity = null;
		Cursor c = null;
		try{
			c = bdd.rawQuery(AdministrationTable.TABLE_SELECT_ID, new String[]{_ID});
			ArrayList<Administration> administrations = cursorToAdministrations(c);
			if(administrations != null) {
				entity = administrations.get(0);
			}
		}catch(Exception e){
			Log.e("AdministrationDAO", e.getMessage());
		}finally {
			if(c!= null){
				//On ferme le cursor
				c.close();
			}
		}
		return entity;
	}

	/**
	 * Select by libelle
	 */
	public Administration getAdministrationByLibelle(String _libelle){
		Administration entity = null;
		Cursor c = null;
		try{
			c = bdd.rawQuery(AdministrationTable.TABLE_SELECT_LIBELLE, new String[]{_libelle});
			ArrayList<Administration> administrations = cursorToAdministrations(c);
			if(administrations != null) {
				entity = administrations.get(0);
			}
		}catch(Exception e){
			Log.e("AdministrationDAO", e.getMessage());
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
		deleteAll(AdministrationTable.TABLE_NOM);
	}
}
