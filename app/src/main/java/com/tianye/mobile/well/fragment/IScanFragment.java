package com.tianye.mobile.well.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tianye.mobile.well.R;
import com.tianye.mobile.well.util.qr_codescan.MipcaActivityCapture;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2015/3/19.
 */
public class IScanFragment extends BaseFragment {
    private final static int SCANNIN_GREQUEST_CODE = 1;

    @InjectView(R.id.result)
    TextView mTextView ;

    @InjectView(R.id.qrcode_bitmap)
    ImageView mImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @OnClick(R.id.btnScan) void Click(){
        Intent intent = new Intent();
        intent.setClass(getActivity(), MipcaActivityCapture.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_scan,null);
        ButterKnife.inject(this, contentView);

        return contentView;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if(resultCode == Activity.RESULT_OK){
                    Bundle bundle = data.getExtras();

                    mTextView.setText(bundle.getString("result"));
                    mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }
}
