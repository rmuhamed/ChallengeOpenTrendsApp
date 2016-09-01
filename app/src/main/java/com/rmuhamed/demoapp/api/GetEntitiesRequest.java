package com.rmuhamed.demoapp.api;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.google.gson.Gson;
import com.rmuhamed.demoapp.model.Entity;

import java.util.ArrayList;
import java.util.List;

public class GetEntitiesRequest extends JsonRequest<List<Entity>> {

    public GetEntitiesRequest(String url, Response.Listener<List<Entity>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);

        this.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    protected Response<List<Entity>> parseNetworkResponse(NetworkResponse networkResponse) {
        Response<List<Entity>> result = null;

        try {
            try {
                Gson gson = new Gson();
                WSResponse response = gson.fromJson(networkResponse.data.toString(), WSResponse.class);

                List<Entity> entities = this.getModelInformationFromResponse(response);

                result = Response.success(entities, HttpHeaderParser.parseCacheHeaders(networkResponse));

            } catch (Exception e) {
                result = Response.error(new VolleyError());
            }

        } catch (Exception e) {
            result = Response.error(new VolleyError());
        }

        return result;
    }

    private List<Entity> getModelInformationFromResponse(WSResponse response) {
        List<Entity> entities = null;
        if (response != null && response.getResults() != null) {
            entities = new ArrayList<>();
            for (Result aResult : response.getResults()) {
                entities.add(aResult.getEntity());
            }
        }
        return entities;
    }
}