package com.nursingplus.steafan.android.ui.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nursingplus.steafan.android.R;
import com.nursingplus.steafan.android.adapter.VideoListAdapter;
import com.nursingplus.steafan.android.bean.VideoBean;
import com.nursingplus.steafan.android.ui.activity.VideoPlayerActivity;

import java.util.ArrayList;

/**复习视频播放页面的数据源
 * Created by Administrator on 2017/4/16 0016.
 */
public class VideoFragment  extends BaseFragment{
    private ListView listView;

    @Override
    public int getLayout() {
        return R.layout.fragment_video_list;
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.lv);
    }

    @Override
    public void initData() {
//        利用内容提供器查询数据库中的数据
        ContentResolver resolver = getActivity().getContentResolver();
//        查询的时候必须写_id列，不写报无效的参数异常
        final Cursor cursor =  resolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Audio.Media._ID, MediaStore.Video.Media.DATA, MediaStore.Video.Media.TITLE
                        , MediaStore.Video.Media.DURATION
                        , MediaStore.Video.Media.SIZE}, null, null, null);

//        为LisiView设置适配器
        VideoListAdapter videoListAdapter = new VideoListAdapter(getActivity(), cursor);
        listView.setAdapter(videoListAdapter);

//        设置条目点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                VideoBean bean = VideoBean.newInstanceFromCursor(cursor);
//                intent.putExtra("bean", bean);
                ArrayList<VideoBean> list = VideoBean.getListFromCursor(cursor);
                Intent intent = new Intent(getActivity(), VideoPlayerActivity.class);
                intent.putExtra("list", list);
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        });

//        while(cursor.moveToNext()){
//            VideoBean bean = VideoBean.newInstanceFromCursor(cursor);
//            LogUtils.e(TAG, bean.toString());
//        }
    }

    @Override
    public void initListener() {

    }
}
