package com.rmuhamed.demoapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.model.Entity;
import com.rmuhamed.demoapp.ui.activity.listener.FragmentLifecycleCallback;
import com.rmuhamed.demoapp.utils.ImageLoader;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondaryFragment extends BaseFragment<FragmentLifecycleCallback> {
    public static final String ENTITY = "ENTITY";

    private FragmentLifecycleCallback activityCallback;

    @BindView(R.id.main_picture)
    ImageView mainPicture;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.gender)
    TextView gender;

    public SecondaryFragment() {}

    public static SecondaryFragment newInstance(Entity entity) {
        Bundle args = new Bundle();
        args.putParcelable(ENTITY, entity);

        SecondaryFragment fragment = new SecondaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_secondary, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Entity anEntity = this.getArguments().getParcelable(ENTITY);

        assert anEntity != null;

        this.gender.setText(String.format(Locale.getDefault(), this.getString(R.string.gender), anEntity.getGender()));
        this.description.setText(String.format(Locale.getDefault(), this.getString(R.string.description), anEntity.getDescription()));

        ImageLoader loader = new ImageLoader(this.getContext());
        loader.loadFromUrl(anEntity.getPicture(), this.mainPicture);
    }

    @Override
    public void onResume() {
        super.onResume();

        this.activityCallback.onToolbarShouldBeUpdate(this.getString(R.string.activity_item_list_main_fragment_title));
    }

    /**
     * To handle message collaborations between activity & fragment
     * @param activityCallback
     */
    @Override
    public void setFragmentCallback(FragmentLifecycleCallback activityCallback) {
        this.activityCallback = activityCallback;
    }
}
