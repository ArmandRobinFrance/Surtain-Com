package fr.robin.android.surtaincom.ui.horaire;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.util.List;

import fr.robin.android.surtaincom.R;
import fr.robin.android.surtaincom.models.bo.Article;
import fr.robin.android.surtaincom.ui.GenericFragment;

public class HoraireFragment extends GenericFragment {

    private HoraireViewModel horaireViewModel;
    private ListView listeView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_horaire, container, false);
        //Lecture BDD
        this.databaseHelper = getHelper();
        List<Article> listeArticle = null;//this.databaseHelper.selectArticle(Categorie.WORDPRESS_CATEGORIE_HORAIRE);
        if(listeArticle != null) {
            listeView = (ListView) root.findViewById(R.id.historique_listview);
            HoraireAdapter adapter = new HoraireAdapter(this.getActivity(), listeArticle);
            listeView.setAdapter(adapter);
        }
        //
        return root;
    }
}
