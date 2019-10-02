package com.example.roomtestapp2.ui;

import android.os.Bundle;
import android.widget.EditText;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void autoSetEditText(EditText editText, String string){
        editText.setText(string);
    }
}
