package com.example.orca.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.Socket;

public class ChatActivity  extends Activity  implements View.OnClickListener{
    private Button sendButton ;
    private EditText sendText ;
    private TextView revText;
    //线程类作为成员变量
    public class mThread extends Thread{
        public  Handler mHandler;
        private String buf;
        @Override
        public void run() {
            //从主线程接受text中的msg
            Looper.prepare();
            mHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    buf= msg.obj.toString();
                    Toast.makeText(ChatActivity.this, "recevied msg:"+buf, Toast.LENGTH_SHORT).show();
                }
            };

            //用socket发送消息
            try {
                Socket socket = new Socket("10.0.2.22", 2345);
                //发送信息
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.write(buf);
                pw.flush();//清空缓冲数据
                Toast.makeText(ChatActivity.this, "sent msg:"+buf, Toast.LENGTH_SHORT).show();
                socket.shutdownOutput();
                //获取信息
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader bw = new BufferedReader(isr);
                //创建一个buffer来进行数据缓存
                 StringBuffer temp =new StringBuffer();
                 String info = null;
                while (( info = bw.readLine()) != null) {
                    temp.append(info);
                    System.out.println("message from server" + info);
                }
                final String buffer =  temp.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ChatActivity.this, "revText改变了"+buffer, Toast.LENGTH_SHORT).show();
                        revText.setText(buffer);
                    }
                });
                bw.close();
                isr.close();
                pw.close();
                os.close();
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            Looper.loop();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);

        revText = (TextView) findViewById(R.id.revText);
        sendText=(EditText) findViewById(R.id.bot_message);
        sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.send_button:
                final mThread mthread = new mThread();
                mthread.start();
                Toast.makeText(ChatActivity.this, "按钮被按了！", Toast.LENGTH_SHORT).show();
                Message msg = new Message();
                String mbuf = sendText.getText().toString();
                msg.obj=mbuf;
                mthread.mHandler.handleMessage(msg);
                break;
        }
    }
        }

