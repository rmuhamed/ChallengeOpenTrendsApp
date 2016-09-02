package com.rmuhamed.demoapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.model.Entity;
import com.rmuhamed.demoapp.utils.RemoteImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmuhamed on jueves.
 */
public class EntityRecyclerAdapter extends RecyclerView.Adapter<EntityViewHolder> {
    private final Context context;
    private List<Entity> items;

    public EntityRecyclerAdapter(Context context){
        this.items = new ArrayList<>();
        this.context = context;
    }

    @Override
    public EntityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);

        return new EntityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EntityViewHolder holder, int position) {
        Entity anEntityForPosition = this.items.get(position);

        assert anEntityForPosition != null;

        holder.label.setText(anEntityForPosition.getGender() != null ? anEntityForPosition.getGender() : "Some info for ITEM");

        RemoteImageLoader remoteImageLoader = new RemoteImageLoader(this.context, anEntityForPosition.getThumbnail());
        remoteImageLoader.loadInto(holder.picture);
    }

    @Override
    public int getItemCount() {
        return this.items != null ? this.items.size() : 0;
    }

    public void fill(List<Entity> entities) {
        this.items = entities;
        //FORCE redraw
        this.notifyDataSetChanged();
    }
}
