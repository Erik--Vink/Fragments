package com.example.erik.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class PokemonAdapter extends ArrayAdapter<Pokemon>{

    ArrayList<Pokemon> pokemonList;
    LayoutInflater inflater;
    int Resource;
    ViewHolder holder;

    public PokemonAdapter(Context context, int resource, ArrayList<Pokemon> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        pokemonList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(Resource, null);
            holder.imageview = (ImageView) view.findViewById(R.id.ivImage);
            holder.tvPokedexNumber = (TextView) view.findViewById(R.id.tvPokedexNumber);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        Pokemon current = pokemonList.get(position);

        holder.imageview.setImageResource(R.drawable.placeholder);
        new DownloadImageTask(holder.imageview).execute(current.getImage());
        holder.tvPokedexNumber.setText(String.valueOf(current.getPokedexNumber()));
        holder.tvName.setText(current.getName());

        return view;
    }

    static class ViewHolder {
        public ImageView imageview;
        public TextView tvPokedexNumber;
        public TextView tvName;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon = null;
            try{
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
