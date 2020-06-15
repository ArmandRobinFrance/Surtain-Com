package fr.robin.android.surtain_com.data;

public class SiteClient {
    private String loginPassword;
    private String url;

    public SiteClient(String _loginPassword,String _url){
        this.loginPassword = _loginPassword;
        this.url = _url;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
