package com.tianye.mobile.well.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tianye.mobile.well.App;

/**
 * Created by lenovo on 2015/4/2.
 */
public class MySingleton {
    private static MySingleton mInstance;

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private MySingleton(Context context){
        mCtx = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String,Bitmap>
                        cache = new LruCache<String,Bitmap>(20);
                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url,bitmap);
                    }
                });
    }

    public static synchronized MySingleton getInstance(Context context){
        if(mInstance == null){
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            //
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }

    void useExample(){
        //Get a request queue
        RequestQueue queue = MySingleton.getInstance(App.getContext()).getRequestQueue();

        StringRequest stringRequest = null;
        MySingleton.getInstance(App.getContext()).addToRequestQueue(stringRequest);

    }

}
