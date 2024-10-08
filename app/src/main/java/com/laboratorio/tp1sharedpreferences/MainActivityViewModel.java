package com.laboratorio.tp1sharedpreferences;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.laboratorio.tp1sharedpreferences.Model.Usuario;
import com.laboratorio.tp1sharedpreferences.Request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> mUsuario;
    private MutableLiveData<String> mError;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getMUsuario(){
        if(mUsuario==null){
            mUsuario=new MutableLiveData<>();
        }
        return mUsuario;
    }

    public LiveData<String> getMError(){
        if(mError==null){
            mError=new MutableLiveData<>();
        }
        return mError;
    }

    public void login(String mail, String password){
        Usuario usuario = ApiClient.login(getApplication(), mail, password);
        if(usuario!=null){
            mUsuario.postValue(usuario);
        }else{
            mError.postValue("No corresponde a un usuario");
        }
    }
}
