package com.example.orca.myapplication;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.orca.fragment.CFragment;
import com.example.orca.fragment.RFragment;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final OkHttpClient client = new OkHttpClient();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //按钮监听
       button= (Button) findViewById(R.id.controller);
       button .setOnClickListener(this);
        findViewById(R.id.receiver).setOnClickListener(this);
    }
    @Override
    //各个按钮的响应
    public void onClick(View v){
        switch(v.getId())
        {
            case R.id.controller:
                CFragment cFragment = new CFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.blank,cFragment);
                fragmentTransaction.commit();
                getRequest();

                break;
            case R.id.receiver:
                RFragment rFragment = new RFragment();
                FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.blank,rFragment);
                fragmentTransaction2.commit();
                break;

        }

    }
    /*新的请求写法 用到call.nque*/
    private void getRequest(){
        Request request = new Request.Builder().url("http://baidu.com").build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback(){
            @Override
            public void onResponse(Response response) throws IOException {
                final String res = response.body().string();
                //ui线程更新ui
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setText(res);

                    }
                });

            }



            @Override
            public void onFailure(Request request, IOException e) {

            }

        });
    }
    /*之前的请求写法*/
   /* private void getRequest() {

        final Request request=new Request.Builder()
                .get()
                .tag(this)
                .url("http://www.baidu.com")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY","打印GET响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void postRequest() {

        RequestBody formBody = new FormEncodingBuilder()
                .add("","")
                .build();

        final Request request = new Request.Builder()
                .url("http://www.wooyun.org")
                .post(formBody)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response = null;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        Log.i("WY","打印POST响应的数据：" + response.body().string());
                    } else {
                        throw new IOException("Unexpected code " + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }*/
}
