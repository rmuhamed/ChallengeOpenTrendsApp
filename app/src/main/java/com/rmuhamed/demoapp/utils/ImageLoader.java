package com.rmuhamed.demoapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.rmuhamed.demoapp.R;

/**
 * Created by rmuhamed on jueves.
 */
public class ImageLoader {

    private final Context context;
    private int placeHolderResId;

    public ImageLoader(Context context) {
        this.context = context;
        this.placeHolderResId = R.mipmap.ic_launcher;
    }

    public void loadFromUrl(String url, ImageView iv) {
        Glide.with(this.context)
                .load(url)
                .asBitmap()
                .placeholder(this.placeHolderResId)
                .centerCrop()
                .into(iv);
    }

    public void loadFromResource(int resourceId, ImageView iv) {
        Glide.with(this.context)
                .load(resourceId)
                .asBitmap()
                .placeholder(this.placeHolderResId)
                .centerCrop()
                .into(iv);
    }

    public void setPlaceHolder(int placeHolderResourceId) {
        this.placeHolderResId = placeHolderResourceId;
    }
}
