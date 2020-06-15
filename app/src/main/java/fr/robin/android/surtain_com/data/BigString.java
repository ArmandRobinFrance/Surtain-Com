package fr.robin.android.surtain_com.data;

import com.google.gson.annotations.SerializedName;

public class BigString {
    private String rendered;

    @SerializedName("protected")
    public String myProtected;

    public String getRendered() {
        return rendered;
    }

    public void setRendered(String rendered) {
        this.rendered = rendered;
    }

    public String getProtected() {
        return myProtected;
    }

    public void setProtected(String myProtected) {
        this.myProtected = myProtected;
    }
}
