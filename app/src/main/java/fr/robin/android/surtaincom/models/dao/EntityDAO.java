package fr.robin.android.surtaincom.models.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import fr.robin.android.surtaincom.util.DatabaseHelper;

public class EntityDAO {
    protected SQLiteDatabase bdd;
    private String table;
    private String tableDataID;

    /**
     * Constructeur
     */
    public EntityDAO(DatabaseHelper dbHelper, String _table, String _tableDataID) {
        this.bdd = dbHelper.getBDD();
        this.table = _table;
        this.tableDataID = _tableDataID;
    }

    /**
     * INSERT into BDD
     */
    protected long insert(ContentValues _values) {
        return bdd.insert(this.table,null,_values);
    }

    /**
     * UPDATE into BDD
     */
    protected void update(ContentValues _values, String _TableId) {
        bdd.update(this.table, _values,  this.tableDataID + "=?", new String[]{_TableId});
    }

    /**
     * DELETE into BDD
     */
    protected void delete(ContentValues _values, String _TableId){
        bdd.delete(this.table, this.tableDataID + "=?", new String[]{_TableId});
    }

    /**
     * DELETE into BDD
     */
    protected void deleteAll(String _TableId){
        bdd.delete(this.table, "", null);
    }


}