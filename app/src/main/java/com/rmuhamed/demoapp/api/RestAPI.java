package com.rmuhamed.demoapp.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.rmuhamed.demoapp.api.request.GetEntitiesRequest;
import com.rmuhamed.demoapp.api.request.GetEntitiesRequestCallback;
import com.rmuhamed.demoapp.model.Entity;

import java.util.List;

/**
 * Created by rmuhamed on miércoles.
 */
public class RestAPI implements IRestAPI {
//VV8U-IIHJ-LJGC-A7QK
    private static final String GET_URL = "https://randomapi.com/api/?key=LMW0-SW97-ISC4-FF25&id=t60ldyb&results=20";
    private static RestAPI instance;
    private RequestQueue requestQueue;

    public static RestAPI getInstance() {
        if (instance == null) {
            instance = new RestAPI();
        }

        return instance;
    }

    public RestAPI initialize(Context context) {
        this.requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        return this;
    }

    public void getEntities(final GetEntitiesRequestCallback listener) {
        GetEntitiesRequest jsonRequest = new GetEntitiesRequest(GET_URL,
                new Response.Listener<List<Entity>>() {
                    @Override
                    public void onResponse(List<Entity> entities) {
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

        this.requestQueue.add(jsonRequest);
    }

}
