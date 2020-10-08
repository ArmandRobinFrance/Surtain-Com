package fr.robin.android.surtaincom.ui.viescolaire;

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

public class VieScolaireFragment extends GenericFragment {

    private VieScolaireViewModel vieScolaireViewModel;
    private ListView listeView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vie_scolaire, container, false);
        //Lecture BDD
        this.databaseHelper = getHelper();
        List<Article> listeArticle = null;//this.databaseHelper.selectArticle(Categorie.CATEGORIE_VIE_SCOLAIRE);
        if(listeArticle != null) {
            listeView = (ListView) root.findViewById(R.id.historique_listview);
            VieScolaireAdapter adapter = new VieScolaireAdapter(this.getActivity(), listeArticle);
            listeView.setAdapter(adapter);
        }
        return root;
    }
}
