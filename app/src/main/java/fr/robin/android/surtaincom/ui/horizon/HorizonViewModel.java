package fr.robin.android.surtaincom.ui.horizon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import fr.robin.android.surtaincom.models.bo.Administration;
import fr.robin.android.surtaincom.util.DatabaseHelper;

public class HorizonViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    public HorizonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("V00.00.00   @Copyright - Mairie de Surtainville");
    }

    /**
     *
     * @param databaseHelper
     */
    public void init(DatabaseHelper databaseHelper){
        databaseHelper = databaseHelper;
        Administration version =  databaseHelper.selectAdministration("VERSION");
        mText.setValue(version.getValeur());
    }

    public LiveData<String> getText() {
        return mText;
    }
}