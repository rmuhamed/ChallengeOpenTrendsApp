package com.rmuhamed.demoapp.api.request;

import com.rmuhamed.demoapp.model.User;

import java.util.List;

public interface GetEntitiesRequestCallback {
    void onSuccess(List<User> response);

    void onError(String errorMessage);

}
