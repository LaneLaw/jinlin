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
//                    // ????????????????????????????????????
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
//            //??????????????????
//            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
//        }
//    }
//    public class MyGetJob extends AsyncTask<String,Void,String>{
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        //doInBackground?????????????????????????????????????????????????????????
//        @Override
//        protected String doInBackground(String... params) {
//            //?????????????????? ???????????????
//            // HttpURLConnection???????????????????????????  ?????????????????????????????????????????????
//
//            HttpURLConnection con = null;
//            //???
//            InputStream is = null;
//            StringBuilder sbd = new StringBuilder();
//
//            try {
//                URL url = new URL(params[0]);
//                con = (HttpURLConnection) url.openConnection();
//                //????????????????????????
//                con.setConnectTimeout(5 * 1000);
//                //????????????????????????
//                con.setReadTimeout(5 * 1000);
//                /**
//                 * http?????????
//                 * 200?????????  404????????????  500???????????????
//                 */
//
//                if (con.getResponseCode() == 200) {
//                    // ????????????????????????????????????
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
//                        //??????
//                        is.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (con != null) {
//                    //????????????
//                    con.disconnect();
//                }
//            }
//
//            return sbd.toString();
//        }
//
//        //?????????doInBackground????????????????????????
//        //String s?????????   ??????doInBackground?????????????????????
//
//        //onPostExecute ???UI????????????????????????????????????
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            //??????????????????
//            account.setText(s);
//        }
//    }

}

