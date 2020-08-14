package fr.robin.android.surtain_com.data;

/**
 * Liste des catégories
 */
public class Categorie {
    //COMMUN
    public static String WORDPRESS_CATEGORIE_ANDROID = "ANDROID";
    //SERVEUR
    public static String WORDPRESS_CATEGORIE_CLES_PUBLIC = "CLES_PUBLIC";
    //CLIENT
    public static String WORDPRESS_CATEGORIE_APROPOS = "APROPOS";
    public static int WORDPRESS_CATEGORIE_APROPOS_ID = 1;

    private int id;
    private String name;
    private String description;

    public Categorie(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
