package com.berishaerblin.moneymanager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Elvudin on 15-Jan-17.
 */

public class MySingleton {

    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context1) {
        context = context1;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue() {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return requestQueue;
    }

    public static synchronized MySingleton getInstance(Context ctx) {

        if (mInstance == null) {
            mInstance = new MySingleton(ctx);
        }

        return mInstance;
    }

    public <T> void addToRequestque(Request<T> request) {
        requestQueue.add(request);
    }
}
