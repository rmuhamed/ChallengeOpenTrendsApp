package com.rmuhamed.demoapp.api;

import com.rmuhamed.demoapp.api.request.GetEntitiesRequestCallback;

/**
 * Created by rmuhamed on miércoles.
 */
public interface IRestAPI {
    /**
     * GET through https://randomapi.com/api/
     * @param aCallback
     */
    void getEntities(GetEntitiesRequestCallback aCallback);
}
