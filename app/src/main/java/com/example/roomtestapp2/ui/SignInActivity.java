package com.example.roomtestapp2.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.roomtestapp2.R;
import com.example.roomtestapp2.entities.Login;

public class SignInActivity extends LoginActivity {

    private EditText edId, edPass;
    private Login mlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        edId = findViewById(R.id.username);
        edPass = findViewById(R.id.password);


        autoSetEditText(edId, "123456");
        autoSetEditText(edPass, "123123");
    }

    @Override
    protected void onResume() {
        super.onResume();
        observerSetup();
        listenerSetup();
    }

    private void listenerSetup() {
        final Button btSubmit = findViewById(R.id.login);
        Button btSignUp = findViewById(R.id.bt_sign_up);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btSubmit.setEnabled(false);
                authenticator(edId.getText().toString(), edPass.getText().toString());
                btSubmit.setEnabled(true);
            }
        });

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUpActivity(SignInActivity.this);
            }
        });
    }

    private void observerSetup() {
        viewModel.getLoginLiveData().observe(this, new Observer<Login>() {
            @Override
            public void onChanged(Login login) {
                viewModel.findLogin();
                mlogin = viewModel.getLoginData();
            }
        });

        viewModel.getResultLogin().observe(this, new Observer<Login>() {
            @Override
            public void onChanged(Login login) {
                if (login != null) {
                    mlogin = new Login(login.getIdPermissionario(),
                            login.getSenha());

                    loginOffline();
                }
            }
        });
    }

    private void authenticator(String id, String pass) {
        if (id.equals("") && pass.equals("")) {
            Toast.makeText(this, "Id ou senha vazios", Toast.LENGTH_LONG).show();
        } else {
            if (viewModel.getCount() != 0) {
                if (!mlogin.getIdPermissionario().equals(id)) {
                    Toast.makeText(this, "Id inexistente", Toast.LENGTH_LONG).show();
                } else if (!mlogin.getSenha().equals(pass)) {
                    Toast.makeText(this, "Senha inválida", Toast.LENGTH_LONG).show();
                } else {
                    idPerm = id;
                    goToMainActivity(SignInActivity.this);
                }
            } else {
                Toast.makeText(this, "Favor efetuar o cadastro no botão CADASTRAR", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void loginOffline() {
        if (viewModel.getCount() != 0) {
            if (mlogin != null) {
                if (mlogin.getIdPermissionario() != null) {
                    goToMainActivity(SignInActivity.this);
                    idPerm = mlogin.getIdPermissionario();
                    Log.d("loginOffline()", "flag static idPerm: " + idPerm);
                }
            }
        }
    }

}
