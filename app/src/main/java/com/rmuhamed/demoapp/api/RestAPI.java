package com.rmuhamed.demoapp.api;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.rmuhamed.demoapp.model.Entity;

import java.util.List;

/**
 * Created by rmuhamed on miércoles.
 */
public class RestAPI implements IRestAPI {

    private static final String GET_URL = "https://randomapi.com/api/?key=LMW0-SW97-ISC4-FF25&id=t60ldyb&results=20";
    private final Context context;

    public RestAPI(Context context) {
        this.context = context;
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

        RequestQueue requestQueue = Volley.newRequestQueue(this.context.getApplicationContext());
        requestQueue.add(jsonRequest);
    }

}
