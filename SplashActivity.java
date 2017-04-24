package com.nursingplus.steafan.android.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.nursingplus.steafan.android.R;

/**
 * Created by Administrator on 2017/4/16 0016.
 * 闪屏页
 */
public class SplashActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
//        延迟3秒跳到主界面
        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
