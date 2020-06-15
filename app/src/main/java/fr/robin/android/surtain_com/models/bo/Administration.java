package fr.robin.android.surtain_com.models.bo;

public class Administration {
    private int id;
    private String libelle;
    private String valeur;

    public Administration() {

    }

    public Administration(int id, String libelle, String valeur) {
        this.id = id;
        this.libelle = libelle;
        this.valeur = valeur;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
}
