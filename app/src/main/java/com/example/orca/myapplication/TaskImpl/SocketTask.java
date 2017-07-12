package com.example.orca.myapplication.TaskImpl;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.example.orca.myapplication.ChatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by orca on 2017/5/22.
 */

public class SocketTask implements Runnable {
    private String buf;
    private String response;
    private Handler handler;
    public SocketTask(String buf,Handler handler)
    {
        this.buf=buf;
        this.handler=handler;

    }

    public String getResponse() {
        return response;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("10.0.2.22", 2346);
            //发送信息
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write(buf);
            pw.flush();//清空缓冲数据
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
            response = temp.toString();
            if(response!=null){
                Message msg = new Message();
                msg.what=110;
                msg.obj=response;
                handler.handleMessage(msg);
            }
            bw.close();
            isr.close();
            pw.close();
            os.close();
            socket.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
