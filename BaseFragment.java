package com.nursingplus.steafan.android.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nursingplus.steafan.android.ui.uiinterface;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment implements uiinterface {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(getLayout(), null);
        initView();
        initData();
        initListener();
        return view;
    }
//    加载fragment布局里的控件
    public View findViewById(int resId){
        return view.findViewById(resId);
    }
}
