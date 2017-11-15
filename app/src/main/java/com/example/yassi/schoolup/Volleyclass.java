package com.example.yassi.schoolup;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Imama on 15/11/2017.
 */

public class Volleyclass {
    private static Volleyclass mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx ;
    private Volleyclass(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {

        if (requestQueue==null)
        {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue ;
    }
    public  static synchronized Volleyclass getmInstance(Context context)
    {
        if(mInstance==null)
        {
            mInstance = new Volleyclass(context) ;
        }
        return mInstance;
    }
    public <T>void addToRequestque(Request<T> request)
    {
        requestQueue.add(request);
    }
}

