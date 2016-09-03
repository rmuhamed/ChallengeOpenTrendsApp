package com.rmuhamed.demoapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.model.Entity;

/**
 * Created by rmuhamed on martes.
 */
public class SecondaryFragment extends Fragment {

    public static final String ENTITY = "ENTITY";

    public SecondaryFragment() {}

    public static Fragment newInstance(Entity entity) {

        Bundle args = new Bundle();
        args.putParcelable(ENTITY, entity);

        SecondaryFragment fragment = new SecondaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_secondary, container, false);
    }
}
