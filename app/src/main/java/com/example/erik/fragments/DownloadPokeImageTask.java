package com.example.erik.fragments;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Erik on 9-3-2016.
 */
public class DownloadPokeImageTask extends AsyncTask<PokemonModel, Void, Drawable>
{
    public DownloadPokeImageTask()
    {

    }

    protected Drawable doInBackground(PokemonModel... pokemon)
    {
        Drawable d = null;
        PokemonModel current = pokemon[0];
        try {
            InputStream is = (InputStream) new URL(current.getImageLink()).getContent();
            d = Drawable.createFromStream(is, "test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

    protected void onPostExecute(Drawable result)
    {

    }
}
