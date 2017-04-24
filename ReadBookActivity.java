package com.nursingplus.steafan.android.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nursingplus.steafan.android.R;

public class ReadBookActivity extends AppCompatActivity {
    TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readbook_layout);
//        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.scrollReadText);
        WebView testVB = (WebView) findViewById(R.id.testWebView);
        testVB.getSettings().setJavaScriptEnabled(true);
        testVB.setWebViewClient(new WebViewClient());
        testVB.loadUrl("http://www.ycxk.ac.cn/appNp/TestData.html");
//        sendRequestWithOkHttp();
    }

//    @Override
//    public void onClick(View v) {
//        if(v.getId() == R.id.send_request){
//            sendRequestWithOkHttp();
//        }
//    }
//    private void sendRequestWithOkHttp(){
//        //    开启线程来发起网络请求
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    OkHttpClient client = new OkHttpClient();
//                    Request request = new Request.Builder()
//                            .url("http://www.ycxk.ac.cn/appNp/getData.json")
//                            .build();
//                    Response response = client.newCall(request).execute();
//                    String responseData = response.body().string();
//                    parseJSONWithGSON(responseData);
//                    showResponse(responseData.toString());
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
////    将获取到的JSON数据设置到UI上面的方法
//        private void showResponse(final String responseContent){
////            转移到主线程中设置UI界面
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
////                在这里进行UI操作，将结果显示到界面上
//                responseText.setText(responseContent);
//            }
//        });
//    }
////    解析JSON数据的方法
//    private void parseJSONWithGSON(String jsonData){
//        Gson gson = new Gson();
//        List<App> appList = gson.fromJson(jsonData,
//                new TypeToken<List<App>>(){}.getType());
//        for(App app : appList){
//
//        }
//    }
}
