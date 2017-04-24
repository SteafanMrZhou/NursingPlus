package com.nursingplus.steafan.android;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nursingplus.steafan.android.adapter.BooksAdapter;
import com.nursingplus.steafan.android.database.Books;
import com.nursingplus.steafan.android.ui.activity.BaseActivity;
import com.nursingplus.steafan.android.ui.activity.NavAboutActivity;
import com.nursingplus.steafan.android.ui.activity.NavMyAbilityActivity;
import com.nursingplus.steafan.android.ui.activity.NavMyNoteActivity;
import com.nursingplus.steafan.android.ui.activity.NavMyShelfActivity;
import com.nursingplus.steafan.android.ui.activity.NavReviewActivity;
import com.nursingplus.steafan.android.ui.activity.NavSettingsActivity;
import com.nursingplus.steafan.android.ui.activity.ReadBookActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Books[] bookses = {new Books("基础护理学第五版", R.drawable.jihu2),
            new Books("内科护理学第五版", R.drawable.neike)
            , new Books("外科护理学第五版", R.drawable.waike)
            , new Books("儿科护理学第五版", R.drawable.erke)
            , new Books("妇产科护理学第五版", R.drawable.fuchanke)
    };
    private String Content[] = {"1", "2", "3", "4", "5"};
    private List<Books> booksList = new ArrayList<>();
    private BooksAdapter adapter;
    //编写按钮响应的左侧边栏活动功能
    public DrawerLayout drawerLayout;//定义左滑动菜单的变量存储
    private Button navButton;//定义响应滑动菜单的按钮的变存储
    private NavigationView navView;
    private Button next_button;
    private RecyclerView recyclerView;


    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //为左侧边栏滑动菜单添加按钮响应事件
        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);
        navButton = (Button) findViewById(R.id.title_home);
        //        为菜单中的功能添加点击事件
        navView = (NavigationView) findViewById(R.id.nav_view);
        //在主界面添加下一步按钮响应事件
//        next_button = (Button) findViewById(R.id.title_go);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void initData() {
        //        加载主界面的方法
        initBooks();
//        主界面卡片式布局
        mainCardLayout();
    }

    private void mainCardLayout() {
        //        设置卡片式布局
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BooksAdapter(booksList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initListener() {
//        编写按钮响应打开左滑动侧边栏的逻辑
        navButton.setOnClickListener(this);
//        下一步按钮
//        next_button.setOnClickListener(this);
//        侧滑菜单的点击事件
        abilityOpenLeftDrawerLayoutClickListener();
    }

    //    点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            打开左滑动侧边栏的按钮
            case R.id.nav_view:
                abilityOpenLeftDrawerLayout();
                break;
//            下一步按钮
//            case R.id.title_go:
//                abilityNextButton();
//                break;
        }
    }

    /**
     * 分别设置侧滑菜单的点击事件
     */
    private void abilityOpenLeftDrawerLayoutClickListener() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.nav_bookShelf:
//                        abilityNavMyShelf();
//                        break;
//                    case R.id.nav_friends:
//                        abilityNavMyNote();
//                        break;
                    case R.id.nav_location:
                        abilityNavMyAbility();
                        break;
                    case R.id.nav_review:
                        abilityNavReview();
                        break;
//                    case R.id.nav_mail:
//                        abilityNavSettings();
//                        break;
                    case R.id.nav_task:
                        abilityNavAbout();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 侧滑菜单中我的书架的点击事件
     */
    private void abilityNavMyShelf() {
        Intent myShelfIntent = new Intent(MainActivity.this, NavMyShelfActivity.class);
        startActivity(myShelfIntent);
    }

    /**
     * 侧滑菜单中关于的点击事件
     */
    private void abilityNavAbout() {
        Intent navAboutIntent = new Intent(MainActivity.this, NavAboutActivity.class);
        startActivity(navAboutIntent);
    }

    /**
     * 侧滑菜单中设置的点击事件
     */
    private void abilityNavSettings() {
        Intent navSettingsIntent = new Intent(MainActivity.this, NavSettingsActivity.class);
        startActivity(navSettingsIntent);
    }

    /**
     * 侧滑菜单中复习的点击事件
     */
    private void abilityNavReview() {
        Intent navReviewIntent = new Intent(MainActivity.this, NavReviewActivity.class);
        startActivity(navReviewIntent);
    }

    /**
     * 侧滑菜单中我的能力的点击事件
     */
    private void abilityNavMyAbility() {
        Intent navMyAbilityIntent = new Intent(MainActivity.this, NavMyAbilityActivity.class);
        startActivity(navMyAbilityIntent);
    }

    /**
     * 侧滑菜单中我的笔记的点击事件
     */
    private void abilityNavMyNote() {
        Intent navNoteIntent = new Intent(MainActivity.this, NavMyNoteActivity.class);
        startActivity(navNoteIntent);
    }

    /**
     * 下一步按钮
     */
    private void abilityNextButton() {
        Intent next_intent = new Intent(MainActivity.this, ReadBookActivity.class);
        startActivity(next_intent);
    }
    /**
     * 打开侧滑菜单
     */
    private void abilityOpenLeftDrawerLayout() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    //初始化书籍的方法
    private void initBooks() {
        booksList.clear();
//        通过为ArrayList集合赋对应数组中元素的索引,来让其显示哪个元素,
//        注:ArrayList集合索引值从0开始,所以对应的数组索引值也必须要从0开始,否则会报数组元素越界异常
        booksList.add(bookses[0]);
        booksList.add(bookses[1]);
        booksList.add(bookses[2]);
        booksList.add(bookses[3]);
        booksList.add(bookses[4]);
    }
}