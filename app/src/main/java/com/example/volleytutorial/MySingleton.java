package com.example.volleytutorial;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

import java.io.File;

public class MySingleton {
    private static volatile MySingleton instance;
    private RequestQueue requestQueue;
   private Context context;
    private MySingleton(Context context)
    {
        this.context=context;
        requestQueue=getRequestQueue();
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue==null) {
            Cache cache = new DiskBasedCache(context.getCacheDir(), 1024 * 1024);
            Network network = new BasicNetwork(new HurlStack());

            requestQueue = new RequestQueue(cache, network);
            requestQueue.start();

        }
        return requestQueue;
    }

    public static MySingleton getInstance(Context context)
    {
        synchronized (MySingleton.class)
        {
            if(instance==null)
            {
                instance=new MySingleton(context.getApplicationContext());
            }
        }
        return instance;
    }

    public<T> void addToRequestQueue(Request<T> request)
    {
                requestQueue.add(request);
    }
}
