package com.tianye.mobile.well.ui.GPS;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.tianye.mobile.well.App;
import com.tianye.mobile.well.R;
import com.tianye.mobile.well.fragment.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lenovo on 2015/3/30.
 */
public class LocationActivity extends BaseActivity {
    private LocationClient mLocationClient;
    private TextView mLocationResult,mModeInfor;
    private Button startLocation;
    private RadioGroup selectMode,selectCoordinates;
    private EditText frequence;
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
    private String tempcoor = "gcj02";
    private CheckBox checkGeoLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        ButterKnife.inject(this);

        mLocationClient = ((App)getApplication()).mLocationClient;
        mLocationResult = (TextView)findViewById(R.id.textView1);
        mModeInfor= (TextView)findViewById(R.id.modeinfor);
        mModeInfor.setText(getString(R.string.hight_accuracy_desc));
        ((App)getApplication()).mLocationResult = mLocationResult;
        frequence = (EditText)findViewById(R.id.frequence);
        checkGeoLocation = (CheckBox)findViewById(R.id.geolocation);
        startLocation = (Button)findViewById(R.id.addfence);

        selectMode = (RadioGroup)findViewById(R.id.selectMode);
        selectCoordinates= (RadioGroup)findViewById(R.id.selectCoordinates);
        selectMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                String ModeInformation = null;
                switch (checkedId) {
                    case R.id.radio_hight:
                        tempMode = LocationClientOption.LocationMode.Hight_Accuracy;
                        ModeInformation = getString(R.string.hight_accuracy_desc);
                        break;
                    case R.id.radio_low:
                        tempMode = LocationClientOption.LocationMode.Battery_Saving;
                        ModeInformation = getString(R.string.saving_battery_desc);
                        break;
                    case R.id.radio_device:
                        tempMode = LocationClientOption.LocationMode.Device_Sensors;
                        ModeInformation = getString(R.string.device_sensor_desc);
                        break;
                    default:
                        break;
                }
                mModeInfor.setText(ModeInformation);
            }
        });
        selectCoordinates.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.radio_gcj02:
                        tempcoor="gcj02";
                        break;
                    case R.id.radio_bd09ll:
                        tempcoor="bd09ll";
                        break;
                    case R.id.radio_bd09:
                        tempcoor="bd09";
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @OnClick(R.id.addfence) void start(){
        InitLocation();

        if(startLocation.getText().equals(getString(R.string.startlocation))){
            mLocationClient.start();
            startLocation.setText(getString(R.string.stoplocation));
        }else{
            mLocationClient.stop();
            startLocation.setText(getString(R.string.startlocation));
        }
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        mLocationClient.stop();
        super.onStop();
    }

    private void InitLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//设置定位模式
        option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
        int span=1000;
        try {
            span = Integer.valueOf(frequence.getText().toString());
        } catch (Exception e) {
            // TODO: handle exception
        }
        option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(checkGeoLocation.isChecked());
        mLocationClient.setLocOption(option);
//        try{
//            BDLocation location = mLocationClient.getLastKnownLocation();
//            mLocationResult.setText(location.getCity());
//
//        }catch(Exception e){
//            Log.d("Location ---------",e.getMessage());
//        }


    }
//    @OnClick(R.id.addfence) void start(View.OnClickListener()){
//
//    };

}
