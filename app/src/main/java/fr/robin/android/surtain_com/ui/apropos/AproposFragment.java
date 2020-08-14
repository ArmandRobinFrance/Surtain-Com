package fr.robin.android.surtain_com.ui.apropos;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
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

import java.util.HashMap;
import java.util.List;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.data.Cache;
import fr.robin.android.surtain_com.data.Categorie;
import fr.robin.android.surtain_com.models.bo.Article;
import fr.robin.android.surtain_com.ui.GenericFragment;
import fr.robin.android.surtain_com.util.Constantes;
import fr.robin.android.surtain_com.util.Data;
import fr.robin.android.surtain_com.util.DatabaseHelper;
import fr.robin.android.surtain_com.util.UtilView;

import static android.content.Context.MODE_PRIVATE;

public class AproposFragment extends GenericFragment {

    private AproposViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(AproposViewModel.class);
        View root = inflater.inflate(R.layout.fragment_apropos, container, false);
        //Init BDD
        viewModel.init(getHelper());
        //Lecture BDD
        this.databaseHelper = getHelper();
        //
        if(Cache.siteClient == null){
            Log.d("MAIRIE COM - AproposFragment", "Site client inconnu !");
            return null;
        }
        //Lecture article
        Article articleAndroidAPropos = this.databaseHelper.selectArticle(Cache.siteClient.getCategorieAndroid(), Cache.ANDROID_APROPOS);
        if(articleAndroidAPropos == null) {
            Log.d("MAIRIE COM - AproposFragment", "Article inconnu !");
        }
        HashMap infos = Data.getData(articleAndroidAPropos.getCorps());
        if (infos == null) {
            Log.d("MAIRIE COM - AproposFragment", "Pas de données !");
        }
        //Région
        UtilView.updateAddTextView(root, infos, R.id.apropos_region, Cache.ANDROID_APROPOS_REGION);
        //Département
        UtilView.updateAddTextView(root, infos, R.id.apropos_departement, Cache.ANDROID_APROPOS_DEPARTEMENT);
        //Préfet
        UtilView.updateAddTextView(root, infos, R.id.apropos_prefet, Cache.ANDROID_APROPOS_PREFET);
        //Communauté
        UtilView.updateAddTextView(root, infos, R.id.apropos_communaute, Cache.ANDROID_APROPOS_COMMUNAUTE);
        //Canton
        UtilView.updateAddTextView(root, infos, R.id.apropos_canton, Cache.ANDROID_APROPOS_CANTON);
        //Maire
        UtilView.updateTextView(root, infos, R.id.apropos_maire, Cache.ANDROID_APROPOS_MAIRE);
        //1 adjoint
        UtilView.updateTextView(root, infos, R.id.apropos_1adjoint, Cache.ANDROID_APROPOS_1_ADJOINT);
        //2 adjoint
        UtilView.updateTextView(root, infos, R.id.apropos_2adjoint, Cache.ANDROID_APROPOS_2_ADJOINT);
        //3 adjoint
        UtilView.updateTextView(root, infos, R.id.apropos_3adjoint, Cache.ANDROID_APROPOS_3_ADJOINT);
        //4 adjoint (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_4adjoint, Cache.ANDROID_APROPOS_4_ADJOINT);
        //1 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_1commission, Cache.ANDROID_APROPOS_1_COMMISSION);
        //2 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_2commission, Cache.ANDROID_APROPOS_2_COMMISSION);
        //3 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_3commission, Cache.ANDROID_APROPOS_3_COMMISSION);
        //4 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_4commission, Cache.ANDROID_APROPOS_4_COMMISSION);
        //5 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_5commission, Cache.ANDROID_APROPOS_5_COMMISSION);
        //6 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_6commission, Cache.ANDROID_APROPOS_6_COMMISSION);
        //7 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_7commission, Cache.ANDROID_APROPOS_7_COMMISSION);
        //8 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_8commission, Cache.ANDROID_APROPOS_8_COMMISSION);
        //9 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_9commission, Cache.ANDROID_APROPOS_9_COMMISSION);
        //10 commission (Masque si pas de données)
        UtilView.updateTextView(root, infos, R.id.apropos_10commission, Cache.ANDROID_APROPOS_10_COMMISSION);

        //VERSION
        final TextView textView = root.findViewById(R.id.apropos_data_version);
        viewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
