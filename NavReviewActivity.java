package com.nursingplus.steafan.android.ui.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;
import com.nursingplus.steafan.android.R;
import com.nursingplus.steafan.android.adapter.PlayerListAdapter;
import com.nursingplus.steafan.android.ui.fragment.AudioFargment;
import com.nursingplus.steafan.android.ui.fragment.BaseFragment;
import com.nursingplus.steafan.android.ui.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 设置复习布局，添加碎皮
 * Created by Administrator on 2017/4/13 0013.
 */
public class NavReviewActivity extends BaseActivity implements View.OnClickListener {
    private static final int TABS_VIDEO = 0;
    private static final int TABS_EXERCISE = 1;

    private ViewPager mReviewViewPager;
    private TextView reviewVideoTv;
    private TextView reviewExercise;
    private View reviewIndictor;

    @Override
    public int getLayout() {
        return R.layout.nav_review_layout;
    }

    @Override
    public void initView() {
//        复习界面ViewPager
        mReviewViewPager = (ViewPager)
                findViewById(R.id.review_vp);
        reviewIndictor = findViewById
                (R.id.review_indictor);
//        //        初始化指示器
//        WindowManager wm = (WindowManager)
        getSystemService(Context.WINDOW_SERVICE);
        reviewVideoTv = (TextView) findViewById
                (R.id.tv_review_title_video);
        reviewExercise = (TextView) findViewById
                (R.id.tv_review_title_exercise);
    }

    @Override
    public void initData() {
        //        初始化标题栏
        updateTabs(TABS_VIDEO);

        WindowManager wm = (WindowManager)
                getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay
                ().getWidth();
//        初始化指示器
        reviewIndictor.getLayoutParams().width =
                width / 2;
    }


    @Override
    public void initListener() {
//        调用适配器，加载播放列表，添加碎片
        List<BaseFragment> testList = new
                ArrayList<>();
        testList.add(new VideoFragment());
        testList.add(new AudioFargment());
        mReviewViewPager
                .setAdapter(new
                        PlayerListAdapter(getSupportFragmentManager(),
                        testList));


        //        设置点击事件
        reviewVideoTv.setOnClickListener(this);
        reviewExercise.setOnClickListener(this);

        //设置ViewPager滑动监听
        mReviewViewPager.setOnPageChangeListener
                (new PageChangeListener());
    }


    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_review_title_video:
                switchTabs(TABS_VIDEO);
                break;
            case R.id.tv_review_title_exercise:
                switchTabs(TABS_EXERCISE);
                break;
        }
    }

    private void switchTabs(int tab) {
        updateTabs(tab);
//        切换播放列表
        mReviewViewPager.setCurrentItem(tab);
    }

    /**
     * 播放列表ViewPager滑动监听
     */
    private class PageChangeListener implements
            ViewPager.OnPageChangeListener {
        //        触发onTouchEvent时调用,编写指示器的滑动效果
        @Override

        public void onPageScrolled(int position,
                                   float positionOffset, int positionOffsetPixels) {
            int width = reviewIndictor.getWidth
                    ();
//            起始位置
            int startX = position * width;
//            移动的位置
            float moveX = positionOffset * width;
//            最终的位置
            int finalX = (int) (startX + moveX);
            ViewHelper.setTranslationX
                    (reviewIndictor, finalX);
        }

        @Override
        public void onPageSelected(int position) {
            updateTabs(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 修改标题字体大小、颜色
     *
     * @param position
     */
    private void updateTabs(int position) {
        int tab = TABS_VIDEO;
        if (position == tab) {
//            视频播放列表
//            设置字体颜色
            reviewVideoTv.setTextColor(getResources().getColor(R.color.review_view));
            reviewExercise.setTextColor(getResources().getColor(R.color.review_half_white));
//            设置字体变大,通过动画来设置
            ViewPropertyAnimator.animate(reviewVideoTv).scaleX(1.2f);
            ViewPropertyAnimator.animate(reviewVideoTv).scaleY(1.2f);
            ViewPropertyAnimator.animate(reviewExercise).scaleX(1f);
            ViewPropertyAnimator.animate(reviewExercise).scaleY(1f);
        } else {
//            习题列表
            reviewVideoTv.setTextColor(getResources().getColor(R.color.review_half_white));
            reviewExercise.setTextColor(getResources().getColor(R.color.review_view));
            //            设置字体变大,通过动画来设置
            ViewPropertyAnimator.animate(reviewExercise).scaleX(1.2f);
            ViewPropertyAnimator.animate(reviewExercise).scaleY(1.2f);
            ViewPropertyAnimator.animate(reviewVideoTv).scaleX(1f);
            ViewPropertyAnimator.animate(reviewVideoTv).scaleY(1f);
        }
    }
}