package com.rmuhamed.demoapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rmuhamed.demoapp.R;

/**
 * Created by rmuhamed on jueves.
 */
public class RemoteImageLoader {

    private final Context context;
    private final String remoteUrl;
    private int placeHolderResId;

    public RemoteImageLoader(Context context, String remoteUrl) {
        this.remoteUrl = remoteUrl;
        this.context = context;
        this.placeHolderResId = R.mipmap.ic_launcher;
    }

    public void loadInto(ImageView imageView) {
        Glide.with(this.context)
                .load(this.remoteUrl)
                .asBitmap()
                .placeholder(this.placeHolderResId)
                .centerCrop()
                .into(imageView);
    }

    public void setPlaceHolder(int placeHolderResourceId) {
        this.placeHolderResId = placeHolderResourceId;
    }
}
