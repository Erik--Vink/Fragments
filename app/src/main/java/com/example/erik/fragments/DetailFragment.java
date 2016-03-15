package com.example.erik.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.erik.fragments.old.PokemonModel;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_view,
                container, false);
        return view;
    }

    public void setDisplayData(PokemonModel pokemon){
        View view = getView();

        TextView pokedexNumber = (TextView) view.findViewById(R.id.pokedex_number);
        pokedexNumber.setText(pokemon.getPokedexNumber());
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(pokemon.getName());
        TextView type = (TextView) view.findViewById(R.id.type);
        type.setText(pokemon.getType());
//        ImageView image = (ImageView) view.findViewById(R.id.image);
//        image.setImageBitmap(pokemon.getImage());
    }

//    public void setNewText(String url) {
//        Log.v("text", url);
//        TextView view = (TextView) getView().findViewById(R.id.detailsText);
//        view.setText(url);
//    }
}



