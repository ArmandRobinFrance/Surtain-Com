package fr.robin.android.surtain_com.dialog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.activity.GenericActivity;
import fr.robin.android.surtain_com.util.Constantes;


public class QuitterDialog extends DialogFragment {

    //Activity
    private GenericActivity activity;

    //Bouton principale qui ferme l'application et déconnecte l'utilisateur
    private Button quitter;

    //Bouton secondaire qui ferme la boite de dialog, retournant à la navigation normale
    private ImageView annuler;

    /**
     * Constructeur de la boite de dialogue
     */
    public static QuitterDialog newInstance() {
        QuitterDialog fragment = new QuitterDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_quitter_app, container, false);
        this.quitter = v.findViewById(R.id.boutonQuitterApp);
        this.annuler = v.findViewById(R.id.boutonAnnulerQuitter);
        // On quitte l'application et on déconnecte l'utilisateur
        quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*SharedPreferences preferences = getActivity().getSharedPreferences(Constantes.SHARED_PREFERENCES_NAME, 0);
                preferences.edit().clear().commit();
                System.exit(0);
                dismiss();*/
                close();
            }
        });
        // On ferme juste la boite de dialog
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    //Close
    protected void close(){
        SharedPreferences preferences = getActivity().getSharedPreferences(Constantes.SHARED_PREFERENCES_NAME, 0);
        preferences.edit().clear().commit();
        //System.exit(0);
        //dismiss();
        this.activity.closeAllActivities(this.activity);
    }

    public void setGenericActivity(GenericActivity activity){
        this.activity = activity;
    }
}
