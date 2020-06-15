package fr.robin.android.surtain_com.data;

public class Article {
    private int id;
    private String author;
    private BigString title;
    private String date;
    private BigString content;
    private int categorie;

    public Article(){

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title.getRendered();
    }

    public void setTitle(BigString title) {
        this.title = title;
    }

    public String getContent() {
        return content.getRendered();
    }

    public void setContent(BigString content) {
        this.content = content;
    }

    public int getCategorie() {
        return categorie;
    }

    public void setCategorie(int categorie) {
        this.categorie = categorie;
    }
}
