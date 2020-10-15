package fr.robin.android.surtaincom.ui.urgences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UrgencesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UrgencesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("2020 @Copyright - Mairie de Surtainville");
    }

    public LiveData<String> getText() {
        return mText;
    }
}