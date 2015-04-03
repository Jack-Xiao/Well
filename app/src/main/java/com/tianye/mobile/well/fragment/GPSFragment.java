package com.tianye.mobile.well.fragment;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tianye.mobile.well.App;
import com.tianye.mobile.well.R;
import com.tianye.mobile.well.util.ToastUtil;
import com.tianye.mobile.well.util.UseTool;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by lenovo on 2015/3/27.
 */
public class GPSFragment extends BaseFragment {
    private static final String TAG = "GPSFragment";
    @InjectView(R.id.et_gps)
    TextView etGps;
    @InjectView(R.id.city_name)
    TextView cityName;

    private Geocoder geocoder;
    private List<Address> addList;
    private LocationManager locationManager;
    private String mCityName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            ToastUtil.showLong("请开启GPS导航...");
            //返回开启GPS 导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
            return;
        }else{


            LocationListener locationListener = new LocationListener() {

                // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                // Provider被enable时触发此函数，比如GPS被打开
                @Override
                public void onProviderEnabled(String provider) {

                }

                // Provider被disable时触发此函数，比如GPS被关闭
                @Override
                public void onProviderDisabled(String provider) {

                }

                //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        Log.e("Map", "Location changed : Lat: "
                                + location.getLatitude() + " Lng: "
                                + location.getLongitude());
                    }
                }
            };
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null){
//                latitude = location.getLatitude(); //经度
//                longitude = location.getLongitude(); //纬度
                updateView(location);
            }

        }
        geocoder = new Geocoder(App.getContext());


    }

    private void GetCityName(Location location) {
        location =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location == null){
            location =locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        //location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        LocationManager locMan = (LocationManager) context
//                .getSystemService(Context.LOCATION_SERVICE);
//        Location location = locMan
//                .getLastKnownLocation(LocationManager.GPS_PROVIDER);


//        if (location == null) {
//            location = locMan
//                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        }
//        return location;


        try {
            String url="http://api.map.baidu.com/geocoder?output=json&location=" + location.getLatitude() + "," +
                            location.getLongitude() + " & key=" + UseTool.getAppKey();

//            RequestManager.addRequest(new JsonObjectRequest(Method.GET,url,null,new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject jsonObject) {
//                    cityName.setText(jsonObject.toString());
//                }
//            }));
//            RequestManager.addRequest(new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener() {
//                @Override
//                public void onResponse(Object o) {
//                    cityName.setText(o.toString());
//                }
//            },null));

            RequestQueue mQueue = Volley.newRequestQueue(App.getContext());

            mQueue.add(new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener(){
                        @Override
                        public void onResponse(Object o) {
                            cityName.setText(o.toString());
                        }
                    }, null));
            mQueue.start();


            addList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            //mCityName = addList
            for (int i = 0; i < addList.size(); i++) {
                Address add = addList.get(i);
                mCityName += add.getLocality();
            }
            if (mCityName !=null && mCityName.length() != 0) {
                mCityName= mCityName.substring(0, (mCityName.length() - 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateView(location);
            Log.i(TAG, "时间：" + location.getTime());
            Log.i(TAG, "经度：" + location.getLongitude());
            Log.i(TAG, "纬度：" + location.getLatitude());
            Log.i(TAG, "海拔：" + location.getAltitude());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                //GPS状态为可见时
                case LocationProvider.AVAILABLE:
                    Log.i(TAG, "当前GPS状态为可见状态");
                    break;
                //GPS状态为服务区外时
                case LocationProvider.OUT_OF_SERVICE:
                    Log.i(TAG, "当前GPS状态为服务区外状态");
                    break;
                //GPS状态为暂停服务时
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.i(TAG, "当前GPS状态为暂停服务状态");
                    break;
            }
        }

        /*    GPS开启时触发    */
        @Override
        public void onProviderEnabled(String provider) {
            Location location = locationManager.getLastKnownLocation(provider);
            updateView(location);
        }

        /* GPS禁用时触发 */
        @Override
        public void onProviderDisabled(String provider) {
            updateView(null);
        }
    };
    GpsStatus.Listener listener = new GpsStatus.Listener() {

        @Override
        public void onGpsStatusChanged(int event) {
            switch (event) {
                //第一次定位
                case GpsStatus.GPS_EVENT_FIRST_FIX:
                    Log.i(TAG, "第一次定位");
                    break;
                //卫星状态改变
                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
                    Log.i(TAG, "卫星状态改变");
                    //获取当前状态
                    GpsStatus gpsStatus = locationManager.getGpsStatus(null);
                    //获取卫星颗数的默认最大值
                    int maxSatellites = gpsStatus.getMaxSatellites();
                    //创建一个迭代器保存所有卫星
                    Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
                    int count = 0;
                    while (iters.hasNext() && count <= maxSatellites) {
                        GpsSatellite s = iters.next();
                        count++;
                    }
                    System.out.println("搜索到：" + count + "颗卫星");
                    break;
                //定位启动
                case GpsStatus.GPS_EVENT_STARTED:
                    Log.i(TAG, "定位启动");
                    break;
                //定位结束
                case GpsStatus.GPS_EVENT_STOPPED:
                    Log.i(TAG, "定位结束");
                    break;
            }
        }
    };

    private void updateView(Location location) {
        if (location != null) {
            etGps.setText("设备位置信息\n\n经度：");
            etGps.append(String.valueOf(location.getLongitude()));
            etGps.append("\n纬度：");
            etGps.append(String.valueOf(location.getLatitude()));
            GetCityName(location);

        } else {
            //清空EditText对象
            //etGps.getEditableText().clear();
           // cityName.getEditableText().clear();
        }
    }

    /**
     * 返回查询条件
     *
     * @return
     */
    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        //设置定位精确度 Criteria.ACCURACY_COARSE比较粗略，Criteria.ACCURACY_FINE则比较精细
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //设置是否要求速度
        criteria.setSpeedRequired(false);
        // 设置是否允许运营商收费
        criteria.setCostAllowed(false);
        //设置是否需要方位信息
        criteria.setBearingRequired(false);
        //设置是否需要海拔信息
        criteria.setAltitudeRequired(false);
        // 设置对电源的需求
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        return criteria;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.fragment_gps, null);
        ButterKnife.inject(this, contentView);

        String bestProvider = locationManager.getBestProvider(getCriteria(), true);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        updateView(location);
        //监听状态
        locationManager.addGpsStatusListener(listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
        return contentView;
    }

    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.setting",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    //    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initLocation();
//    }
//
//    private void initLocation() {
//        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
//        locationManager.addGpsStatusListener(listener); //绑定监听状态
//    }
//
//    private LocationListener locationListener = new LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            updateView(location);
//            Log.i(TAG, "时间 : " + location.getTime());
//            Log.i(TAG, "经度：" + location.getLongitude());
//            Log.i(TAG, "纬度：" + location.getLatitude());
//            Log.i(TAG, "海拔：" + location.getAltitude());
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//            switch (status) {
//                //GPS状态为可见时
//                case LocationProvider.AVAILABLE:
//                    Log.i(TAG, "当前GPS状态为可见状态");
//                    break;
//                //GPS状态为服务区外时
//                case LocationProvider.OUT_OF_SERVICE:
//                    Log.i(TAG, "当前GPS状态为服务区外状态");
//                    break;
//                //GPS状态为暂停服务时
//                case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                    Log.i(TAG, "当前GPS状态为暂停服务状态");
//                    break;
//            }
//        }
//
//        /**
//         * GPS开启时触发
//         *
//         * @param provider
//         */
//        @Override
//        public void onProviderEnabled(String provider) {
//            Location location = locationManager.getLastKnownLocation(provider);
//            updateView(location);
//        }
//
//        @Override
//        public void onProviderDisabled(String provider) {
//            updateView(null);
//        }
//    };
//
//
//    private void getLocation() {
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        Log.d(TAG, "时间：" + location.getTime());
//        Log.d(TAG, "经度：" + location.getLongitude());
//        //注意：Location location=new Location(LocationManager.GPS_PROVIDER)方式获取的location的各个参数值都是为0。
//
//    }
//
//    GpsStatus.Listener listener = new GpsStatus.Listener() {
//
//        @Override
//        public void onGpsStatusChanged(int event) {
//            switch (event) {
////第一次定位
//                case GpsStatus.GPS_EVENT_FIRST_FIX:
//                    Log.i(TAG, "第一次定位");
//                    break;
//                //卫星状态改变
//                case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
//                    Log.i(TAG, "卫星状态改变");
//                    //获取当前状态
//                    GpsStatus gpsStatus = locationManager.getGpsStatus(null);
//                    //获取卫星颗数的默认最大值
//                    int maxSatellites = gpsStatus.getMaxSatellites();
//                    //创建一个迭代器保存所有卫星
//                    Iterator<GpsSatellite> iters = gpsStatus.getSatellites().iterator();
//                    int count = 0;
//                    while (iters.hasNext() && count <= maxSatellites) {
//                        GpsSatellite s = iters.next();
//                        count++;
//                    }
//                    System.out.println("搜索到：" + count + "颗卫星");
//                    break;
//                //定位启动
//                case GpsStatus.GPS_EVENT_STARTED:
//                    Log.i(TAG, "定位启动");
//                    break;
//                //定位结束
//                case GpsStatus.GPS_EVENT_STOPPED:
//                    Log.i(TAG, "定位结束");
//                    break;
//            }
//        }
//    };
//
//    private void getStatus(){
//        GpsStatus gpsStatus = locationManager.getGpsStatus(null);
//        int maxStatellites = gpsStatus.getMaxSatellites();
//        int costTime = gpsStatus.getTimeToFirstFix();
//        Iterable<GpsSatellite> iterable=gpsStatus.getSatellites();
//            //一般再次转换成Iterator
//        Iterator<GpsSatellite> itrator=iterable.iterator();
//    }
//    //GpsSatellite，定位卫星，包含卫星的方位、高度、伪随机噪声码、信噪比等信息。
//    private void getStatellite(){
//        Iterable<GpsSatellite> iterable=gpsStatus.getSatellites();
////再次转换成Iterator
//        Iterator<GpsSatellite> itrator=iterable.iterator();
////通过遍历重新整理为ArrayList
//        ArrayList<GpsSatellite> satelliteList=new ArrayList<GpsSatellite>();
//        int count=0;
//        int maxSatellites=gpsStatus.getMaxSatellites();
//        while (itrator.hasNext() && count <= maxSatellites) {
//            GpsSatellite satellite = itrator.next();
//            satelliteList.add(satellite);
//            count++;
//        }
//        System.out.println("总共搜索到"+count+"颗卫星");
////输出卫星信息
//        for(int i=0;i<satelliteList.size();i++){
//            //卫星的方位角，浮点型数据
//            System.out.println(satelliteList.get(i).getAzimuth());
//            //卫星的高度，浮点型数据
//            System.out.println(satelliteList.get(i).getElevation());
//            //卫星的伪随机噪声码，整形数据
//            System.out.println(satelliteList.get(i).getPrn());
//            //卫星的信噪比，浮点型数据
//            System.out.println(satelliteList.get(i).getSnr());
//            //卫星是否有年历表，布尔型数据
//            System.out.println(satelliteList.get(i).hasAlmanac());
//            //卫星是否有星历表，布尔型数据
//            System.out.println(satelliteList.get(i).hasEphemeris());
//            //卫星是否被用于近期的GPS修正计算
//            System.out.println(satelliteList.get(i).hasAlmanac());
//        }
//    }
}