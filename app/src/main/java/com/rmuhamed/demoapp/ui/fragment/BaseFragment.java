package com.rmuhamed.demoapp.ui.fragment;

import android.support.v4.app.Fragment;

import com.rmuhamed.demoapp.ui.activity.listener.FragmentLifecycleCallback;

/**
 * Created by rmuhamed on 5/9/16.
 */
public abstract class BaseFragment<T extends FragmentLifecycleCallback> extends Fragment {
    public abstract void setFragmentCallback(T activityCallback);
}
