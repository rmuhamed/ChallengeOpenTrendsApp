package com.rmuhamed.demoapp.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.api.GetEntitiesRequestCallback;
import com.rmuhamed.demoapp.api.RestAPI;
import com.rmuhamed.demoapp.model.Entity;

import java.util.List;

public class MainFragment extends Fragment implements GetEntitiesRequestCallback {

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RestAPI aRestAPI = new RestAPI(this.getContext());
        aRestAPI.getEntities(this);
    }

    @Override
    public void onSuccess(List<Entity> response) {
        //TODO: Fill Adapter with data
    }

    @Override
    public void onError(String errorMessage) {
        //TODO: Inform error using SnackBar with retry logic
    }
}
