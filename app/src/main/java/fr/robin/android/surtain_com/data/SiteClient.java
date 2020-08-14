package fr.robin.android.surtain_com.data;

public class SiteClient {
    private String login;
    private String password;
    private String url;
    private int categorieAndroid = 0;

    public SiteClient(String _login, String _password,String _url,int _categorieAndroid){
        this.login = _login;
        this.password = _password;
        this.url = _url;
        this.categorieAndroid = _categorieAndroid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCategorieAndroid() {
        return categorieAndroid;
    }

    public void setCategorieAndroid(int categorieAndroid) {
        this.categorieAndroid = categorieAndroid;
    }
}
