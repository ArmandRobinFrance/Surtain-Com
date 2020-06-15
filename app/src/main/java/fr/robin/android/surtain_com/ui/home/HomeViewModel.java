package fr.robin.android.surtain_com.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("V01.00.00   @Copyright - Mairie de Surtainville");
    }

    public LiveData<String> getText() {
        return mText;
    }
}