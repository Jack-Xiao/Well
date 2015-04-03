package com.tianye.mobile.well.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.tianye.mobile.well.App;
import com.tianye.mobile.well.R;

import butterknife.InjectView;

/**
 * Created by lenovo on 2015/3/31.
 */
public class GPSFragment2 extends BaseFragment {

    public LocationClient mLocation = null ;
    public MyLocationListener myListener = new MyLocationListener();

    @InjectView(R.id.cityName_gps2)
    TextView cityName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocation = new LocationClient(App.getContext());
        //mLocation.registerLocationListener(myListener);
        setLocationOption();
        mLocation.start();
    }

    private void setLocationOption() {

    }

    @Override
    public void onResume() {
        super.onResume();
        mLocation.registerLocationListener(myListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mLocation.unRegisterLocationListener(myListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(getContent(),null);
    }
    public int getContent(){
        return R.layout.fragment_gps2;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);

    }

    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.d("GPSFragment2", "-------------------------");
            cityName.setText(location.getCity());
        }
    }

    @Override
    public void onDestroy() {
        mLocation.stop();
        super.onDestroy();
    }
}
