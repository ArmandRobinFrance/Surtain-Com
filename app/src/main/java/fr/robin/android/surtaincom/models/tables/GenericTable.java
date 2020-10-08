package fr.robin.android.surtaincom.models.tables;

public class GenericTable {
    private String TABLE_NOM;
    private String TABLE_ID;
    public static final String PARAM_DATAID = "data_id";
    public static final String PARAM_DELETED = "data_deleted";
    public static final String PARAM_TIMESTAMP = "data_timestamp";
    public static final String PARAM_VERSION = "data_version";
    public String TABLE_DELETE;
    public String TABLE_DROP;
    public String TABLE_TRUNCATE;
    public String TABLE_SELECT_ALL;
    public String TABLE_SELECT_COUNT;
    public String TABLE_SELECT_ID;
    public String TABLE_SELECT_DATAID;
    public String TABLE_SELECT_LAST;

    public GenericTable(String _table_nom, String _table_id) {
        this.TABLE_NOM = _table_nom;
        this.TABLE_ID = _table_id;
        this.TABLE_DELETE = "DELETE FROM " + this.TABLE_NOM;
        this.TABLE_DROP = "DROP TABLE IF EXISTS " + this.TABLE_NOM;
        this.TABLE_TRUNCATE = "TRUNCATE TABLE IF EXISTS" + this.TABLE_NOM;
        this.TABLE_SELECT_ALL = "SELECT * FROM " + this.TABLE_NOM + " ORDER BY " + this.TABLE_ID;
        this.TABLE_SELECT_COUNT = "SELECT count(*) FROM " + this.TABLE_NOM;
        this.TABLE_SELECT_ID = "SELECT * FROM " + this.TABLE_NOM + " WHERE " + this.TABLE_ID + "=?";
        this.TABLE_SELECT_DATAID = "SELECT * FROM " + this.TABLE_NOM + " WHERE " + "data_id" + "=?";
        this.TABLE_SELECT_LAST = "SELECT MAX(" + _table_id + ") FROM " + _table_nom;
    }
}