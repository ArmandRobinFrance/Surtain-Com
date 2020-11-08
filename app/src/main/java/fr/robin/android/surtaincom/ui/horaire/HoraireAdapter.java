package fr.robin.android.surtaincom.ui.horaire;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import java.util.HashMap;
import java.util.List;

import fr.robin.android.surtaincom.R;
import fr.robin.android.surtaincom.data.Cache;
import fr.robin.android.surtaincom.models.bo.Article;
import fr.robin.android.surtaincom.util.Data;

public class HoraireAdapter extends ArrayAdapter<Article> {

    public HoraireAdapter(Context context, List<Article> Articles) {
            super(context, 0, Articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_horaire_row,parent, false);
            }
            //
            HoraireViewHolder viewHolder = (HoraireViewHolder) convertView.getTag();
            if(viewHolder == null){
                viewHolder = new HoraireViewHolder();
                viewHolder.titre = (TextView) convertView.findViewById(R.id.textViewTitre);
                viewHolder.corps = (Button) convertView.findViewById(R.id.buttonURL);
                convertView.setTag(viewHolder);
            }
            //Article
            Article article = getItem(position);
            HashMap infos = Data.getData(article.getCorps());
            String titre = (String)infos.get("TITRE");
            String contenu = (String)infos.get("CONTENU");
            String url = (String)infos.get("ARTICLE");
            String icone = (String)infos.get("ICONE");
            //il ne reste plus qu'à remplir notre vue
            if(titre != null) {
                viewHolder.titre.setText(Html.fromHtml(titre, HtmlCompat.FROM_HTML_MODE_LEGACY));
            }
        if(contenu != null) {
            viewHolder.corps.setText(Html.fromHtml(contenu, HtmlCompat.FROM_HTML_MODE_LEGACY));
        }
        //ARTICLE
        if(url != null) {
            viewHolder.corps.setTag(url);
            viewHolder.corps.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View arg0) {
                        String url = (String)arg0.getTag();
                        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                        getContext().startActivity(viewIntent);
                    }
                });
        }
        //ICONE
        if(icone != null) {
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            int id = getContext().getResources().getIdentifier(icone, "drawable", getContext().getPackageName());
            if(id !=0) {
                viewHolder.imageView.setImageResource(id);
            }
        }
        return convertView;
    }
}
