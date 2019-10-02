package com.example.roomtestapp2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomtestapp2.R;
import com.example.roomtestapp2.entities.Login;

public class SignUpActivity extends LoginActivity {

    private EditText edNewId, edNewPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edNewId = findViewById(R.id.ed_usernameSignUp);
        edNewPass = findViewById(R.id.ed_passSignUp);
        final Button btCadastrar = findViewById(R.id.cadastrar);
        Button btVoltar = findViewById(R.id.bt_sign_voltar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                autoSetEditText(edNewId, "123456");
                autoSetEditText(edNewPass, "123123");

                viewModel.insertLogin(new Login(
                        edNewId.getText().toString(),
                        edNewPass.getText().toString()
                ));

                Toast.makeText(SignUpActivity.this, "Id "
                        + edNewId.getText().toString() + " cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                goToMainActivity(SignUpActivity.this);
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
