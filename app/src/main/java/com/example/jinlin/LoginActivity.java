package com.example.jinlin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jinlin.getandpost.MyPostJob;

public class LoginActivity extends AppCompatActivity{
    Button login, register;
    EditText account,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = findViewById(R.id.edit_account);
        password = findViewById(R.id.edit_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url_str = "http://192.168.42.188:1337/user/login";
                new MyPostJob(getApplicationContext()).execute(url_str);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, TestActivity.class);
                startActivity(intent);
            }
        });

    }
//    public class MyPostJob extends AsyncTask<String, Void, String>{
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected String doInBackground(String... parms){
//            HttpURLConnection con = null;
//            InputStream is = null;
//            StringBuilder sbd = new StringBuilder();
//            String acc = account.getText().toString();
//            String pas = password.getText().toString();
//
//
//            try{
//                URL url = new URL(parms[0]);
//                con = (HttpURLConnection) url.openConnection();
//                con.setRequestMethod("POST");
//                con.setRequestProperty("Content-Type", "application/json");
////                String body = "{\"username\": \"" + acc + "\", \"password\": \"" + pas + "\"}";
//                String body = "{\"id\" : "+ 3 + "}";
//                con.setDoInput(true);
//                con.setDoOutput(true);
//                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//                wr.writeBytes(body);
//                wr.flush();
//                wr.close();
//
//                if (con.getResponseCode() == 200) {
//                    // 读取网页内容（字符串流）
//                    is = con.getInputStream();
//                    int next = 0;
//                    byte[] bt = new byte[1024];
//                    while ((next = is.read(bt)) > 0) {
//                        sbd.append(new String(bt, 0, next));
//                    }
//                }else if(con.getResponseCode() == 401){
//                    is = con.getErrorStream();
//                    int next = 0;
//                    byte[] bt = new byte[1024];
//                    while ((next = is.read(bt)) > 0) {
//                        sbd.append(new String(bt, 0, next));
//                    }
//                }
//                con.disconnect();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return sbd.toString();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            //返回显示结果
//            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
//        }
//    }
//    public class MyGetJob extends AsyncTask<String,Void,String>{
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        //doInBackground在子线程中执行命令（耗时操作写在这里）
//        @Override
//        protected String doInBackground(String... params) {
//            //因为要抛异常 所以置为空
//            // HttpURLConnection用这个可以访问网络  在清单文件中打开访问网络的权限
//
//            HttpURLConnection con = null;
//            //流
//            InputStream is = null;
//            StringBuilder sbd = new StringBuilder();
//
//            try {
//                URL url = new URL(params[0]);
//                con = (HttpURLConnection) url.openConnection();
//                //设置连接超时时间
//                con.setConnectTimeout(5 * 1000);
//                //设置读取超时时间
//                con.setReadTimeout(5 * 1000);
//                /**
//                 * http响应码
//                 * 200：成功  404：未找到  500：发生错误
//                 */
//
//                if (con.getResponseCode() == 200) {
//                    // 读取网页内容（字符串流）
//                    Log.d("Probelms!", "Here!");
//                    is = con.getInputStream();
//                    int next = 0;
//                    byte[] bt = new byte[1024];
//                    while ((next = is.read(bt)) > 0) {
//                        sbd.append(new String(bt, 0, next));
//                    }
//
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (is != null) {
//                    try {
//                        //关闭
//                        is.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (con != null) {
//                    //断开连接
//                    con.disconnect();
//                }
//            }
//
//            return sbd.toString();
//        }
//
//        //执行完doInBackground后返回结果的位置
//        //String s是结果   对应doInBackground里的第三个参数
//
//        //onPostExecute 在UI线程（主线程）中执行命令
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            //返回显示结果
//            account.setText(s);
//        }
//    }

}

