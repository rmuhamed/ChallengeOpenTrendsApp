package com.rmuhamed.demoapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmuhamed.demoapp.R;
import com.rmuhamed.demoapp.ui.adapter.listener.ViewHolderClickCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rmuhamed on jueves.
 */
public class EntityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ViewHolderClickCallback itemClickCallback;

    @BindView(R.id.item_label_tv)
    TextView label;

    @BindView(R.id.item_picture_iv)
    ImageView picture;


    public EntityViewHolder(ViewHolderClickCallback callback, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        this.itemClickCallback = callback;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.itemClickCallback.onItemClicked(this.getAdapterPosition());
    }
}
