package com.tianye.mobile.well.data;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by lenovo on 2015/4/2.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String,String> headers;
    private final Response.Listener<T> listener;

//
//    public GsonRequest(String url, Response.ErrorListener listener) {
//        super(url, listener);
//    }

    /**
     * Make a Get request and return a parsed object from JSON.
     * @param
     * @param url
     * @param listener
     */

    public GsonRequest(String url,Class<T> clazz,Map<String,String> headers,Response.Listener<T> listener,
                       Response.ErrorListener errorListener){
        super(Method.GET,url,errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;

    }

    public Map<String, String> getHeader() throws AuthFailureError {
        return headers !=null ? headers : super.getHeaders();
    }
    //    public GsonRequest(int method, String url, Response.ErrorListener listener) {
//        super(method, url, listener);
//    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(gson.fromJson(json,clazz),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        listener.onResponse(t);
    }
    class PhotoManager{

        //Called by the PhotoView to get a photo

    }
}
