package fr.robin.android.surtain_com.ui.horaire;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.data.Categorie;
import fr.robin.android.surtain_com.models.bo.Article;
import fr.robin.android.surtain_com.ui.GenericFragment;

public class HoraireFragment extends GenericFragment {

    private HoraireViewModel horaireViewModel;
    private ListView listeView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_horaire, container, false);
        //Lecture BDD
        this.databaseHelper = getHelper();
        List<Article> listeArticle = this.databaseHelper.selectArticle(Categorie.WORDPRESS_CATEGORIE_HORAIRE);
        if(listeArticle != null) {
            listeView = (ListView) root.findViewById(R.id.historique_listview);
            HoraireAdapter adapter = new HoraireAdapter(this.getActivity(), listeArticle);
            listeView.setAdapter(adapter);
        }
        //
        return root;
    }
}
