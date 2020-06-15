package fr.robin.android.surtain_com.data;

public class Categorie {
    public static int WORDPRESS_CATEGORIE_ANDROID = 19;
    public static int WORDPRESS_CATEGORIE_NOTIFICATION = 5;
    public static int WORDPRESS_CATEGORIE_HORAIRE = 17;
    public static int CATEGORIE_VIE_SCOLAIRE = 18;
    public static int CATEGORIE_NOS_COMMERCE = 0;
    public static int CATEGORIE_ARRETES_MUNICIPAUX = 0;
    public static int CATEGORIE_NOS_PARTENAIRES = 0;
    public static int CATEGORIE_NOTIFICATION = 0;

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
