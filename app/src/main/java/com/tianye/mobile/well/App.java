package com.tianye.mobile.well;

import android.app.Application;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2015/3/26.
 */
public class App extends Application {
    private static Context sContext;
    public LocationClient mLocationClient;
    public GeofenceClient mGeofenceClient;
    public MyLocationListener mMyLocationListener;

    public TextView mLocationResult,logMsg;
    public TextView trigger,exit;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);

        initGPS();

    }

    /**
     * 实现实位回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuffer sb = new StringBuffer(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            if (location.getLocType() == BDLocation.TypeGpsLocation){
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\ndirection : ");
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());

                sb.append("\ncity:");
                sb.append(location.getCity());

                sb.append(location.getDirection());

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());

                sb.append("\ncity:");
                sb.append(location.getCity());
            }

            RequestQueue mQueue = Volley.newRequestQueue(App.getContext());

//            String url="http://api.map.baidu.com/geocoder?output=json&location=" + location.getLatitude() + "," +
//                    location.getLongitude() + " & key=" + UseTool.getAppKey();
//
//            mQueue.add(new JsonObjectRequest(Request.Method.GET, url, null,
//                    new Response.Listener(){
//                        @Override
//                        public void onResponse(Object o) {
//                            //cityName.setText(o.toString());
//                            logMsg(o.toString());
//                            Log.d("App", o.toString());
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError volleyError) {
//                    Log.e("Error: ", volleyError.toString());
//                    Log.e("Error: " ,volleyError.getLocalizedMessage());
//                }
//            }));
//            mQueue.start();

            logMsg(sb.toString());
            Log.i("BaiduLocationApiDem", sb.toString());
        }

    }
    /**
     * 显示请求字符串
     * @param str
     */
    public void logMsg(String str) {
        try {
            if (mLocationResult != null)
                mLocationResult.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initGPS() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        mGeofenceClient = new GeofenceClient(getApplicationContext());

    }

    public static Context getContext() {
        return sContext;
    }
}
