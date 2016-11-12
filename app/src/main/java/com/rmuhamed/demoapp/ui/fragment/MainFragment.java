package com.rmuhamed.demoapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.api.RestAPI;
import com.rmuhamed.demoapp.api.request.GetEntitiesRequestCallback;
import com.rmuhamed.demoapp.model.User;
import com.rmuhamed.demoapp.ui.activity.listener.MainFragmentCallback;
import com.rmuhamed.demoapp.ui.adapter.EntityRecyclerAdapter;
import com.rmuhamed.demoapp.ui.adapter.listener.EntitySelectedCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends BaseFragment<MainFragmentCallback> implements GetEntitiesRequestCallback, EntitySelectedCallback {
    private EntityRecyclerAdapter anAdapter;
    private MainFragmentCallback activityCallback;

    private View rootView;

    @BindView(R.id.recycler_list)
    RecyclerView anEntityRecyclerList;

    @BindView(R.id.progress)
    ProgressBar progress;

    public MainFragment() {}

    public static MainFragment newInstance() {
        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Retrieve data from WS
        RestAPI.getInstance().initialize(this.getContext()).getEntities(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, this.rootView);

        return this.rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LinearLayoutManager aLayoutManager =
                new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);

        this.anAdapter = new EntityRecyclerAdapter(this, this.getContext());

        this.anEntityRecyclerList.setLayoutManager(aLayoutManager);
        this.anEntityRecyclerList.setAdapter(this.anAdapter);
    }

    @Override
    public void onSuccess(List<User> entities) {
        this.updateRootViewStatus();

        this.anAdapter.clear();

        this.anAdapter.fill(entities);
    }

    @Override
    public void onResume() {
        super.onResume();

        //TODO: RM - Review this
        if (this.activityCallback != null) {
            this.activityCallback.onToolbarTitleShouldBeUpdated(this.getString(R.string.activity_item_list_main_fragment_title));
        } else {
            Crashlytics.log("ActivityCallback not set");
        }
    }

    @Override
    public void onError(String errorMessage) {
        this.progress.setVisibility(View.GONE);

        Snackbar.make(this.rootView, errorMessage, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onEntitySelected(User entity) {
        this.activityCallback.onEntityShouldBeShownInDetailedMode(entity);
    }

    /**
     * To handle message collaborations between activity & fragment
     * @param activityCallback
     */
    @Override
    public void setFragmentCallback(MainFragmentCallback activityCallback) {
        this.activityCallback = activityCallback;
    }

    private void updateRootViewStatus() {
        this.progress.setVisibility(View.GONE);
        this.anEntityRecyclerList.setVisibility(View.VISIBLE);
    }
}
