package fr.robin.android.surtaincom.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("2020 @Copyright - Mairie de Surtainville");
    }

    public LiveData<String> getText() {
        return mText;
    }
}