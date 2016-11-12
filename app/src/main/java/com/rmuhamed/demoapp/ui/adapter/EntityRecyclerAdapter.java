package com.rmuhamed.demoapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.model.User;
import com.rmuhamed.demoapp.ui.adapter.listener.EntitySelectedCallback;
import com.rmuhamed.demoapp.ui.adapter.listener.ViewHolderClickCallback;
import com.rmuhamed.demoapp.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmuhamed on jueves.
 */
public class EntityRecyclerAdapter extends RecyclerView.Adapter<EntityViewHolder> implements ViewHolderClickCallback {
    private static final String DUMMY_LABEL_FOR_RECYCLER_ITEM = "Some info for ITEM";

    private final Context context;
    private final EntitySelectedCallback entitySelectedCallback;
    private List<User> items;

    public EntityRecyclerAdapter(EntitySelectedCallback mainFragmentCallback, Context context) {
        this.items = new ArrayList<>();
        this.context = context;
        this.entitySelectedCallback = mainFragmentCallback;
    }

    @Override
    public EntityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);

        return new EntityViewHolder(this, itemView);
    }

    @Override
    public void onBindViewHolder(EntityViewHolder holder, int position) {
        User anEntityForPosition = this.items.get(position);

        assert anEntityForPosition != null;

        holder.label.setText(anEntityForPosition.getSport() != null
                ? anEntityForPosition.getSport()
                : DUMMY_LABEL_FOR_RECYCLER_ITEM);

        ImageLoader.getInstance()
                .with(this.context)
                .loadFromUrl(anEntityForPosition.getProfileImage(), holder.picture);
    }

    @Override
    public int getItemCount() {
        return this.items != null ? this.items.size() : 0;
    }

    public void fill(List<User> entities) {
        this.items = entities;
        //FORCE redraw
        this.notifyDataSetChanged();
    }

    @Override
    public void onItemClicked(int position) {
        User aSelectedEntity = this.items.get(position);

        this.entitySelectedCallback.onEntitySelected(aSelectedEntity);
    }

    public void clear() {
        if (this.items != null && !this.items.isEmpty()) {
            this.items.clear();
        }
    }
}
