package fr.robin.android.surtaincom.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =  ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        try {
            //DATA SERVEUR
            final TextView textViewPage1 = root.findViewById(R.id.home_page2);
            //TAG
            String tag = (String) textViewPage1.getText();
            //Par défaut
            textViewPage1.setText(getString (R. string.texte_vide));
            //
            while(!Cache.synchroniser){
                Thread.sleep(1000);
            }
            if(Cache.siteClient == null){
                return root;
            }
            Article articleAndroidHome = this.getHelper().selectArticle(Cache.siteClient.getCategorieAndroid(), tag);
            //
            if (articleAndroidHome != null && !articleAndroidHome.getCorps().isEmpty()) {
                String texte = Data.getDataCorp(articleAndroidHome.getCorps());
                textViewPage1.setText(texte);
            }
            //
            final TextView textView2 = root.findViewById(R.id.text_home);
            homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView2.setText(s);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
            Log.e("MAIRIE HomeFragment Exception", e.getMessage());
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
