package com.tianye.mobile.well.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tianye.mobile.well.R;
import com.tianye.mobile.well.data.RequestManager;
import com.tianye.mobile.well.util.ToastUtil;

/**
 * Created by lenovo on 2015/3/19.
 */
public class BaseFragment extends Fragment {

    private Bundle bundle;
    public static final String ARG_SECTION_NUMBER = "section_number";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getContentView(),null);
    }

    private int getContentView() {
        return R.layout.pager_with_tabs;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        ((MainActivity) activity).onSectionAttached(
//                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    protected void executeRequest(Request request) {
        RequestManager.addRequest(request, this);
    }

    protected Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ToastUtil.showLong(error.getMessage());
            }
        };
    }
}
