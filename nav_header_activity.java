package com.nursingplus.steafan.android.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.nursingplus.steafan.android.R;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
public class nav_header_activity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        ActionBar navHeaderActionBar = getSupportActionBar();
        if(navHeaderActionBar != null){
            navHeaderActionBar.hide();
        }

    }
}
