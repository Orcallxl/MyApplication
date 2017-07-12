package com.example.orca.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.orca.myapplication.ChatActivity;
import com.example.orca.myapplication.MainActivity;
import com.example.orca.myapplication.R;


import java.util.Collection;
import java.util.Collections;

/**
 * Created by orca on 2017/3/26.
 */

public class CFragment extends Fragment implements View.OnClickListener{
    private String ipAddress;
    private WifiManager wifiManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        wifiManager= (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        View view = inflater.inflate(R.layout.c_fragment,container,false);
        view.findViewById(R.id.connect).setOnClickListener(this);
        view.findViewById(R.id.chat).setOnClickListener(this);
        return  view;
    }
    //链接网络和聊天按钮响应
    public void onClick(View v){
        switch (v.getId()){
            case R.id.connect:
//                Intent wifiintent = new Intent(getActivity(),WifiActivity.class);
//                getActivity().startActivity(wifiintent);
                if(wifiManager.isWifiEnabled()==false){//问题：连接不一定是可以访问，比如设置了密码的热点
                    Toast.makeText(getActivity(),"请打开WIFI连接机器人的热点网络！",Toast.LENGTH_SHORT).show();
                }
                else {;
                    getWifiAddress();
                    Toast.makeText(getActivity(), "连接到了机器人，机器人的地址是："+ipAddress,Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.chat:
                Intent chatintent = new Intent(getActivity(),ChatActivity.class);
                getActivity().startActivity(chatintent);
                break;
        }
    }
    //获取wifi地址
    void getWifiAddress(){
        WifiInfo wifiInfo= wifiManager.getConnectionInfo();
        ipAddress=long2Ip(wifiInfo.getIpAddress());

    }
    //把整型ip转换为字符串
    public static final String long2Ip(final long ip) {
        final long[] mask = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };

        final StringBuilder ipAddress = new StringBuilder();
        for (int i = mask.length-1; i >= 0; i--) {
            ipAddress.insert(0, (ip & mask[i]) >> (i * 8));
            if (i < mask.length - 1) {
                ipAddress.insert(0, ".");
            }
    }

//        return new StringBuilder(ipAddress.toString()).reverse().toString();
        return ipAddress.toString();
    }

}
