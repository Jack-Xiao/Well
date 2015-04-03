package com.tianye.mobile.well.fragment;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.tianye.mobile.well.App;

/**
 * Created by lenovo on 2015/4/1.
 */
public class GSPFragment3 extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void getLocalIPAddress(){
        WifiManager wm = (WifiManager) App.getContext().getSystemService(Context.WIFI_SERVICE);
        if (!wm.isWifiEnabled()) {
            //Toast.makeText(this, "没有可用的网络", Toast.LENGTH_LONG).show();
        }
    }
    private String getIp(){
        WifiManager wm=(WifiManager)getActivity().getSystemService(Context.WIFI_SERVICE);
        //检查Wifi状态
        if(!wm.isWifiEnabled())
            wm.setWifiEnabled(true);
        WifiInfo wi=wm.getConnectionInfo();
        //获取32位整型IP地址
        int ipAdd=wi.getIpAddress();
        //把整型地址转换成“*.*.*.*”地址
        String ip=intToIp(ipAdd);
        return ip;
    }
    private String intToIp(int i) {
        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }
}
