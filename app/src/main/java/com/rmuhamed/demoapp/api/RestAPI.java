package com.rmuhamed.demoapp.api;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.rmuhamed.demoapp.BuildConfig;
import com.rmuhamed.demoapp.api.queue.SingletonRequestQueue;
import com.rmuhamed.demoapp.api.request.GetEntitiesRequest;
import com.rmuhamed.demoapp.api.request.GetEntitiesRequestCallback;
import com.rmuhamed.demoapp.model.User;

import java.util.List;

/**
 * Created by rmuhamed on miércoles.
 */
public class RestAPI implements IRestAPI {
    private static final String GET_URL = BuildConfig.SERVER_URL;

    private static RestAPI instance;
    private SingletonRequestQueue requestQueue;

    private RestAPI() {}

    public static RestAPI getInstance() {
        if (instance == null) {
            instance = new RestAPI();
        }

        return instance;
    }

    public RestAPI initialize(Context context) {
        this.requestQueue = SingletonRequestQueue.getInstance(context.getApplicationContext());

        return this;
    }

    public void getEntities(final GetEntitiesRequestCallback listener) {
        GetEntitiesRequest jsonRequest = new GetEntitiesRequest(GET_URL,
                new Response.Listener<List<User>>() {
                    @Override
                    public void onResponse(List<User> entities) {
                        listener.onSuccess(entities);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        listener.onError(volleyError.getMessage());
                    }
                });

        jsonRequest.setShouldCache(true);

        this.requestQueue.addToRequestQueue(jsonRequest);
    }
}
