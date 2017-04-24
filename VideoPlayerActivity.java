package com.nursingplus.steafan.android.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nursingplus.steafan.android.R;
import com.nursingplus.steafan.android.bean.VideoBean;
import com.nursingplus.steafan.android.utlis.StringUtils;
import com.nursingplus.steafan.android.view.VideoView;

import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;

//import android.widget.VideoView;

/**
 * 视频播放界面
 * Created by Administrator on 2017/4/17 0017.
 */
public class VideoPlayerActivity extends BaseActivity implements View.OnClickListener {

    private static final int MSG_UPDATE_SYSTEM_TIME = 0;
    private static final int MSG_UPDATE_PLAY_TIME = 1;
    private static final int MSG_AUTO_HIDE_CTRL = 2;
    private VideoView mVideoView;
    private Button btnPlayPause;
    private TextView tvTitle;
    private TextView tvSystemTime;
    private ImageView ivBattery;
    private BatteryBroadcastReceiver batteryBroadcastReceiver;
    private AudioManager mAudioManager;
    private ImageView ivMute;
    private SeekBar sbVolume;
    private int mCurrentVolume;
    private float startY;
    private int mScreenHeight;
    private int currentVolume;
    private TextView tvPlayTime;
    private SeekBar sbPlayPosition;
    private TextView tvTotalTime;
    private Button btnBack;
    private Button btnReturn;
    private Button btnNext;
    private Button btnDefaultFullScreen;
    private ArrayList<VideoBean> mVideoList;
    private int mPosition;
    private GestureDetector mGesture;
    private LinearLayout llBottom;
    private LinearLayout llTop;

    @Override
    public int getLayout() {
        return R.layout.activity_video_player_layout;
    }

    @Override
    public void initView() {
//        视频
        mVideoView = (VideoView) findViewById(R.id.vv);
//        单击隐藏播放视频功能栏
        llBottom = (LinearLayout) findViewById(R.id.ll_bottom);
        llTop = (LinearLayout) findViewById(R.id.ll_top);
//        底部栏，播放暂停功能
        btnPlayPause = (Button) findViewById(R.id.btn_video_play_pause);
//        返回功能
        btnBack = (Button) findViewById(R.id.btn_back);
        btnReturn = (Button) findViewById(R.id.btn_return);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnDefaultFullScreen = (Button) findViewById(R.id.btn_default_fullscreen);
//        顶部栏
        tvTitle = (TextView) findViewById(R.id.tv_video_title);
        tvSystemTime = (TextView) findViewById(R.id.tv_system_time);
        ivBattery = (ImageView) findViewById(R.id.iv_battery);
        ivMute = (ImageView) findViewById(R.id.iv_mute);
        sbVolume = (SeekBar) findViewById(R.id.seekBar);
        tvPlayTime = (TextView) findViewById(R.id.tv_play_time);
        sbPlayPosition = (SeekBar) findViewById(R.id.sb_play_position);
        tvTotalTime = (TextView) findViewById(R.id.tv_total_time);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
//        VideoBean videoBean = (VideoBean) intent.getSerializableExtra("bean");
//        LogUtils.e(TAG, videoBean.toString());
        mVideoList = (ArrayList<VideoBean>) intent.getSerializableExtra("list");
        mPosition = intent.getIntExtra("pos", -1);
        playItem();
//        初始化系统时间
        startSystemTime();
//        注册系统电池电量变化的广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        batteryBroadcastReceiver = new BatteryBroadcastReceiver();
        registerReceiver(batteryBroadcastReceiver, filter);
//        获取系统的音量
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = getStreamSystemVolume();
//        LogUtils.e(TAG, "系统最大的音量：" + maxVolume);
//        把系统音量和SeekBar关联起来
        sbVolume.setMax(maxVolume);
//        设置当前的系统音量
        int currentVolume = getCurrentVolume();
        sbVolume.setProgress(currentVolume);
//        获取手机屏幕的高度
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = wm.getDefaultDisplay();
        mScreenHeight = defaultDisplay.getHeight();
    }

    private void playItem() {
//        设置上一曲下一曲在没有课播放的视频的时候的按钮是否可点击功能
        btnReturn.setEnabled(mPosition != 0);
        btnNext.setEnabled(mPosition != mVideoList.size() - 1);
        VideoBean videoBean = mVideoList.get(mPosition);
        mVideoView.setVideoURI(Uri.parse(videoBean.getData()));
//        设置视频的标题
        tvTitle.setText(videoBean.getTitle());
    }

    /**
     * 获取系统当前的音量
     *
     * @return
     */
    private int getCurrentVolume() {
        return mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
    }

    /**
     * 获取系统最大的音量
     */
    private int getStreamSystemVolume() {
        return mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        activity销毁前移除handler中所有的信息
        handler.removeCallbacksAndMessages(null);
//        注销电池电量变化的广播接收者
        unregisterReceiver(batteryBroadcastReceiver);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_UPDATE_SYSTEM_TIME:
                    startSystemTime();
                    break;
                case MSG_UPDATE_PLAY_TIME:
                    updatePlayPosition();
                    break;
                case MSG_AUTO_HIDE_CTRL:
                    hideCtrl();
                    break;
            }
        }
    };

    //初始化系统时间
    private void startSystemTime() {
        tvSystemTime.setText(StringUtils.formatSystemTime());
//        发送消息
        handler.sendEmptyMessageDelayed(MSG_UPDATE_SYSTEM_TIME, 500);
    }

    @Override
    public void initListener() {
//        底部栏，播放暂停
        btnPlayPause.setOnClickListener(this);
        mVideoView.setOnPreparedListener(new VideoPreparedListener());
        SeekBarChangeListener seekBarChangeListener = new SeekBarChangeListener();
        sbPlayPosition.setOnSeekBarChangeListener(seekBarChangeListener);
//        返回功能
        btnBack.setOnClickListener(this);
        btnReturn.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnDefaultFullScreen.setOnClickListener(this);
//        顶部栏
        sbVolume.setOnSeekBarChangeListener(seekBarChangeListener);
//        设置静音按钮
        ivMute.setOnClickListener(this);
//        视频
        mVideoView.setOnCompletionListener(new CompletionListener());
        mGesture = new GestureDetector(new SimpleOnGestureListener());
    }

    //    手指滑动修改音量
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGesture.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                获取按下时的坐标点
                startY = event.getY();
                currentVolume = getCurrentVolume();
                break;
            case MotionEvent.ACTION_MOVE:
//                滑动事件(4.3.2.1)
//划过屏幕的距离,决定是上滑音量减小还是反之，注释的之中为上滑音量减小，不注释的为上滑音量增大
//                float offsetY = event.getY() - startY;
                float offsetY = startY - event.getY();
//                划过屏幕的百分比
                float percent = offsetY / mScreenHeight;
//                变化音量
                float changeVolume = percent * getStreamSystemVolume();
//                最终音量
                int finalVolume = (int) (currentVolume + changeVolume);
                updateSystemVolume(finalVolume);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            播放暂停
            case R.id.btn_video_play_pause:
                switchPlayPause();
                break;
//            静音按钮
            case R.id.iv_mute:
                switchMute();
                break;
//            返回按钮
            case R.id.btn_back:
                finish();
                break;
//            上一曲
            case R.id.btn_return:
                playReturn();
                break;
//            下一曲
            case R.id.btn_next:
                playNext();
                break;
            case R.id.btn_default_fullscreen:
                switchFullScreen();
                break;
        }
    }

    private  void switchFullScreen() {
        mVideoView.switchDefaultFullScreen();
        updateFullScreenStatus();
    }

    /**
     * 更改全屏或默认比例的背景图片
     */
    private void updateFullScreenStatus() {
        if (mVideoView.isFullScreen()) {
            btnDefaultFullScreen
                    .setBackgroundResource(R.drawable.video_defultscreen_selector);
        } else {
            btnDefaultFullScreen
                    .setBackgroundResource(R.drawable.video_fullscreen_selector);
        }
    }

    private void playReturn() {
//        解决数组越界异常
        if (mPosition != 0) {
            mPosition--;
            playItem();
        }
    }

    private void playNext() {
//        解决数组越界异常
        if (mPosition != mVideoList.size() - 1) {
            mPosition++;
            playItem();
        }
    }

    /**
     * 静音按钮的处理
     * 如果是静音，恢复上一次的音量
     * 如果不是静音，设置为静音(音量值设置为0)
     */
    private void switchMute() {
        int cuurVolume = getCurrentVolume();
        if (cuurVolume == 0) {
//如果是静音，恢复上一次的音量
            updateSystemVolume(mCurrentVolume);
        } else {
//            保存当前的音量值
            mCurrentVolume = getCurrentVolume();
//            如果不是静音，设置成静音
            updateSystemVolume(0);
        }
    }

    /**
     * 修改系统音量
     */
    private void updateSystemVolume(int volume) {
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0);
        sbVolume.setProgress(volume);
    }

    private void switchPlayPause() {
        if (mVideoView.isPlaying()) {
            mVideoView.pause();
        } else {
            mVideoView.start();
        }
//        更新播放暂停图片
        updatePlayPause();
    }

    private void updatePlayPause() {
        if (mVideoView.isPlaying()) {
//            正在播放，显示暂停按钮
            btnPlayPause.setBackgroundResource(R.drawable.video_pause_selector);
            //            发送更新已播放时间的消息
            handler.sendEmptyMessageDelayed(MSG_UPDATE_PLAY_TIME, 500);

        } else {
            btnPlayPause.setBackgroundResource(R.drawable.video_play_selector);
            handler.removeMessages(MSG_UPDATE_PLAY_TIME);
        }
    }

    /**
     * 视频准备完成监听
     */
    private class VideoPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
//            准备完成后播放
            mVideoView.start();
//            设置视频的总时间
            tvTotalTime.setText(StringUtils.formatTime(mp.getDuration()));
//            更新已播放时间
            updatePlayPosition();

//            更新播放暂停按钮
            updatePlayPause();
//设置进度条的最大值
            sbPlayPosition.setMax(mp.getDuration());

//            延迟5秒发送隐藏控制面板的消息
            notifyAutoHideCtrl();
        }
    }

    /**
     *            延迟5秒发送隐藏控制面板的消息
     */
    private void notifyAutoHideCtrl() {
        handler.sendEmptyMessageDelayed(MSG_AUTO_HIDE_CTRL, 5000);
    }

    /**
     * 更新已播放时间
     */
    private void updatePlayPosition() {
        int currentPosition = mVideoView.getCurrentPosition();
        updatePlayTime(currentPosition);
        handler.sendEmptyMessageDelayed(MSG_UPDATE_PLAY_TIME, 500);
    }

    /**
     * 更新已播放时间
     *
     * @param currentPosition
     */
    private void updatePlayTime(int currentPosition) {
        tvPlayTime.setText(StringUtils.formatTime(currentPosition));
        sbPlayPosition.setProgress(currentPosition);
    }

    /**
     * 电池电量变化的广播接收者
     */
    private class BatteryBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", -1);

            updateSystemBatteryStatus(level);
        }

        /**
         * 修改系统电池电量的背景图片
         */
        private void updateSystemBatteryStatus(int level) {
            if (level == 100) {
                ivBattery.setImageResource(R.drawable.ic_battery_100);
            } else if (level >= 80 && level < 100) {
                ivBattery.setImageResource(R.drawable.ic_battery_80);
            } else if (level < 80 && level >= 60) {
                ivBattery.setImageResource(R.drawable.ic_battery_60);
            } else if (level < 60 && level >= 40) {
                ivBattery.setImageResource(R.drawable.ic_battery_40);
            } else if (level < 40 && level >= 20) {
                ivBattery.setImageResource(R.drawable.ic_battery_20);
            } else {
                ivBattery.setImageResource(R.drawable.ic_battery_0);
            }
        }
    }

    /**
     * 进度条拖动的监听
     */
    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        //进度变化后回调
//        fromUser:是不是用户拖动的进度条，需要对其进行判断，只要一拖动，fromUser就为true
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//            参数3：是否显示系统音量控制栏。1 显示，0 不显示
//            判断：如果不是用户拖动即返回为空值，即静音，否则再设置用户拖动改变的音量
            if (!fromUser) {
                return;
            }

            switch (seekBar.getId()) {
                case R.id.seekBar:
                    mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    break;
                case R.id.sb_play_position:
//                    已播放时间
//                    更新播放进度
                    mVideoView.seekTo(progress);
//                    更新播放时间
                    updatePlayTime(progress);
                    break;

            }

        }

        //手指触摸到进度条时调用
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            handler.removeMessages(MSG_AUTO_HIDE_CTRL);
        }

        //手指离开进度条前调用
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            notifyAutoHideCtrl();
        }
    }

    /**
     * 视频播放完成
     */
    private class CompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
//            移除handler发送的所有信息
            handler.removeCallbacksAndMessages(MSG_UPDATE_PLAY_TIME);
//            规避播放完成的回退1秒的BUG
            int max = mp.getDuration();
            updatePlayTime(max);
//            更新播放、暂停按钮的状态
            updatePlayPause();
        }
    }

    /**
     * 单击隐藏功能
     */
    private class SimpleOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        //        单击事件
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            switchCtrl();
            return super.onSingleTapConfirmed(e);
        }

        //双击事件
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            switchFullScreen();
            return super.onDoubleTap(e);
        }

        //长按事件
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            switchPlayPause();
        }
    }

    /**
     *
     */
    private boolean isShowing = true;
    private void switchCtrl() {
        if(isShowing){
//            隐藏控制面板
            hideCtrl();
        }else{
//            显示控制面板
            showCtrl();
        }
    }

    private void showCtrl() {
        ViewPropertyAnimator.animate(llTop).translationY(0);
        ViewPropertyAnimator.animate(llBottom).translationY(0);
        isShowing = true;
        notifyAutoHideCtrl();
    }

    private void hideCtrl() {
        ViewPropertyAnimator.animate(llTop).translationY(-llTop.getHeight());
        ViewPropertyAnimator.animate(llBottom).translationY(llBottom.getHeight());
        isShowing = false;
    }
}