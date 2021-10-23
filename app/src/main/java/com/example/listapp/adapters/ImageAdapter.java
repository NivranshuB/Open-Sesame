package com.example.listapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * This class is responsible to create the image slider view which is shown in the DetailsActivity
 * page. The current iteration only displays three images however the ImageAdapter can also handle
 * any number of images for the image gallery. The images are stored as strings that represent the
 * image file in the drawable folder.
 */
public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> imageNames;

    public ImageAdapter(Context context, List<String> images) {
        mContext = context;
        imageNames = images;;
    }

    /**
     * @return The number of images in this adapter
     */
    @Override
    public int getCount() {
        return imageNames.size();
    }

    /**
     * @param view
     * @param object
     * @return true if view belongs to this object, false otherwise
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * @param container
     * @param position
     * @return Return the image at the position specified
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int imageId = mContext.getResources().getIdentifier(
                imageNames.get(position), "drawable", mContext.getPackageName());

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(imageId);
        container.addView(imageView, 0);
        return imageView;
    }

    /**
     * Removes an image view object from a view group container
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
