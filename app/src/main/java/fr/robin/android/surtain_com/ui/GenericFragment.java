package fr.robin.android.surtain_com.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import fr.robin.android.surtain_com.R;
import fr.robin.android.surtain_com.ui.apropos.AproposViewModel;
import fr.robin.android.surtain_com.util.DatabaseHelper;

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
