package deanharrison.example.com.imageloadingsample;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by User1 on 23/11/2015.
 *
 * This Utilities class was purely just created to separate the code for simplicities sake.
 *
 * For more information on these classes please use :
 *
 * GridView with ImageAdapter info : http://developer.android.com/guide/topics/ui/layout/gridview.html
 *
 * Scaling down large images to Bitmaps : http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
 * Scaling down the images off of the UI thread : http://developer.android.com/training/displaying-bitmaps/process-bitmap.html
 *
 */
public class ImageUtils {
    /*
        This method is used to return a scaled down Bitmap of an image.
        You provide the height and width that you want the image to fit, along with resources and the image ID.

     */
    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /*
        This method provides the inSampleSize used for decoding the image.
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth){
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}
