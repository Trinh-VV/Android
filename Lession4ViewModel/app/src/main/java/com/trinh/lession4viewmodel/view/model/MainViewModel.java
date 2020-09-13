package com.trinh.lession4viewmodel.view.model;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> count = new MutableLiveData<>(0);
    private MutableLiveData<Boolean> isEN = new MutableLiveData<>(false);
    private MutableLiveData<String> firstName = new MutableLiveData<>("");
    private MutableLiveData<String> lastName = new MutableLiveData<>("");


    public void counting() {
        count.setValue(count.getValue() + 1);
    }

    public MutableLiveData<Integer> getCount() {
        return count;
    }

    public MediatorLiveData<String> getFullNameLiveData() {
        final MediatorLiveData<String> fullNameLD = new MediatorLiveData<>();
        Observer<Object> handler = new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                fullNameLD.setValue(isEN.getValue() ?
                        firstName.getValue() + " " + lastName.getValue() :
                        lastName.getValue() + " " + firstName.getValue());
            }

        };
        fullNameLD.addSource(isEN, handler);
        fullNameLD.addSource(firstName, handler);
        fullNameLD.addSource(lastName, handler);
        return fullNameLD;
    }
    //return fullName;

    public void changeEN(boolean value) {
        isEN.setValue(value);
    }

    public void setFirstName(String name) {
        firstName.setValue(name);
    }

    public void setLastName(String name) {
        firstName.setValue(name);
    }


}
