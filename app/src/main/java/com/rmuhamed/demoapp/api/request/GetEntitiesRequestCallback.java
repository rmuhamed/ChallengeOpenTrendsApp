package com.rmuhamed.demoapp.api.request;

import com.rmuhamed.demoapp.model.Entity;

import java.util.List;

public interface GetEntitiesRequestCallback {
    void onSuccess(List<Entity> response);

    void onError(String errorMessage);

}
