package fr.robin.android.surtaincom.ui.information;

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
import fr.robin.android.surtaincom.util.SynchronisationTask;

public class InformationFragment extends GenericFragment {

    private InformationViewModel viewModel;
    private ListView listeView;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_information, container, false);

        //Lecture BDD
        this.databaseHelper = getHelper();
        List<Article> listeArticle = this.databaseHelper.selectArticles(Cache.siteClient.getCategorieAndroid(),"INFO%");

        if(listeArticle != null) {
            listeView = (ListView) root.findViewById(R.id.historique_listview);
            InformationAdapter adapter = new InformationAdapter(this.getActivity(), listeArticle);
            listeView.setAdapter(adapter);
        }
        //
        return root;
    }
}
