package fr.robin.android.surtain_com.models.bo;

public class Article {
    private int id;
    private String titre;
    private String corps;
    private int categorie;

    public Article() {

    }

    public Article(int id, String titre, String corps,int categorie) {
        this.id = id;
        this.titre = titre;
        this.corps = corps;
        this.categorie =  categorie;
    }

    public double getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
}
