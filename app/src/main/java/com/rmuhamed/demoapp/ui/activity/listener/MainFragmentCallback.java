package com.rmuhamed.demoapp.ui.activity.listener;

import com.rmuhamed.demoapp.model.Entity;

/**
 * Created by rmuhamed on 5/9/16.
 */
public interface MainFragmentCallback extends FragmentLifecycleCallback {
    void onEntityShouldBeShowInDetailedMode(Entity entity);
}
