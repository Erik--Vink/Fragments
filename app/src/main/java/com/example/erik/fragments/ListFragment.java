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

    private ListView listView;
    private PokemonListAdapter pokemonListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.pokemon_list);
        pokemonListAdapter = new PokemonListAdapter(getActivity(), R.layout.pokemon_list_item, getData());
        listView.setAdapter(pokemonListAdapter);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
    }


    //dummy data

    private ArrayList<PokemonModel> getData() {
        final ArrayList<PokemonModel> pokemonItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        String[] images = getResources().getStringArray(R.array.image_ids);

        for(int i = 0; i< images.length; i++) {
//            Log.v("image", images[i]);
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            pokemonItems.add(new PokemonModel((i+1),"pokemonName","type", "http://s3-eu-west-1.amazonaws.com/calpaterson-pokemon/1.jpeg")); //Geef ALTIJD protocol aan.(http(s))
        }
        return pokemonItems;
    }

        //
//    public interface OnItemSelectedListener {
//        public void onSportItemSelected(String link);
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof AdapterView.OnItemSelectedListener) {
//            listener = (AdapterView.OnItemSelectedListener) context;
//        } else {
//            throw new ClassCastException(context.toString()
//                    + " must implemenet MyListFragment.OnItemSelectedListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        listener = null;
//    }

}