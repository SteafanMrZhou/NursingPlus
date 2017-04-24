package com.nursingplus.steafan.android.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nursingplus.steafan.android.R;

/**
 * Created by Administrator on 2017/4/15 0015.
 */
public class BooksItemActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_item);
        ImageView longImage = (ImageView) findViewById(R.id.books_image);
        longImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(BooksItemActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
//        FloatingActionButton reviewButton = (FloatingActionButton) findViewById(R.id.reviewFB);
//        reviewButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                //按下操作
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    Toast.makeText(BooksItemActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
//                }
//                //抬起操作
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//
//                }
//                //移动操作
//                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
//
//                }
//                return false;
//            }
//        });
    }
}
