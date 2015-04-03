package com.tianye.mobile.well.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tianye.mobile.well.R;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by lenovo on 2015/4/2.
 */
public class VolleyFragment extends BaseFragment {
    public static final String TAG=VolleyFragment.class.getSimpleName();
    HttpStack mStack;

    RequestQueue mQueue;
    @InjectView(R.id.request)
    EditText mRequest;
    @InjectView(R.id.response)
    EditText mResponse;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View contentView = inflater.inflate(R.layout.volley_fragment, null);
        ButterKnife.inject(this, contentView);
        return contentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.connect)
    void click() {
        mQueue = Volley.newRequestQueue(getActivity());
        String url = mRequest.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        mResponse.setText(s.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mResponse.setText(volleyError.getMessage());
            }
        });
        mQueue.add(stringRequest);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mQueue != null){
            mQueue.cancelAll(TAG);
        }
    }
    void createConnect(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            //HttpConnection = ();
        }
        Network network = new BasicNetwork(mStack);
    }
    void createQueue(){
        RequestQueue requestQueue;
        //Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(),1024 * 1024); //1MB cap
        //set up the network to use HttpURLConnection as the Http client;
        Network network = new BasicNetwork(new HurlStack());
        //Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache,network);
        //start the queue.
        requestQueue.start();

        String url = "http://www.myurl.com";
        //Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(stringRequest);
    }

    private File getCacheDir() {
        return null;
    }

}

