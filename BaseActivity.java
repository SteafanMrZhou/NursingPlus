package com.nursingplus.steafan.android.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.nursingplus.steafan.android.ui.uiinterface;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public abstract class BaseActivity extends FragmentActivity implements uiinterface {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
        initListener();
//        初始化BmobSDK
//        Bmob.initialize(this, "ea1f2af3af8505422de548d75b6733db");
    }
}
