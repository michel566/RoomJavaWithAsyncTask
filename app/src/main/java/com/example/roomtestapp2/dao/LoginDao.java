package com.example.roomtestapp2.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomtestapp2.entities.Login;

@Dao
public interface LoginDao {

    @Insert
    void insertLogin(Login login);

    @Query("SELECT COUNT(*) FROM login")
    int countRecords();

    @Query("SELECT * FROM login")
    Login findLogin();

    @Query("SELECT * FROM login")
    LiveData<Login> getLogin();

    @Query("DELETE FROM login WHERE idPermissionario = :idPerm")
    void deleteLogin(String idPerm);
}
