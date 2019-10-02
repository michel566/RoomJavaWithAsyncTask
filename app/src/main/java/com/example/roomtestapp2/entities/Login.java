package com.example.roomtestapp2.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Login {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "idPermissionario")
    private String idPermissionario;

    @ColumnInfo(name = "senha")
    private String senha;

    public Login(String idPermissionario, String senha) {
        this.idPermissionario = idPermissionario;
        this.senha = senha;
    }

    public String getIdPermissionario() {
        return idPermissionario;
    }

    public void setIdPermissionario(String idPermissionario) {
        this.idPermissionario = idPermissionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
