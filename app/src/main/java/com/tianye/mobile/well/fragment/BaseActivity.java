package com.tianye.mobile.well.fragment;

import android.support.v7.app.ActionBarActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.tianye.mobile.well.data.RequestManager;
import com.tianye.mobile.well.util.ToastUtil;

/**
 * Created by lenovo on 2015/3/19.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RequestManager.cancelAll(this);
    }

    protected void executeRequest(Request<?> request) {
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
