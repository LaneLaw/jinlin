package com.example.jinlin.getandpost;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyGetJob extends AsyncTask<String,Void,String>{
    //获取当前页面所需上下文，吐司要用
    Context context;
    Message msg;

    public void setMsg(Message msg){
        this.msg = msg;
    }

    public MyGetJob(Context context){
        super();
        this.context = context;
    }
    public MyGetJob(){
        super();
    }

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
                while((next = is.read(bt)) > 0){
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
        if(s.equals("")) {
            Toast.makeText(context, "", Toast.LENGTH_LONG).show();
        }else {
            msg.tranStr(s);
        }
    }
}
