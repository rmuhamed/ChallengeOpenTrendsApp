package com.rmuhamed.demoapp.api.queue;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class SingletonRequestQueue {
    private static SingletonRequestQueue instance;
    private RequestQueue requestQueue;

    private SingletonRequestQueue(Context applicationContext) {
        this.requestQueue = this.getRequestQueue(applicationContext);
    }

    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonRequestQueue(context);
        }
        return instance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        this.requestQueue.add(req);
    }

    public void cancelAllRequests() {
        this.requestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return true;
            }
        });
    }

    private RequestQueue getRequestQueue(Context applicationContext) {
        if (this.requestQueue == null) {
            this.requestQueue = Volley.newRequestQueue(applicationContext);
        }
        return this.requestQueue;
    }
}
