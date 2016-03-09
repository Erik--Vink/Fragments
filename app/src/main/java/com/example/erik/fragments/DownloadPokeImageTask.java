package com.example.erik.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Erik on 9-3-2016.
 */
public class DownloadPokeImageTask extends AsyncTask<PokemonModel, Void, Bitmap>
{
    private ImageView view;

    public DownloadPokeImageTask(ImageView view)
    {
        this.view = view;
    }

    protected Bitmap doInBackground(PokemonModel... pokemon)
    {
        Bitmap d = null;
        PokemonModel current = pokemon[0];

        try {
            Log.v("Getting image: ", current.getImageLink());
            URL url = new URL(current.getImageLink());
            InputStream in = url.openStream(); //it's an image, not a webpage, so just stream it.
            d = BitmapFactory.decodeStream(in); //which makes this really easy.
            in.close();
        } catch ( Exception e) {
            Log.e("Error in worker thread:", e.getMessage(), e);
        }

        return d;
    }

    protected void onPostExecute(Bitmap result)
    {
        view.setImageBitmap(result);
        view.postInvalidate();//And wakeup the UI thread to update.
    }
}
