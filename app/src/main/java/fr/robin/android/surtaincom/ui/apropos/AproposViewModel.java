package fr.robin.android.surtaincom.ui.apropos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.robin.android.surtaincom.models.bo.Administration;
import fr.robin.android.surtaincom.util.DatabaseHelper;

public class AproposViewModel extends ViewModel {
    private DatabaseHelper databaseHelper;
    private MutableLiveData<String> mText;

    public AproposViewModel() {
       mText = new MutableLiveData<>();
    }

    public void init(DatabaseHelper databaseHelper){
        databaseHelper = databaseHelper;
        Administration version =  databaseHelper.selectAdministration("VERSION");
        mText.setValue(version.getValeur());
    }

    public LiveData<String> getText() {
        return mText;
    }
}