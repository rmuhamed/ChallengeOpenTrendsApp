package com.rmuhamed.demoapp.api;

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
