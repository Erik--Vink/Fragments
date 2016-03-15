package com.example.erik.fragments.old;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.erik.fragments.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadPokeImageTask extends AsyncTask<String, Void, Bitmap>
{
    private final WeakReference<ImageView> imageViewReference;

    public DownloadPokeImageTask(ImageView view)
    {
        imageViewReference = new WeakReference<ImageView>(view);
    }

    protected Bitmap doInBackground(String... params)
    {
        return downloadBitmap(params[0]);
    }

    protected void onPostExecute(Bitmap result)
    {
        if (isCancelled()) {
            result = null;
        }

        if(imageViewReference != null){
            ImageView imageView = imageViewReference.get();
            if (imageView != null) {
                if (result != null){
                    imageView.setImageBitmap(result);
                }
                else{
                    Drawable placeholder = imageView.getContext().getResources().getDrawable(R.drawable.placeholder);
                    imageView.setImageDrawable(placeholder);                }
            }

        }
    }

    private Bitmap downloadBitmap(String url) {
        try {
            Log.v("Getting image: ", url);
            URL uri = new URL(url);
            InputStream inputstream = uri.openStream(); //it's an image, not a webpage, so just stream it.
            if(inputstream != null){
                Bitmap bitmap = BitmapFactory.decodeStream(inputstream); //which makes this really easy.
                return bitmap;
            }
        } catch ( Exception e) {
            Log.e("Error in worker thread:", e.getMessage(), e);
        }

        return null;
    }

}
