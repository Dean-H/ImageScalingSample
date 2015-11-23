package deanharrison.example.com.imageloadingsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

/*
    This Application is purely just to demo how to scale images to fit smaller ImageViews to handle large images
    without running out of space.

    From following the android developers documentation it was made.

    For more information on these classes please use :
 *
 * GridView with ImageAdapter info : http://developer.android.com/guide/topics/ui/layout/gridview.html
 *
 * Scaling down large images to Bitmaps : http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
 * Scaling down the images off of the UI thread : http://developer.android.com/training/displaying-bitmaps/process-bitmap.html
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Simple GridView just as a holder for the images.
         */
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new ImageAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
