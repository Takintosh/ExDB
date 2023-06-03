package com.example.exdb;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends OptionsMenuHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("ExDB - Mobile 1");
    }

}
