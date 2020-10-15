package fr.robin.android.surtaincom.ui.urgences;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import fr.robin.android.surtaincom.R;
import fr.robin.android.surtaincom.data.Cache;
import fr.robin.android.surtaincom.models.bo.Article;
import fr.robin.android.surtaincom.util.Data;
import fr.robin.android.surtaincom.util.DatabaseHelper;

public class UrgencesFragment extends Fragment {

    private UrgencesViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel =  ViewModelProviders.of(this).get(UrgencesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_urgences, container, false);
        try {
            //DATA SERVEUR
            String tag = "PAGE2";
            WebView webView = (WebView)root.findViewById(R.id.urgence_webView);
            //
            Article articleAndroid = this.getHelper().selectArticle(Cache.siteClient.getCategorieAndroid(), tag);
            //
            if (articleAndroid != null) {
                webView.loadDataWithBaseURL(null, Data.getDataCorp(articleAndroid.getCorps()), "text/html", "utf-8", null);
            }else{
                webView.loadDataWithBaseURL(null, getString(R.string.texte_vide), "text/html", "utf-8", null);
            }
            webView.setBackgroundColor(0x01000000);
            //
        }catch(Exception e){
            e.printStackTrace();
            Log.e("MAIRIE UrgencesFragment Exception", e.getMessage());
        }
        return root;
    }

    //Base de données
    //Database Helper
    protected DatabaseHelper databaseHelper;
    public DatabaseHelper getHelper(){
        if(this.databaseHelper == null){
            this.databaseHelper = new DatabaseHelper(this.getContext());
            this.databaseHelper.openWritable();
        }
        return this.databaseHelper;
    }
}
