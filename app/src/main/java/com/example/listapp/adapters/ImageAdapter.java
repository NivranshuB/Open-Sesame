package com.example.listapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.listapp.R;

import java.util.List;


public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> imageNames;

    public ImageAdapter(Context context, List<String> images) {
        mContext = context;
        imageNames = images;;
    }

    @Override
    public int getCount() {
        return imageNames.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int imageId = mContext.getResources().getIdentifier(
                imageNames.get(position), "drawable", mContext.getPackageName());

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageId);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
