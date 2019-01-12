package com.mypushtak.app.Bean;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySignleton {

    private static  MySignleton mInstance;
    private RequestQueue mrequesQueue;
    private static Context mctx;

    private MySignleton(Context context)
    {
        mctx =context;
        mrequesQueue =getRequestqueue();
    }

    private RequestQueue getRequestqueue() {

        if(mrequesQueue ==null)
        {
            mrequesQueue = Volley.newRequestQueue(mctx.getApplicationContext());

        }

        return mrequesQueue;
    }


    public static synchronized MySignleton getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance =new MySignleton(context);
        }
        return  mInstance;
    }


    public <T> void addToRequestqueue(Request<T> request)
    {
        mrequesQueue.add(request);
    }

}
