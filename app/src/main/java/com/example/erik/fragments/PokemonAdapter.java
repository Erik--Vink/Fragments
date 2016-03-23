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
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;


public class PokemonAdapter extends ArrayAdapter<Pokemon> implements Filterable {

    private ArrayList<Pokemon> filteredPokemonList = null;
    private ArrayList<Pokemon> originalPokemonList = null;
    private LayoutInflater inflater;
    private int Resource;
    private ViewHolder holder;
    private ItemFilter mFilter = new ItemFilter();

    public PokemonAdapter(Context context, int resource, ArrayList<Pokemon> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        filteredPokemonList = objects;
        originalPokemonList = objects;
    }

    public int getCount() {
        if(filteredPokemonList==null){
            Log.v("LOG","Warn, null filteredData");
            return 0;
        }else{
            return filteredPokemonList.size();
        }
    }

    public Pokemon getItem(int position) {
        return filteredPokemonList.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(Resource, null);
            holder.imageview = (ImageView) view.findViewById(R.id.ivImage);
            holder.tvPokedexNumber = (TextView) view.findViewById(R.id.tvPokedexNumber);
            holder.tvName = (TextView) view.findViewById(R.id.tvName);
            holder.tvType = (TextView) view.findViewById(R.id.tvType);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        Pokemon current = filteredPokemonList.get(position);

        holder.imageview.setImageResource(R.drawable.placeholder);
        new DownloadImageTask(holder.imageview, current.getImage()).execute(current.getImage());
        holder.tvPokedexNumber.setText("#" + String.valueOf(current.getPokedexNumber()));
        holder.tvName.setText(current.getName());
        holder.tvType.setText(current.getType());

        return view;
    }

    static class ViewHolder {
        public TextView tvPokedexNumber;
        public TextView tvName;
        public TextView tvType;
        public ImageView imageview;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String url;

        public DownloadImageTask(ImageView bmImage, String url) {
            this.bmImage = bmImage;
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bmImage.setTag(url);
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
            String tag = (String)bmImage.getTag();
            if(tag != null && tag.equals(url)){
                //Draw the image
                bmImage.setImageBitmap(result);
            } else {
                //Draw the placeholder
                bmImage.setImageResource(R.drawable.placeholder);
            }

        }
    }

    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            int filterSize = Integer.parseInt(constraint.toString());

            FilterResults results = new FilterResults();

            final ArrayList<Pokemon> list = originalPokemonList;

            final ArrayList<Pokemon> nlist = new ArrayList<Pokemon>(list);

            nlist.subList(filterSize, nlist.size()).clear();

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredPokemonList = (ArrayList<Pokemon>) results.values;
            notifyDataSetChanged();
        }
    }
}
