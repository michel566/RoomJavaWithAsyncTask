package com.example.roomtestapp2.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomtestapp2.entities.Login;
import com.example.roomtestapp2.entities.Product;

@Database(entities = {Product.class, Login.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ProductDao productDao();

    public abstract LoginDao loginDao();

    private static AppDatabase INSTANCE;

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class,
                                    "product_database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }
}
