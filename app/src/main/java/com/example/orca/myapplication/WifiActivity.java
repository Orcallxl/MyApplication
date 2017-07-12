//package com.example.orca.myapplication;
//
//import android.app.Activity;
//import android.net.wifi.ScanResult;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.orca.tools.WifiAdmin;
//
//import java.util.List;
//
///**
// * Created by orca on 2017/4/25.
// */
//
//public class WifiActivity extends Activity implements View.OnClickListener {
//    private ScrollView sView;
//    private TextView allNetWork;
//    private Button scan;
//    private Button start;
//    private Button stop;
//    private Button check;
//    private WifiAdmin mWifiAdmin;
//    // 扫描结果列表
//    private List<ScanResult> list;
//    private ScanResult mScanResult;
//    private StringBuffer mStringBuffer = new StringBuffer();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.wifi_layout);
//        mWifiAdmin = new WifiAdmin(WifiActivity.this);
//        init();
//    }
//
//    //view 初始化
//    public void init() {
//        sView = (ScrollView) findViewById(R.id.mScrollView);
//        allNetWork = (TextView) findViewById(R.id.allNetWork);
//        scan = (Button) findViewById(R.id.scan);
//        start = (Button) findViewById(R.id.start);
//        stop = (Button) findViewById(R.id.stop);
//        check = (Button) findViewById(R.id.check);
//        scan.setOnClickListener(WifiActivity.this);
//        start.setOnClickListener(WifiActivity.this);
//        stop.setOnClickListener(WifiActivity.this);
//        check.setOnClickListener(WifiActivity.this);
//    }
//
//    // WIFI_STATE_DISABLING 0
//    // WIFI_STATE_DISABLED 1
//    // WIFI_STATE_ENABLING 2
//    // WIFI_STATE_ENABLED 3
//    public void start() {
//        mWifiAdmin.openWifi();
//        Toast.makeText(WifiActivity.this, "当前Wifi网卡状态为" + mWifiAdmin.checkState(),
//                Toast.LENGTH_SHORT).show();
//    }
//
//    public void stop() {
//        mWifiAdmin.closeWifi();
//        Toast.makeText(WifiActivity.this, "当前Wifi网卡状态为" + mWifiAdmin.checkState(),
//                Toast.LENGTH_SHORT).show();
//    }
//
//    public void check() {
//        Toast.makeText(WifiActivity.this, "当前Wifi网卡状态为" + mWifiAdmin.checkState(),
//                Toast.LENGTH_SHORT).show();
//    }
//
//    public void getAllNetWorkList() {
//        // 每次点击扫描之前清空上一次的扫描结果
//        if (mStringBuffer != null) {
//            mStringBuffer = new StringBuffer();
//        }
//
//        // 开始扫描网络
//        mWifiAdmin.startScan();
//        list = mWifiAdmin.getWifiList();
//
//        if (list != null) {
//            for (int i = 0; i < list.size(); i++) {
//                mScanResult = list.get(i);
//                // 得到网络的SSID：the network name
//                mStringBuffer = mStringBuffer
//                        .append(mScanResult.SSID).append("        ")
//                        .append(mScanResult.BSSID).append("        ")
//                        .append(mScanResult.capabilities).append("        ")
//                        .append(mScanResult.frequency).append("        ")
//                        .append(mScanResult.level).append("        ")
//                        .append("\n\n");
//            }
//            allNetWork.setText("扫描到的所有Wifi网络：\n" + mStringBuffer.toString());
//        }
//    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.scan:
//                getAllNetWorkList();
//                break;
//            case R.id.start:
//                start();
//                break;
//            case R.id.stop:
//                stop();
//                break;
//            case R.id.check:
//                check();
//                break;
//            default:
//                break;
//        }
//    }
//}
