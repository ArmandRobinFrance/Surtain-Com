package fr.robin.android.surtaincom.ui.information;

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

public class InformationAdapter extends ArrayAdapter<Article> {

    public InformationAdapter(Context context, List<Article> Articles) {
            super(context, 0, Articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_information_row,parent, false);
            }
            //
            InformationViewHolder viewHolder = (InformationViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new InformationViewHolder();
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
            String url = (String)infos.get("ARTICLE");
            if(url != null){
                url = "";
            }
            //
            return convertView;
    }
}
