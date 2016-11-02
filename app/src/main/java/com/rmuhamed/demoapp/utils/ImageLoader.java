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
    private Context context;
    private int placeHolderResId;

    private ImageLoader() {
        this.placeHolderResId = R.mipmap.ic_launcher;
    }

    public void placeHolder(int placeHolderResourceId) {
        this.placeHolderResId = placeHolderResourceId;
    }

    public static ImageLoader getInstance() {
        return new ImageLoader();
    }

    public ImageLoader with(Context context) {
        this.context = context;

        return this;
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
}
