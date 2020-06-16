package com.muslim.taskmadarsoft.Java.viewmodels;

import android.app.Application;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.muslim.taskmadarsoft.Java.data.Infoo;
import com.muslim.taskmadarsoft.Java.data.InfooRepository;

import java.util.List;

public class InfooViewModel extends AndroidViewModel {
    private InfooRepository repository;
    private LiveData <List<Infoo>> allinfoo;

    public
    InfooViewModel(@NonNull Application application) {
        super(application);
        repository = new InfooRepository(application);
        allinfoo = repository.getAllInfoo();
    }

    public void insert(Infoo infoo) {
        repository.insert(infoo);
    }

    public void update(Infoo infoo) {
        repository.update(infoo);
    }

    public void delete(Infoo infoo) {
        repository.delete(infoo);
    }

    public void deleteAllinfoo() {
        repository.deleteAllinfoo();
    }

    public LiveData<List<Infoo>> getAllinfoo() {
        return allinfoo;
    }
}