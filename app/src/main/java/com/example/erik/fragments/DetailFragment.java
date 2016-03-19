package com.example.erik.fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erik.fragments.old.PokemonModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class DetailFragment extends Fragment {

    Pokemon selectedPokemon;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detail_view,
                container, false);

        if(selectedPokemon !=null){
            setDisplayData(view);
        }

        //Save button
        Button button= (Button) view.findViewById(R.id.saveButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePokemon();
            }
        });

        return view;
    }

    public void setDisplayData(Pokemon pokemon){
        View view = getView();
        selectedPokemon = pokemon;

        getActivity().setTitle(pokemon.getName());

        EditText pokedexNumber = (EditText) view.findViewById(R.id.detailPokedexnumber);
        disableEditText(pokedexNumber);
        pokedexNumber.setText(String.valueOf(pokemon.getPokedexNumber()));
        EditText name = (EditText) view.findViewById(R.id.detailName);
        disableEditText(name);
        name.setText(pokemon.getName());
        EditText type = (EditText) view.findViewById(R.id.detailType);
        disableEditText(type);
        type.setText(pokemon.getType());
        ImageView image = (ImageView) view.findViewById(R.id.detailImage);
        new DownloadImageTask(image).execute(pokemon.getImage());

        //Opinion
        final EditText opinion = (EditText) view.findViewById(R.id.detailOpinion);
        opinion.setText(pokemon.getOpinion());

        opinion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    opinion.setSelection(opinion.getText().length());
                }
            }
        });
    }

    public void setDisplayData(View view){

        getActivity().setTitle(selectedPokemon.getName());

        EditText pokedexNumber = (EditText) view.findViewById(R.id.detailPokedexnumber);
        disableEditText(pokedexNumber);
        pokedexNumber.setText(String.valueOf(selectedPokemon.getPokedexNumber()));
        EditText name = (EditText) view.findViewById(R.id.detailName);
        disableEditText(name);
        name.setText(selectedPokemon.getName());
        EditText type = (EditText) view.findViewById(R.id.detailType);
        disableEditText(type);
        type.setText(selectedPokemon.getType());
        ImageView image = (ImageView) view.findViewById(R.id.detailImage);
        type.setText(selectedPokemon.getType());

        //Opinion
        final EditText opinion = (EditText) view.findViewById(R.id.detailOpinion);
        opinion.setText(selectedPokemon.getOpinion());

        opinion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    opinion.setSelection(opinion.getText().length());
                }
            }
        });
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

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        editText.setBackgroundColor(Color.TRANSPARENT);
    }

    //Save button

    private void updatePokemon(){

        String url = "https://powerful-depths-54671.herokuapp.com/ios/pokemon/"+selectedPokemon.getPokedexNumber();
        JSONObject jPokemon = new JSONObject();

        try {
            EditText opinion = (EditText) view.findViewById(R.id.detailOpinion);
            jPokemon.put("opinion", opinion.getText());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (jPokemon.length() > 0) {
            new SendJsonDataToServer().execute(String.valueOf(jPokemon), url, "PUT");
        }
        Toast.makeText(getActivity().getApplicationContext(), "Successfully saved your opinion.", Toast.LENGTH_SHORT).show();
    }

}



