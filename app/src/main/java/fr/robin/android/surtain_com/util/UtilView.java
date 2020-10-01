package fr.robin.android.surtain_com.util;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.core.text.HtmlCompat;

import java.util.HashMap;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.data.Cache;

public class UtilView {

    /**
     *
     * @param root
     * @param infos
     * @param key
     */
    static public void updateTextView(View root, HashMap infos, int idView,String key){
        try {
            final TextView textView = root.findViewById(idView);
            if (textView != null && infos.containsKey(key)) {
                String str = Html.fromHtml((String) infos.get(key), HtmlCompat.FROM_HTML_MODE_LEGACY).toString();
                textView.setText(str);
                return;
            }
            //Cache
            if (textView != null && !infos.containsKey(key)) {
                textView.setVisibility(View.INVISIBLE);
                return;
            }
            //Cache
            if (textView != null && infos.containsKey(key) && ((String)infos.get(key)).isEmpty()) {
                textView.setVisibility(View.INVISIBLE);
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.e("MAIRIE updateTextView Exception", e.getMessage());
        }
    }

    /**
     *
     * @param root
     * @param infos
     * @param key
     */
    static public void updateAddTextView(View root, HashMap infos, int idView,String key){
        try {
            final TextView textView = root.findViewById(idView);
            if (infos.containsKey(key)) {
                String str = Html.fromHtml((String) infos.get(key), HtmlCompat.FROM_HTML_MODE_LEGACY).toString();
                textView.setText(textView.getText() + " " + str);
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.e("MAIRIE updateAddTextView Exception", e.getMessage());
        }
    }

}
