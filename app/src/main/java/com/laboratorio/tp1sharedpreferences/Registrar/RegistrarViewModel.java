package com.laboratorio.tp1sharedpreferences.Registrar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laboratorio.tp1sharedpreferences.Model.Usuario;
import com.laboratorio.tp1sharedpreferences.Request.ApiClient;

public class RegistrarViewModel extends AndroidViewModel {
    private MutableLiveData<String> mMsj;

    public RegistrarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMMsj(){
        if(mMsj==null){
            mMsj=new MutableLiveData<>();
        }
        return mMsj;
    }

    public void registrar(Usuario usuario){
        ApiClient.guardar(getApplication(), usuario);
        mMsj.postValue("Registro exitoso!!");
    }
}
