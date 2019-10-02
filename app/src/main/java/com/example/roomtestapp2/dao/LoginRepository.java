package com.example.roomtestapp2.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.roomtestapp2.entities.Login;

public class LoginRepository {

    private MutableLiveData<Login> resultLogin = new MutableLiveData<>();
    private LiveData<Login> loginLiveData;

    private Login loginData;
    private LoginDao loginDao;
    private static int count;

    public LoginRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getDatabase(application);
        loginDao = db.loginDao();
        loginLiveData = loginDao.getLogin();
        loginData = getLogin();
    }

    //Insert login
    public void insertLogin(Login login) {
        InsertAsyncTask task = new InsertAsyncTask(loginDao);
        task.execute(login);
    }

    private static class InsertAsyncTask extends AsyncTask<Login, Void, Void> {
        private LoginDao asyncTaskDao;

        InsertAsyncTask(LoginDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Login... params) {
            asyncTaskDao.insertLogin(params[0]);
            return null;
        }
    }

    //Delete login to id
    public void deleteLogin() {
        DeleteAsyncTask task = new DeleteAsyncTask(loginDao);
        task.execute();
    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {
        private LoginDao asyncTaskDao;

        DeleteAsyncTask(LoginDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskDao.deleteLogin();
            return null;
        }
    }

    //Query and get Login
    public void findLogin() {
        QueryAsyncTask task = new QueryAsyncTask(loginDao);
        task.delegate = this;
        task.execute();
    }

    private Login getLogin() {
        QueryAsyncTask task = new QueryAsyncTask(loginDao);
        task.delegate = this;
        task.execute();
        return getLoginData();
    }

    public MutableLiveData<Login> getResultLogin() {
        return resultLogin;
    }

    public LiveData<Login> getLoginLiveData() {
        return loginLiveData;
    }

    public Login getLoginData() {
        return loginData;
    }

    private void setLoginData(Login login) {
        this.loginData = login;
    }

    public int getCount() {
        return count;
    }

    private void asyncFinished(Login result) {
        resultLogin.setValue(result);
        setLoginData(result);
    }

    private static class QueryAsyncTask extends AsyncTask<Void, Void, Login> {
        private LoginDao asyncTaskDao;
        private LoginRepository delegate = null;

        QueryAsyncTask(LoginDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Login doInBackground(final Void... voids) {
            count = asyncTaskDao.countRecords();
            return asyncTaskDao.findLogin();
        }

        @Override
        protected void onPostExecute(Login login) {
            delegate.asyncFinished(login);
        }
    }

}
