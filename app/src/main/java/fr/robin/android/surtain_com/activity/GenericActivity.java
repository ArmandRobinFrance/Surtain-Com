package fr.robin.android.surtain_com.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import fr.robin.android.surtain_com.dialog.QuitterDialog;
import fr.robin.android.surtain_com.util.Constantes;
import fr.robin.android.surtain_com.util.DatabaseHelper;


public abstract class GenericActivity extends AppCompatActivity {

    //Context de l'activité
    protected Context mContext;
    //Database Helper
    protected DatabaseHelper databaseHelper;
    //
    public static final String FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION = "FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION";
    private BaseActivityReceiver baseActivityReceiver = new BaseActivityReceiver();
    public static final IntentFilter INTENT_FILTER = createIntentFilter();

    private static IntentFilter createIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION);
        return filter;
    }

    protected void registerBaseActivityReceiver() {
        registerReceiver(baseActivityReceiver, INTENT_FILTER);
    }
    protected void unRegisterBaseActivityReceiver() {
        unregisterReceiver(baseActivityReceiver);
    }
    public class BaseActivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION)) {
                finish();
            }
        }
    }

    //Close
    public void closeAllActivities(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences(Constantes.SHARED_PREFERENCES_NAME, 0);
        preferences.edit().clear().commit();
        sendBroadcast(new Intent(FINISH_ALL_ACTIVITIES_ACTIVITY_ACTION));
    }

    //Quitter l'application
    protected void quitter(){
        QuitterDialog quitterDialog = QuitterDialog.newInstance();
        quitterDialog.setGenericActivity(this);
        quitterDialog.show(getSupportFragmentManager(), "dialog");
    }

    //Base de données
    public DatabaseHelper getHelper(){
        if(this.databaseHelper == null){
            this.databaseHelper = new DatabaseHelper(mContext);
            this.databaseHelper.openWritable();
        }
        return this.databaseHelper;
    }
}