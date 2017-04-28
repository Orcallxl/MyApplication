package com.example.orca.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.orca.myapplication.ChatActivity;
import com.example.orca.myapplication.MainActivity;
import com.example.orca.myapplication.R;
import com.example.orca.myapplication.WifiActivity;

/**
 * Created by orca on 2017/3/26.
 */

public class CFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.c_fragment,container,false);
        view.findViewById(R.id.connect).setOnClickListener(this);
        view.findViewById(R.id.chat).setOnClickListener(this);
        return  view;
    }
    //链接网络和聊天按钮响应
    public void onClick(View v){
        switch (v.getId()){
            //链接网络
            case R.id.connect:
                Intent wifiintent = new Intent(getActivity(),WifiActivity.class);
                getActivity().startActivity(wifiintent);
                break;
            //歌曲推送，这里打算写成一个智能的聊天事务，可能要用到即时通信。
            case R.id.chat:
                Intent chatintent = new Intent(getActivity(),ChatActivity.class);
                getActivity().startActivity(chatintent);
                break;


        }
    }

}
