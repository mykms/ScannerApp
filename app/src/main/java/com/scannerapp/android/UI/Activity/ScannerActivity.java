package com.scannerapp.android.UI.Activity;

import android.os.Bundle;

import com.scannerapp.android.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScannerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
    }
}
