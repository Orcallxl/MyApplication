package com.example.orca.myapplication;

import android.app.Activity;
import android.icu.util.Calendar;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orca.myapplication.Adapter.ChatAdapter;
import com.example.orca.myapplication.Entity.ChatEntity;
import com.example.orca.myapplication.TaskImpl.SocketTask;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity  extends Activity  implements View.OnClickListener{
    private ChatAdapter chatAdapter;
    private ListView listView;
    private Button sendButton ;
    private EditText sendText ;
    //private TextView revText;
    private List<ChatEntity> chatEntityList = new ArrayList<ChatEntity>();
    private  Handler handler= new Handler(){
        @Override
        public void handleMessage(Message msg) {//handler可以实现“监听”的效果
            switch(msg.what){
                case 110:
                ChatEntity var2= new ChatEntity();
                var2.setContent(msg.obj.toString());
                var2.setComeMsg(true);
                var2.setUserImage(1);
                Calendar calendar2 = Calendar.getInstance();
                String created2 = calendar2.get(Calendar.YEAR) + "年"
                        + (calendar2.get(Calendar.MONTH)+1) + "月"
                        + calendar2.get(Calendar.DAY_OF_MONTH) + "日"
                        + calendar2.get(Calendar.HOUR_OF_DAY) + "时"
                        + calendar2.get(Calendar.MINUTE) + "分"+calendar2.get(Calendar.SECOND)+"秒";
                var2.setChatTime(created2);
                chatEntityList.add(var2);
                chatAdapter.notifyDataSetChanged();//刷新界面
                listView.setSelection(chatEntityList.size());//listview定位到最后一行
                break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_chat);
        chatAdapter = new ChatAdapter(ChatActivity.this,R.layout.chat_item,chatEntityList);
        listView = (ListView) findViewById(R.id.msg_list_view);
        listView.setAdapter(chatAdapter);
        //revText = (TextView) findViewById(R.id.revText);
        sendText=(EditText) findViewById(R.id.input_text);
        sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.send_button:;
                //问题：没按一次发送新建一个对象，内存会boom
                ChatEntity var1= new ChatEntity();
                var1.setContent(sendText.getText().toString());
                var1.setComeMsg(false);
                var1.setUserImage(1);
                Calendar calendar = Calendar.getInstance();
                String created = calendar.get(Calendar.YEAR) + "年"
                        + (calendar.get(Calendar.MONTH)+1) + "月"
                        + calendar.get(Calendar.DAY_OF_MONTH) + "日"
                        + calendar.get(Calendar.HOUR_OF_DAY) + "时"
                        + calendar.get(Calendar.MINUTE) + "分"+calendar.get(Calendar.SECOND)+"秒";
                var1.setChatTime(created);
                chatEntityList.add(var1);
                chatAdapter.notifyDataSetChanged();//刷新界面
                listView.setSelection(chatEntityList.size());//listview定位到最后一行
                Runnable socketTask= new SocketTask(sendText.getText().toString(),handler);
                Thread thread = new Thread(socketTask);
                thread.start();
                sendText.setText("");
                break;
        }
    }
        }

