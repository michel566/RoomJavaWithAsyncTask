package com.example.roomtestapp2.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.roomtestapp2.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        FrameLayout container = findViewById(R.id.container);
        managerFragmentTransaction(container.getId(), MainFragment.newInstance());
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        TextView tvToolbar = findViewById(R.id.tv_toolbar);
        tvToolbar.setText(getApplicationInfo().name);
    }

    public void managerFragmentTransaction(int idContainer, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.findFragmentById(idContainer) == null) {
            transaction.add(idContainer, fragment);
        } else {
            transaction.replace(idContainer, fragment);
        }
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sign_out:
                viewModel.deleteLogin(idPerm);
                goToSignInActivity(MainActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}