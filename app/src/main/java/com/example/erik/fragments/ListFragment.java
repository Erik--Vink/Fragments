package com.example.erik.fragments;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListFragment extends Fragment {

     ArrayList<Pokemon> pokemonList;
     PokemonAdapter pokemonAdapter;
    private AdapterView.OnItemClickListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        pokemonList = new ArrayList<Pokemon>();

        new JSONAsyncTask().execute("https://powerful-depths-54671.herokuapp.com/ios/pokemon");

        ListView listview = (ListView)getView().findViewById(R.id.pokemon_list);
        pokemonAdapter = new PokemonAdapter(getActivity().getApplicationContext(), R.layout.pokemon_row, pokemonList);

        listview.setAdapter(pokemonAdapter);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();

        pokemonAdapter.getFilter().filter(String.valueOf(PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("number_of_pokemons", 151)));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AdapterView.OnItemSelectedListener) {
            listener = (AdapterView.OnItemClickListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implemenet ListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Collecting pokemons");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                String data = getDataFromUrl(urls[0]);

                JSONArray jarray = new JSONArray(data);

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject object = jarray.getJSONObject(i);

                    Pokemon pokemon = new Pokemon();

                    pokemon.setPokedexNumber(object.getInt("pokedexId"));
                    pokemon.setName(object.getString("name"));
                    pokemon.setType(object.getString("type"));
                    pokemon.setImage(object.getString("image"));
                    if(!object.isNull("opinion")){
                        pokemon.setOpinion(object.getString("opinion"));
                    }

                    pokemonList.add(pokemon);
                }
                return true;

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            //Limit the list
            pokemonAdapter.getFilter().filter(String.valueOf(PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("number_of_pokemons", 151)));
            pokemonAdapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getActivity().getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }

        public String getDataFromUrl(String url) {
            String result = null;
//        System.out.println("URL comes in jsonparser class is:  " + url);
            try {
                URL myurl=new URL(url);
                HttpURLConnection urlConnection = (HttpURLConnection) myurl
                        .openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.connect();
                InputStream is=urlConnection.getInputStream();
                if (is != null) {
                    StringBuilder sb = new StringBuilder();
                    String line;
                    try {
                        BufferedReader reader = new BufferedReader(
                                new InputStreamReader(is));
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                        reader.close();
                    } finally {
                        is.close();
                    }
                    result = sb.toString();
                }
            }catch (Exception e){
                result=null;
            }
            return result;
        }
    }

}