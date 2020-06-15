package fr.robin.android.surtain_com.ui.apropos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.robin.android.surtain_com.models.bo.Administration;
import fr.robin.android.surtain_com.util.DatabaseHelper;

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