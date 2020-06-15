package fr.robin.android.surtain_com.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.models.tables.AdministrationTable;
import fr.robin.android.surtain_com.models.tables.ArticleTable;


public class BaseSQLite extends SQLiteOpenHelper {

    /**
     * Context de la classe
     */
    private Context mContext;

    public BaseSQLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    // Lancer lorsque versionBDD ==0
    @Override
    public void onCreate(SQLiteDatabase db) {
       createTables(db);
    }

    /**
     *  Création de toutes les tables de la basse de données.
     *  Executé si la base n'existe pas déjà.
     */
    private void createTables(SQLiteDatabase db){
        int insertedRaws = 0;
        try {
            //ADMINISTRATION
            AdministrationTable administrationTable = new AdministrationTable();
            db.execSQL(administrationTable.CREATE_SQLLITE);
            //ARTICLE
            ArticleTable articleTable = new ArticleTable();
            db.execSQL(articleTable.CREATE_SQLLITE);
            // On tente d'insérer des données grâce au fichier sql embarqué (uniquement pour le DEV)
            insertedRaws = insertDataFromScript(db);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Log.i("MAIRIE COM", "Nous avons intégré " + insertedRaws + " lignes en base !");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Suppression des tables de la BDD
     * @param db
     */
    public void deleteTables(SQLiteDatabase db){
        AdministrationTable administrationTable = new AdministrationTable();
        Log.i("MAIRIE COM", "Suppression de la table " + administrationTable.TABLE_DELETE);
        db.execSQL(administrationTable.TABLE_DELETE);


    }

    /**
     * Insertion des données du script res/raw/data.sql dans la BDD
     */
    public int insertDataFromScript(SQLiteDatabase db) throws IOException {
        // Reseting Counter
        int result = 0;
        // Ouvrir le fichier sql
        InputStream insertsStream = mContext.getResources().openRawResource(R.raw.data);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));
        // Itérer à chaque ligne (assuming each insert has its own line and theres no other stuff)
        while (insertReader.ready()) {
            String insertStmt;
            insertStmt = insertReader.readLine();
            Log.i("MAIRIE COM", insertStmt);
            try {
                db.execSQL(insertStmt);
            }catch(Exception e){
                Log.e("MAIRIE COM", insertStmt);
                Log.e("MAIRIE COM",e.getMessage());
             }
            result++;
        }
        insertReader.close();
        // returning number of inserted rows
        return result;
    }
}