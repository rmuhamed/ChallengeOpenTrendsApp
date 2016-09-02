package com.rmuhamed.demoapp.ui.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.api.request.GetEntitiesRequestCallback;
import com.rmuhamed.demoapp.api.RestAPI;
import com.rmuhamed.demoapp.model.Entity;
import com.rmuhamed.demoapp.ui.adapter.EntityRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements GetEntitiesRequestCallback {
    private EntityRecyclerAdapter anAdapter;

    private View rootView;

    @BindView(R.id.recycler_list)
    RecyclerView anEntityRecyclerList;

    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, this.rootView);

        this.setupRecyclerList();

        return this.rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RestAPI aRestAPI = new RestAPI(this.getContext());
        aRestAPI.getEntities(this);
    }

    @Override
    public void onSuccess(List<Entity> entities) {
        this.anAdapter.fill(entities);
    }

    @Override
    public void onError(String errorMessage) {
        Snackbar.make(this.rootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    private void setupRecyclerList() {
        LinearLayoutManager aLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);

        this.anAdapter = new EntityRecyclerAdapter(this.getContext());

        this.anEntityRecyclerList.setLayoutManager(aLayoutManager);
        this.anEntityRecyclerList.setAdapter(this.anAdapter);
    }
}
