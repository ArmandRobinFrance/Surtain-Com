package fr.robin.android.surtaincom.ui;

import androidx.fragment.app.Fragment;

import fr.robin.android.surtaincom.util.DatabaseHelper;

public class GenericFragment extends Fragment {
    protected DatabaseHelper databaseHelper = null;

    public GenericFragment(){

    }

    //Base de données
    public DatabaseHelper getHelper(){
        if(this.databaseHelper == null){
            this.databaseHelper = new DatabaseHelper(this.getContext());
            this.databaseHelper.openWritable();
        }
        return this.databaseHelper;
    }
}
