package com.nursingplus.steafan.android.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nursingplus.steafan.android.R;
import com.nursingplus.steafan.android.database.RegiesterData;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/4/9 0009.
 */
public class RegiesterActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regiester_layout);
        final EditText resAccountEdit = (EditText) findViewById(R.id.resAccontId);
        final EditText resPasswordEdit = (EditText) findViewById(R.id.resPasswordId);
        final EditText resOkPasseord = (EditText) findViewById(R.id.resOkPasswordId);
//        初始化注册按钮
        Button regiesterButton = (Button) findViewById(R.id.regiesterButton);
//        添加点击事件
        regiesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                查询数据
                List<RegiesterData> regiesterDatas = DataSupport.select("resUserName", "resUserPassword").find(RegiesterData.class);
//                循环遍历数据
                for(RegiesterData regiesterData : regiesterDatas){
                    if(resPasswordEdit != resOkPasseord){
                        Toast.makeText(RegiesterActivity.this, "Password is not match!Please write it again!", Toast.LENGTH_SHORT).show();
                    }else if(resAccountEdit.equals(regiesterData.getResUserName()) &&
                            resPasswordEdit.equals(regiesterData.getResUserPassword())){
                        Toast.makeText(RegiesterActivity.this, "This user was extised!Please Login Again!", Toast.LENGTH_SHORT).show();
                    }else{
                        String recieveResAccount = resAccountEdit.getText().toString();
                        String recieveResPassword = resPasswordEdit.getText().toString();
                        RegiesterData addRegiesterData = new RegiesterData();
                        addRegiesterData.setResUserName(recieveResAccount);
                        addRegiesterData.setResUserPassword(recieveResPassword);
                        addRegiesterData.save();
                        Toast.makeText(RegiesterActivity.this, "Congractualations! Regiester Success!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}