package com.example.erik.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erik.fragments.old.PokemonModel;

import java.io.InputStream;

public class DetailFragment extends Fragment {

    Pokemon selectedPokemon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_view,
                container, false);

        if(selectedPokemon !=null){
            setDisplayData(view);
        }

        return view;
    }

    public void setDisplayData(Pokemon pokemon){
        View view = getView();
        selectedPokemon = pokemon;

        getActivity().setTitle(pokemon.getName());

//        TextView pokedexNumber = (TextView) view.findViewById(R.id.pokedex_number);
//        pokedexNumber.setText(pokemon.getPokedexNumber());
        TextView name = (TextView) view.findViewById(R.id.detailName);
        name.setText(pokemon.getName());
//        TextView type = (TextView) view.findViewById(R.id.type);
//        type.setText(pokemon.getType());
        ImageView image = (ImageView) view.findViewById(R.id.detailImage);
        new DownloadImageTask(image).execute(pokemon.getImage());
    }

    public void setDisplayData(View view){

//        TextView pokedexNumber = (TextView) view.findViewById(R.id.pokedex_number);
//        pokedexNumber.setText(pokemon.getPokedexNumber());
//        TextView name = (TextView) view.findViewById(R.id.name);
//        name.setText(pokemon.getName());
//        TextView type = (TextView) view.findViewById(R.id.type);
//        type.setText(pokemon.getType());
//        ImageView image = (ImageView) view.findViewById(R.id.image);
//        image.setImageBitmap(pokemon.getImage());
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



