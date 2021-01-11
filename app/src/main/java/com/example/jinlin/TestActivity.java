package com.example.jinlin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestActivity extends AppCompatActivity {
    Button login;
    TextView account;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        login = findViewById(R.id.btn_);
        account = findViewById(R.id.test);
//        webView = findViewById(R.id.web_view);
//        webView.loadUrl("http://192.168.43.183:5000/Samples/TypeScript/Demo");
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return super.shouldOverrideUrlLoading(view, url);
//            }
//        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_str = "http://localhost:1337/user/login";
                new MyPostJob().execute(url_str);
            }
        });

    }

    public class MyPostJob extends AsyncTask<String, Void, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... parms){
            HttpURLConnection con = null;
            InputStream is = null;
            StringBuilder sbd = new StringBuilder();


            try{
                URL url = new URL(parms[0]);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                String body = "{\"username\": \"Joe\", \"password\": 123456}";
                con.setDoInput(true);
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(body);
                wr.flush();
                wr.close();


                if (con.getResponseCode() == 200) {
                    // 读取网页内容（字符串流）
                    is = con.getInputStream();
                    int next = 0;
                    byte[] bt = new byte[1024];
                    while ((next = is.read(bt)) > 0) {
                        sbd.append(new String(bt, 0, next));
                    }
                    Log.d("Qesponse: ", sbd.toString());
                }
                is.close();
                con.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sbd.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //返回显示结果
            account.setText(s);
        }
    }
    public class MyGetJob extends AsyncTask<String,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //doInBackground在子线程中执行命令（耗时操作写在这里）
        @Override
        protected String doInBackground(String... params) {
            //因为要抛异常 所以置为空
            // HttpURLConnection用这个可以访问网络  在清单文件中打开访问网络的权限

            HttpURLConnection con = null;
            //流
            InputStream is = null;
            StringBuilder sbd = new StringBuilder();

            try {
                URL url = new URL(params[0]);
                con = (HttpURLConnection) url.openConnection();
                //设置连接超时时间
                con.setConnectTimeout(5 * 1000);
                //设置读取超时时间
                con.setReadTimeout(5 * 1000);
                /**
                 * http响应码
                 * 200：成功  404：未找到  500：发生错误
                 */

                if (con.getResponseCode() == 200) {
                    // 读取网页内容（字符串流）
                    is = con.getInputStream();
                    int next = 0;
                    byte[] bt = new byte[1024];
                    while ((next = is.read(bt)) > 0) {
                        sbd.append(new String(bt, 0, next));
                    }

                }
            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        //关闭
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (con != null) {
                    //断开连接
                    con.disconnect();
                }
            }

            return sbd.toString();
        }

        //执行完doInBackground后返回结果的位置
        //String s是结果   对应doInBackground里的第三个参数

        //onPostExecute 在UI线程（主线程）中执行命令
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //返回显示结果
            account.setText(s);
        }
    }
}

