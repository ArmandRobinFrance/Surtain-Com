package fr.robin.android.surtain_com.ui.viescolaire;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import java.util.List;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.models.bo.Article;

public class VieScolaireAdapter extends ArrayAdapter<Article> {

    public VieScolaireAdapter(Context context, List<Article> Articles) {
            super(context, 0, Articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_horaire_row,parent, false);
            }
            //
            VieScolaireViewHolder viewHolder = (VieScolaireViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new VieScolaireViewHolder();
                viewHolder.titre = (TextView) convertView.findViewById(R.id.textViewTitre);
                viewHolder.corps = (TextView) convertView.findViewById(R.id.textViewCorps);
                convertView.setTag(viewHolder);
            }
            //Article
            Article article = getItem(position);
            //il ne reste plus qu'à remplir notre vue
            viewHolder.titre.setText(Html.fromHtml(article.getTitre(), HtmlCompat.FROM_HTML_MODE_LEGACY));
            viewHolder.corps.setText(Html.fromHtml(article.getCorps(),HtmlCompat.FROM_HTML_MODE_LEGACY));
            //
            return convertView;
    }
}
