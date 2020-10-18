package fr.robin.android.surtaincom.ui.compteRendu;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import java.util.HashMap;
import java.util.List;

import fr.robin.android.surtaincom.R;
import fr.robin.android.surtaincom.models.bo.Article;
import fr.robin.android.surtaincom.util.Data;

public class CompteRenduAdapter extends ArrayAdapter<Article> {

    public CompteRenduAdapter(Context context, List<Article> Articles) {
            super(context, 0, Articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_compterendu_row,parent, false);
            }
            //
            CompteRenduViewHolder viewHolder = (CompteRenduViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new CompteRenduViewHolder();
                viewHolder.titre = (TextView) convertView.findViewById(R.id.textViewTitre);
                viewHolder.corps = (TextView) convertView.findViewById(R.id.textViewCorps);
                convertView.setTag(viewHolder);
            }
            //Article
            Article article = getItem(position);
            HashMap infos = Data.getData(article.getCorps());
            //il ne reste plus qu'à remplir notre vue
            viewHolder.titre.setText(Html.fromHtml((String)infos.get("TITRE"), HtmlCompat.FROM_HTML_MODE_LEGACY));
            viewHolder.corps.setText(Html.fromHtml((String)infos.get("CONTENU"),HtmlCompat.FROM_HTML_MODE_LEGACY));
            //
            return convertView;
    }
}
