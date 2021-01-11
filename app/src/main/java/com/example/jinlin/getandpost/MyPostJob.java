package com.example.jinlin.getandpost;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyPostJob extends AsyncTask<String, Void, String> {
    //获取当前页面所需上下文，吐司要用
    Context context;
    Message msg;

    public void setMsg(Message msg){
        this.msg = msg;
    }

    public MyPostJob(Context context){
        super();
        this.context = context;
    }
    public MyPostJob(){
        super();
    }
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
            //设置连接超时时间
            con.setConnectTimeout(5 * 1000);
            //设置读取超时时间
            con.setReadTimeout(5 * 1000);
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            String body = "{\"username\": \"" + parms[1] + "\", \"password\": \"" + parms[2] + "\"}";
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
            }else if(con.getResponseCode() == 401){
                is = con.getErrorStream();
                int next = 0;
                byte[] bt = new byte[1024];
                while ((next = is.read(bt)) > 0) {
                    sbd.append(new String(bt, 0, next));
                }
            }
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
        if(s.equals("")) {
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        }else {
            msg.tranStr(s);
        }
    }
}