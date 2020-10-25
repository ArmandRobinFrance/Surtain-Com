package fr.robin.android.surtaincom.ui.horizon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.util.List;

import fr.robin.android.surtaincom.R;
import fr.robin.android.surtaincom.data.Cache;
import fr.robin.android.surtaincom.models.bo.Article;
import fr.robin.android.surtaincom.ui.GenericFragment;

public class HorizonFragment extends GenericFragment {

    private HorizonViewModel viewModel;
    private ListView listeView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_horizon, container, false);

        //Lecture BDD
        this.databaseHelper = getHelper();
        List<Article> listeArticle = this.databaseHelper.selectArticles(Cache.siteClient.getCategorieAndroid(),"HS%");

        if(listeArticle != null) {
            listeView = (ListView) root.findViewById(R.id.historique_listview);
            HorizonAdapter adapter = new HorizonAdapter(this.getActivity(), listeArticle);
            listeView.setAdapter(adapter);
        }
        //
        return root;
    }
}
