package fi.jokufirma.loadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);

        String image = "https://xvp.akamaized.net/assets/illustrations/one-vpn-for-all-your-android-devices-852aad6f0822c045cb6175252fa65877.png";
        DownloadImage task = new DownloadImage();
        task.execute(image);
    }

    private class DownloadImage extends AsyncTask<String,Void,Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {
            URL imageURL;
            Bitmap bitmap = null;
            try {
                imageURL = new URL(urls[0]);
                InputStream in = imageURL.openStream();
                bitmap = BitmapFactory.decodeStream(in);
            }
            catch (Exception e) {
                Log.d("Kuvassa ongelma",e.getMessage());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
            else {
                Toast.makeText(getApplicationContext(),"Kuva ei latautunut", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
