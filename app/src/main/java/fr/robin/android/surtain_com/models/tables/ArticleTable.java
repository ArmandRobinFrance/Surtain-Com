package fr.robin.android.surtain_com.models.tables;

public class ArticleTable extends GenericTable {
    public static final String TABLE_NOM = "Article";


    public static final String TABLE_ID = "id_article";
    public static final String PARAM_LIBELLE = "libelle";
    public static final String PARAM_VALEUR = "valeur";
    public static final String PARAM_CATEGORIE = "categorie";

    public static final String CREATE_SQLLITE = "CREATE TABLE " + TABLE_NOM + " (" + TABLE_ID + " INTEGER PRIMARY KEY, " + PARAM_LIBELLE + " TEXT NOT NULL, " + PARAM_VALEUR + " TEXT NOT NULL," + PARAM_CATEGORIE + " TEXT NOT NULL);";
    public static String TABLE_SELECT_ID = "SELECT * FROM " + TABLE_NOM + " WHERE " + TABLE_ID + "=?";
    public static String TABLE_SELECT_LIBELLE = "SELECT * FROM " + TABLE_NOM + " WHERE " + PARAM_LIBELLE + "=?";
    public static String TABLE_SELECT_CATEGORIE = "SELECT * FROM " + TABLE_NOM + " WHERE " + PARAM_CATEGORIE + "=?";
    public static String TABLE_SELECT_CATEGORIE_TITRE = "SELECT * FROM " + TABLE_NOM + " WHERE " + PARAM_CATEGORIE + "=? AND " + PARAM_LIBELLE + "=?";
    public static String TABLE_INSERT = "INSERT INTO " + TABLE_NOM + " (" + PARAM_LIBELLE + "," + PARAM_VALEUR + ") VALUES (?,?)";
    public static String TABLE_UPDATE = "UPDATE " + TABLE_NOM + " SET  " + PARAM_LIBELLE + " =? ," + PARAM_VALEUR + " =? WHERE " + TABLE_ID + "=?";

    public ArticleTable() {
        super(TABLE_NOM, TABLE_ID);
    }
}