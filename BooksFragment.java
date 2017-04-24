package com.nursingplus.steafan.android.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nursingplus.steafan.android.R;

/**
 * Created by Administrator on 2017/3/31 0031.
 */
public class BooksFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.books_item, container, false);
        return view;
    }
}
