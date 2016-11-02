package com.rmuhamed.demoapp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.model.Entity;
import com.rmuhamed.demoapp.ui.activity.listener.FragmentLifecycleCallback;
import com.rmuhamed.demoapp.ui.activity.listener.MainFragmentCallback;
import com.rmuhamed.demoapp.ui.fragment.MainFragment;
import com.rmuhamed.demoapp.ui.fragment.SecondaryFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemListActivity extends AppCompatActivity implements MainFragmentCallback, FragmentLifecycleCallback {
    private static final String ONLY_PUSH_MODE = "ONLY_PUSH_MODE";
    private static final String REPLACE_MODE = "REPLACE_MODE";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_item_list);

        ButterKnife.bind(this);

        this.setupToolbar();

        MainFragment mainFragment = MainFragment.newInstance();
        mainFragment.setFragmentCallback(this);

        this.launchFragment(mainFragment, ONLY_PUSH_MODE);
    }

    @Override
    public void onToolbarTitleShouldBeUpdated(String toolbarTitle) {
        //UPDATE TITLE FOR TOOLBAR
        this.toolbar.setTitle(toolbarTitle);
    }

    @Override
    public void onEntityShouldBeShownInDetailedMode(Entity entity) {
        SecondaryFragment fragment = SecondaryFragment.newInstance(entity);
        fragment.setFragmentCallback(this);
        this.launchFragment(fragment, REPLACE_MODE);
    }

    private void setupToolbar() {
        this.toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.toolbar.setTitle(R.string.activity_item_list_main_fragment_title);
        this.setSupportActionBar(this.toolbar);
    }

    private void launchFragment(Fragment aFragment, String mode) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        if (mode.equals(ONLY_PUSH_MODE)) {
            fragmentTransaction.add(R.id.fragment_container, aFragment);
        } else {
            fragmentTransaction.replace(R.id.fragment_container, aFragment);
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }
}
