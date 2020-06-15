package fr.robin.android.surtain_com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.util.DatabaseHelper;

public class FirstActivity extends AppCompatActivity {

    private static final int DELAI_AFFICHAGE = 5000;

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
        //
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
