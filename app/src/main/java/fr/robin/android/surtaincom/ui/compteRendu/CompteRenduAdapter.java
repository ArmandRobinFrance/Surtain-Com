package fr.robin.android.surtaincom.ui.compteRendu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import java.util.HashMap;
import java.util.List;

import fr.robin.android.surtaincom.R;
import fr.robin.android.surtaincom.data.Cache;
import fr.robin.android.surtaincom.models.bo.Article;
import fr.robin.android.surtaincom.util.Data;

public class CompteRenduAdapter extends ArrayAdapter<Article> {

    public CompteRenduAdapter(Context context, List<Article> articles) {
            super(context, 0, articles);
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
                viewHolder.bouton = (Button) convertView.findViewById(R.id.button);
                convertView.setTag(viewHolder);
            }
            //Article
            Article article = getItem(position);
            HashMap infos = Data.getData(article.getCorps());
            //il ne reste plus qu'à remplir notre vue
            viewHolder.bouton.setText(Html.fromHtml((String)infos.get("TITRE"), HtmlCompat.FROM_HTML_MODE_LEGACY));
            //URL PDF
            String url = Cache.siteClient.getUrl()+(String)infos.get("CONTENU");
            //Bouton
            setOnClick( viewHolder.bouton,url);
            //
            return convertView;
    }

    private void setOnClick(final Button btn, final String url){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                Intent newIntent = Intent.createChooser(intent, "Open File");
                try {
                    v.getContext().startActivity(newIntent);
                } catch (ActivityNotFoundException e) {
                    // Instruct the user to install a PDF reader here, or something
                }
            }
        });
    }
}
