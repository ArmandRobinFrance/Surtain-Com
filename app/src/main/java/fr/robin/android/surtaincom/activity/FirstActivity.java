package fr.robin.android.surtaincom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

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
        //Synchronisation WORDPRESS
        this.mContext =  this.getApplicationContext();
        new SynchronisationTask().execute(this,this.getHelper());
        //Date
        SimpleDateFormat dateFormatAnnee = new SimpleDateFormat("yyyy");
        Date aujourdhui = new Date();
        String annee = dateFormatAnnee.format(aujourdhui);
        TextView textViewAnnee = findViewById(R.id.textViewAnnee);
        textViewAnnee.setText("@Mairie "+annee);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                changerActivite();
            }
        }, DELAI_AFFICHAGE);
    }

    //Changer d'activiter
    private void changerActivite(){
        Intent loginIntent = new Intent(this, MainActivity.class);
        startActivity(loginIntent);
        this.finish();
    }
}
