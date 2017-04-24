package com.nursingplus.steafan.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nursingplus.steafan.android.MainActivity;
import com.nursingplus.steafan.android.R;

/**
 * Created by Administrator on 2017/4/1 0001.
 */
public class second extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        Button back_button = (Button) findViewById(R.id.title_back);
        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            //编写返回按钮功能
            public void onClick(View v) {
                Intent back_intent = new Intent(second.this, MainActivity.class);
                startActivity(back_intent);
            }
        });
    }
}
