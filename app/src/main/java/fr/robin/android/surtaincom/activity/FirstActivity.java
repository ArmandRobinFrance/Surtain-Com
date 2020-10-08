package fr.robin.android.surtaincom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.robin.android.surtaincom.R;
import fr.robin.android.surtaincom.data.Cache;
import fr.robin.android.surtaincom.util.SynchronisationTask;

public class FirstActivity extends GenericActivity {

    private static final int DELAI_AFFICHAGE = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //Date
        SimpleDateFormat dateFormatAnnee = new SimpleDateFormat("yyyy");
        Date aujourdhui = new Date();
        String annee = dateFormatAnnee.format(aujourdhui);
        TextView textViewAnnee = findViewById(R.id.textViewAnnee);
        textViewAnnee.setText("@Mairie "+annee);
        //Synchronisation WORDPRESS
        this.mContext =  this.getApplicationContext();
        new SynchronisationTask().execute(this,this.getHelper());
        //
        while(Cache.synchroniser == false) {
            Log.d("MAIRIE COM - First Activity", "En cours ...");
            try {
                //Thread.sleep(10);
            }catch(Exception e){
                Log.e("MAIRIE COM - First Activity", e.getMessage());
            }
        }
        if(Cache.synchroniser) {
            Log.d("MAIRIE COM - First Activity", "synchroniser");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        changerActivite();
                    }
                }, DELAI_AFFICHAGE);
        }
    }

    //Changer d'activiter
    private void changerActivite(){
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
        this.finish();
    }
}
