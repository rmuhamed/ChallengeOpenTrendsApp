package com.rmuhamed.demoapp.ui.activity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.model.Entity;
import com.rmuhamed.demoapp.ui.fragment.MainFragment;
import com.rmuhamed.demoapp.ui.fragment.SecondaryFragment;

public class ItemListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_item_list);

        this.setupToolbar();

        this.launchFragment(MainFragment.newInstance());
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
    }

    private void launchFragment(Fragment aFragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.root_container, aFragment);
        fragmentTransaction.commit();
    }

    public void displayEntityDetailed(Entity entity) {
        Fragment aFragment = SecondaryFragment.newInstance(entity);

        this.launchFragment(aFragment);
    }
}
