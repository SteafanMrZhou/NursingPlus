package com.nursingplus.steafan.android.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nursingplus.steafan.android.R;

//编写教材详情展示界面的布局，可折叠式状态栏
public class BooksActivity extends AppCompatActivity {

//    加入菜单功能的成员变量

    public static final String BOOK_TEXT = "books_name";
    public static final String BOOK_IMAGE_ID = "books_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Intent intent = getIntent();
        String booksName = intent.getStringExtra(BOOK_TEXT);
        int booksImageId = intent.getIntExtra(BOOK_IMAGE_ID, 0);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        获取到折叠式菜单栏的图片，标题栏部分
        ImageView booksImageView = (ImageView) findViewById(R.id.books_image_view);
        //        获取到折叠式菜单栏中的详细内容部分
        TextView textView = (TextView) findViewById(R.id.books_jihu_first);
//      为章节设置点击事件，点击跳转到阅读界面
        TextView jihuFirst = (TextView) findViewById(R.id.books_jihu_first);
        jihuFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jihuFirstIntent = new Intent(BooksActivity.this, ReadBookActivity.class);
                startActivity(jihuFirstIntent);
            }
        });
        setSupportActionBar(toolBar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(booksName);
        Glide.with(this).load(booksImageId).into(booksImageView);
        //为折叠式菜单栏中的悬浮按钮添加点击事件(点击弹出添加的菜单功能)
//        FloatingActionButton zhedieFloatButton = (FloatingActionButton) findViewById(R.id.floatAddButton);
//        zhedieFloatButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                点击后即调用展示popupwindow的方法
//                showPopupWindowMethod();
//                AlertDialog.Builder addBooks = new AlertDialog.Builder(BooksActivity.this);
//                addBooks.setTitle("添加到我的书架");
//                addBooks.setMessage("亲，把基护添加到我的书架？");
//                addBooks.setCancelable(false);
//
//                addBooks.setPositiveButton("添加", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String firstBookName = "基础护理学(第五版)";
//                        Intent sendBookNameIntent = new Intent(BooksActivity.this,ReciveBookNameActivity.class);
////                        传递数据为键值对方式，第一个参数为键，用于在接收数据的活动中接收数据使用
//                        sendBookNameIntent.putExtra("BookNameData", firstBookName);
//                        startActivity(sendBookNameIntent);
//
//                    }
//                });
//                判断是否加入了书架功能，如果加入了书架则提示加入书架成功
//                if(isAddBookName = true){
//                    Toast.makeText(BooksActivity.this, "基护已添加到您的书架，开始学习吧！"
//                            , Toast.LENGTH_SHORT).show();
//                }
//                addBooks.setNegativeButton("不添加", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//
//                addBooks.show();
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

