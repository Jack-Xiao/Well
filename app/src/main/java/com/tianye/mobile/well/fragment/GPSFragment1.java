package com.tianye.mobile.well.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tianye.mobile.well.R;
import com.tianye.mobile.well.ui.GPS.LocationActivity;
import com.tianye.mobile.well.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2015/3/30.
 */
public class GPSFragment1 extends BaseFragment {
//    public LocationClient mLocationClient;
//    public GeofenceClient mGeofenceClient;
//    public MyLocationListener mMyLocationListener;
//
//    public TextView mLocationResult,logMsg;
//    public TextView trigger,exit;
//    public Vibrator mVibrator;
    public LocationManager locationManager;
    private ListView mFunctionList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mLocationClient = new LocationClient(App.getContext());
//        mMyLocationListener = new MyLocationListener();
//        mLocationClient.registerLocationListener(mMyLocationListener);
//        mGeofenceClient = new GeofenceClient(App.getContext());
//
//        mVibrator = (Vibrator)App.getContext().getSystemService(Service.VIBRATOR_SERVICE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            ToastUtil.showLong("请开启GPS导航...");
            //返回开启GPS 导航设置界面
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, 0); // 此为设置完成后返回到获取界面
            return;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.function_list,null);
        mFunctionList = (ListView)contentView.findViewById(R.id.function_list);
        mFunctionList.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_expandable_list_item_1,getData()));

        return contentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFunctionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class TargetClass = null;
                switch (position){
                    case 0:
                        TargetClass = LocationActivity.class;
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
                if(TargetClass !=null){
                    Intent intent = new Intent(getActivity(),TargetClass);
                    startActivity(intent);
                }
            }
        });
    }

    private List<String> getData(){

        List<String> data = new ArrayList<String>();
        data.add("基础定位功能");
        data.add("地理围栏功能");
        data.add("位置消息提醒");
        data.add("常见问题说明");

        return data;
    }
}
