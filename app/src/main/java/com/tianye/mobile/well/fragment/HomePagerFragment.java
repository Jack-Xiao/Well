package com.tianye.mobile.well.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianye.mobile.well.R;

/**
 * Created by lenovo on 2015/3/19.
 */
public class HomePagerFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(getContentView(),null);

        return contentView;
    }

    public int getContentView(){
        return R.layout.layout_test;
    }
}
