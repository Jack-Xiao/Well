package com.tianye.mobile.well.data;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

/**
 * Created by lenovo on 2015/4/2.
 */
public class LurBitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public LurBitmapCache(int maxSize) {
        super(maxSize);
    }

    @Override
    public Bitmap getBitmap(String s) {
        return null;
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {

    }

    void test(){
        //JsonArrayRequest  JsonObjectRequest.
        String url="";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,
                            null, new Response.Listener() {
            @Override
            public void onResponse(Object o) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        //MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

    }
}
