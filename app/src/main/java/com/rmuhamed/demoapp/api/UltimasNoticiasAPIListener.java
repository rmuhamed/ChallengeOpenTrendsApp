package com.rmuhamed.demoapp.api;

import com.android.volley.VolleyError;

public interface UltimasNoticiasAPIListener {

    void onSuccess(WSResponse response);

    void onError(VolleyError volleyError);

}
