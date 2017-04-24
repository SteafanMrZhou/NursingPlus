package com.nursingplus.steafan.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nursingplus.steafan.android.R;

/**
 * Created by Administrator on 2017/4/8 0008.
 */
public class ReciveBookNameActivity extends AppCompatActivity {
//    //    增加数据存储功能
//    private SharedPreferences preferences;
//    private SharedPreferences.Editor editor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recive_book_name_data_layout);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        //        获取SharedPreference对象
//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        点击按钮，接收传递过来的书名数据
         final TextView reciveBookNameText = (TextView) findViewById(R.id.reciveBookNameText);
         Button reciveBookNameButton = (Button)findViewById(R.id.reciveBookNameButton);
        reciveBookNameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent getBookNameIntent = getIntent();
                String getBookName = getBookNameIntent.getStringExtra("BookNameData");
//                让数据显示在TextView中
                reciveBookNameText.setText(getBookName);
//                获取键所对应的值,一开始不存在任何值，所以为false
//                boolean isRemember = preferences.getBoolean("BookNameData", false);
//                if(isRemember){
//                    String bookNameData = preferences.getString(getBookName,"");
//                    reciveBookNameText.setText(getBookName);
//                }else if(reciveBookNameButton.isClickable()){
//                    editor = preferences.edit();
//                    editor.putBoolean("BookNameData", true);
//                    editor.putString("GetBookName","");
//                }

//                if(reciveBookNameButton.isClickable()){
//                    editor.putBoolean("BookNameData", true);
//                    editor.putString("GetBookName", )
//                }
            }
        });
    }
}
