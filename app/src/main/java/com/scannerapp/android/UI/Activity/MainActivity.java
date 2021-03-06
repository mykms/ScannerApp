package com.scannerapp.android.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.scannerapp.android.R;
import com.scannerapp.android.UI.Callback.HostActivityListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements HostActivityListener {
    private NavController navController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onMessage(@NotNull String message) {
        Snackbar.make(getWindow().getDecorView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onNavigateTo(int fragmentNavId, Bundle args) {
        navController.navigate(fragmentNavId, args);
    }
}
