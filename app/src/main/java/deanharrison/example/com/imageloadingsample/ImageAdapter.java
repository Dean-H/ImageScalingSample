package deanharrison.example.com.imageloadingsample;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

/**
 * Created by Dean on 23/11/2015.
 *
 * The ImageAdapter class is purely just used to hold an array of the images to be displayed
 *
 * For more information on these classes please use :
 *
 * GridView with ImageAdapter info : http://developer.android.com/guide/topics/ui/layout/gridview.html
 *
 * Scaling down large images to Bitmaps : http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
 * Scaling down the images off of the UI thread : http://developer.android.com/training/displaying-bitmaps/process-bitmap.html
 *
 */
public class ImageAdapter extends BaseAdapter{
    private Context mContext;

    private int mImageHeight = 100;
    private int mImageWidth = 100;

    public ImageAdapter(Context context){
        mContext = context;
    }


    @Override
    public int getCount() {
        return mThumbnails.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /*
        This is where the GridView is being provided with ImageViews to display.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(mImageHeight, mImageWidth));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }else{
            imageView = (ImageView) convertView;
        }

        /**
         * To test out the difference in memories uncomment the unscaled image line of code below and comment the scaled.
         *
         * Run the application with one of the below commented and one uncommented, then swap them around and re-run
         * during each run of the application watch the Memory log in your Android Studio
         */

        //imageView.setImageResource(mThumbnails[position]);                //un-scaled image
        new BitmapWorkerTask(imageView).execute(mThumbnails[position]);     //scaled image

        return imageView;
    }

    /*
        An Array just to hold references to the images that will be used in the GridView
     */
    private Integer[] mThumbnails = {
            R.drawable.hydrangea, R.drawable.jellyfishy,
            R.drawable.koalas, R.drawable.penguin,
            R.drawable.tulip, R.drawable.hydrangea, R.drawable.jellyfishy,
            R.drawable.koalas, R.drawable.penguin,
            R.drawable.tulip,  R.drawable.hydrangea, R.drawable.jellyfishy,
            R.drawable.koalas, R.drawable.penguin,
            R.drawable.tulip, R.drawable.tulip,  R.drawable.hydrangea, R.drawable.jellyfishy,
            R.drawable.koalas, R.drawable.penguin,
            R.drawable.tulip, R.drawable.tulip,  R.drawable.hydrangea, R.drawable.jellyfishy,
            R.drawable.koalas, R.drawable.penguin,
            R.drawable.tulip
    };

    /*
        Simple Async Task purely just to process the image scaling in the background,
        ensures that the UI still functions smoothly with no stuttering whilst processing occurs.
     */
    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap>{
        /*
            The WeakReference is used to hold a reference to the ImageView,
            but also allow it to be garbage collected.
         */
        private final WeakReference<ImageView> imageViewWeakReference;

        public BitmapWorkerTask(ImageView imageView) {
            this.imageViewWeakReference = new WeakReference<>(imageView);
        }

        /*
            Begins processing the BitMap in the background.
         */
        @Override
        protected Bitmap doInBackground(Integer... params) {
            return ImageUtils.decodeSampleBitmapFromResource(mContext.getResources(), params[0], mImageWidth, mImageHeight);
        }

        /*
            Once the processing of the BitMap has been completed,
            a quick check is performed to ensure the ImageView is still alive.
            If the ImageView is still alive the scaled down image gets displayed.
         */
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(imageViewWeakReference != null && bitmap != null){
                final ImageView imageView = imageViewWeakReference.get();
                if(imageView != null){
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}


