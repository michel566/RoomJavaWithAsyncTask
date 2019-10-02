package com.example.roomtestapp2.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roomtestapp2.dao.LoginRepository;
import com.example.roomtestapp2.entities.Login;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository repository;
    private Login loginData;
    private LiveData<Login> loginLiveData;
    private MutableLiveData<Login> resultLogin;

    public LoginViewModel(Application application) {
        super(application);
        repository = new LoginRepository(application);
        loginData = repository.getLoginData();
        loginLiveData = repository.getLoginLiveData();
        resultLogin = repository.getResultLogin();
    }

    Login getLoginData() {
        return loginData;
    }

    MutableLiveData<Login> getResultLogin() {
        return resultLogin;
    }

    LiveData<Login> getLoginLiveData() {
        return loginLiveData;
    }

    void insertLogin(Login login) {
        repository.insertLogin(login);
    }

    void findLogin() {
        repository.findLogin();
    }

    void deleteLogin(String idPerm) {
        repository.deleteLogin(idPerm);
    }

    int getCount() {
        return repository.getCount();
    }

}
