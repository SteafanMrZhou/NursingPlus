package com.nursingplus.steafan.android.ui.fragment;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nursingplus.steafan.android.R;

/**
 * 音乐的播放列表
 * Created by Administrator on 2017/4/16 0016.
 */
public class AudioFargment extends BaseFragment {

    private WebView exerciseWebView;
    private TextView exerciseTextView;

    @Override
    public int getLayout() {
        return R.layout.fragment_audio_list;
    }

    @Override
    public void initView() {
        exerciseWebView = (WebView) findViewById(R.id.exrciseWebView);
        exerciseTextView = (TextView) findViewById(R.id.scrollReadText);
    }

    @Override
    public void initData() {
        exerciseWebView.getSettings().setJavaScriptEnabled(true);
        exerciseWebView.setWebViewClient(new WebViewClient());
        exerciseWebView.loadUrl("http://www.ycxk.ac.cn/appNp/exercise.html");
    }

    @Override
    public void initListener() {

    }
}
