package com.tianye.mobile.well.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tianye.mobile.well.App;

/**
 * Created by lenovo on 2015/4/1.
 */
public class RequestManager {
    public static RequestQueue mRequestQueue = Volley.newRequestQueue(App.getContext());

    private RequestManager(){

    }
    public static void addRequest(Request<?> request,Object tag){
        if(tag !=null){
          request.setTag(tag);
        }
        mRequestQueue.add(request);
    }

    public static void cancelAll(Object tag){
        mRequestQueue.cancelAll(tag);
    }


    public static void addRequest(JsonObjectRequest jsonObjectRequest) {

    }
}
