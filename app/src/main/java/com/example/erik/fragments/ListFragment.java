package com.example.erik.fragments;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ListView listView = (ListView) getView().findViewById(R.id.pokemon_list);
        listView.setAdapter(new PokemonListAdapter(getActivity(), getData()));
    }


    private ArrayList<PokemonModel> getData() {
        final ArrayList<PokemonModel> pokemonItems = new ArrayList<>();

        for(int i = 0; i< 10; i++) {
            int pokedexnumber = i+1;
            pokemonItems.add(new PokemonModel(pokedexnumber,"pokemonName","type", "http://s3-eu-west-1.amazonaws.com/calpaterson-pokemon/"+pokedexnumber+".jpeg")); //Geef ALTIJD protocol aan.(http(s))
        }
        return pokemonItems;
    }
}