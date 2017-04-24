package com.nursingplus.steafan.android.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.nursingplus.steafan.android.MainActivity;
import com.nursingplus.steafan.android.R;
import com.nursingplus.steafan.android.database.LoginData;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

/**修改登陆界面为手机号验证
 * Created by Administrator on 2017/4/3 0003.
 */
public class LoginActivity extends AppCompatActivity{

//    private Button login_btn;
//    private Button Message_btn;
//    private EditText userName_et, passWord_et;
//
//    @Override
//    public int getLayout() {
//        return R.layout.activity_login;
//    }
//
//    @Override
//    public void initView() {
//        login_btn = (Button) findViewById(R.id.login_btn);
//        Message_btn = (Button) findViewById(R.id.Message_btn);
//        userName_et = (EditText) findViewById(R.id.userName_et);
//        passWord_et = (EditText) findViewById(R.id.passWord_et);
//    }
//
//    @Override
//    public void initData() {
//
//        initBomb();
//        initView();
//        initListener();
//    }
//
//
//    private void initBomb() {
//        Bmob.initialize(this, "ea1f2af3af8505422de548d75b6733db");
//    }
//
//    @Override
//    public void initListener() {
//        login_btn.setOnClickListener(this);
//        Message_btn.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        String userName = userName_et.getText().toString();
//        String passWord = passWord_et.getText().toString();
//        switch (v.getId()) {
//            case R.id.Message_btn:
//                Log.e("MESSAGE:", "2");
//                if (userName.length() != 11) {
//                    Toast.makeText(this, "请输入11位有效手机号码", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Log.e("MESSAGE:", "3");
//                    //进行获取验证码操作和倒计时1分钟操作
//                    BmobSMS.requestSMSCode(this, userName, "短信模板", new RequestSMSCodeListener() {
//                        @Override
//                        public void done(Integer integer, BmobException e) {
//                            if (e == null) {
//                                //发送成功时，让获取验证码按钮不可点击，且为灰色
//                                Message_btn.setClickable(false);
//                                Message_btn.setBackgroundColor(Color.GRAY);
//                                Toast.makeText(LoginActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
//                                new CountDownTimer(60000, 1000) {
//                                    @Override
//                                    public void onTick(long millisUntilFinished) {
////                                        Message_btn.setBackgroundResource(R.drawable.button_shape02);
//                                        Message_btn.setText(millisUntilFinished / 1000 + "秒");
//                                    }
//
//                                    @Override
//                                    public void onFinish() {
//                                        Message_btn.setClickable(true);
////                                        Message_btn.setBackgroundResource(R.drawable.button_shape);
//                                        Message_btn.setText("重新发送");
//                                    }
//                                }.start();
//                                Log.e("MESSAGE:", "4");
//                            }
//                            else {
//                                Toast.makeText(LoginActivity.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//
//                }
//                break;
//            case R.id.login_btn:
//                Log.e("MESSAGE:", "5");
//                if (userName.length() == 0 || passWord.length() == 0 || userName.length() != 11) {
//                    Log.e("MESSAGE:", "6");
//                    Toast.makeText(this, "手机号或验证码输入不合法", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    BmobSMS.verifySmsCode(this, userName, passWord, new VerifySMSCodeListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e == null) {
//                                Log.e("MESSAGE:", "7");
//                                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
//                            }
//                            else {
//                                Log.e("MESSAGE:", "8");
//                                Toast.makeText(LoginActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//                break;
//        }
//    }
//}

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accoountEdit;
    private EditText passwordEdit;
    private Button login;

    private CheckBox rememberPass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        测试是否创建了数据库的按钮,初始化该按钮,并添加点击事件,成功
        Button testDBButton = (Button) findViewById(R.id.testDatabase);
        testDBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
            }
        });
//        测试是否添加了数据的按钮,成功
        Button addData = (Button) findViewById(R.id.testAddDatabase);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                像数据库中添加数据，首先要把数据库实例化（面向对象编程的基本思想）,成功
                LoginData loginData = new LoginData();
                loginData.setUserAccount("123456");
                loginData.setUserPassword("123456");
                loginData.save();
            }
        });
//        测试查询数据,成功
//        Button queryButton = (Button) findViewById(R.id.queryAddDatabase);
//        queryButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                用集合进行数据的查询,成功
//                List<LoginData> loginDatas = DataSupport.findAll(LoginData.class);
//                for(LoginData loginData : loginDatas){
//                    Log.d("LoginActivity","userAccount is:" + loginData.getUserAccount());
//                    Log.d("LoginActivity","userPassword is:" + loginData.getUserPassword());
//                }
//            }
//        });
        accoountEdit = (EditText) findViewById(R.id.accontId);
        passwordEdit = (EditText) findViewById(R.id.passwordId);


        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.loginButton);
        boolean isRemember = pref.getBoolean("记住密码", false);
        if (isRemember) {
//            将账号和密码都设置到文本框中
            String account = pref.getString("账号", "");
            String password = pref.getString("密码", "");
            accoountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
//        登陆按钮功能实现
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        增加用户登陆进度条,每当用户点击登陆的时候，就加载进度条，当登录完成后，取消进度条
                ProgressDialog loginProgressDialog = new ProgressDialog(LoginActivity.this);
                loginProgressDialog.setTitle("护+正在拼命登陆，小天使请稍后哦！");
                loginProgressDialog.setMessage("Loading...");
                loginProgressDialog.setCancelable(false);

//                        每当用户点击登录按钮的时候，遍历数据库中已存在的数据,成功
                List<LoginData> loginDatas = DataSupport.findAll(LoginData.class);
                for (LoginData loginData : loginDatas) {
//                    Log.d("LoginActivity","userAccount is:" + loginData.getUserAccount());
//                    Log.d("LoginActivity","userPassword is:" + loginData.getUserPassword());
                    String account = accoountEdit.getText().toString();
                    String password = passwordEdit.getText().toString();
//                修改登录账号和密码，测试用数据库中已经存在的数据进行登录能否成功.成功
                    if (account.equals(loginData.getUserAccount())
                            && password.equals(loginData.getUserPassword())) {
                        editor = pref.edit();
                        if (rememberPass.isChecked()) {
//                        检查复选框是否被选中
                            editor.putBoolean("记住密码", true);
                            editor.putString("账号", account);
                            editor.putString("密码", password);
                        } else {
                            editor.clear();
                        }
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        loginProgressDialog.show();
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "账户或密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Button regiesterButton = (Button) findViewById(R.id.regiesterButton);
        regiesterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent turnIntent = new Intent(LoginActivity.this, RegiesterActivity.class);
                startActivity(turnIntent);
            }
        });
    }
}