package com.example.erik.fragments;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonListAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public PokemonListAdapter(Context context, int layoutResourceId, ArrayList data){
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = convertView;
        ViewHolder holder = null;

        if(listItem == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            listItem = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.pokedexNumber = (TextView) listItem.findViewById(R.id.pokedex_number);
            holder.name = (TextView) listItem.findViewById(R.id.name);
            holder.type = (TextView) listItem.findViewById(R.id.type);
            holder.image = (ImageView) listItem.findViewById(R.id.image);
            listItem.setTag(holder);
        }else{
            holder = (ViewHolder) listItem.getTag();
        }

        PokemonModel pokemon = (PokemonModel) data.get(position);
        holder.pokedexNumber.setText(pokemon.getPokedexNumber());
        holder.name.setText(pokemon.getName());
        holder.type.setText(pokemon.getType());
        holder.image.setImageBitmap(pokemon.getImage());

        return listItem;
    }

    static class ViewHolder {
        TextView pokedexNumber;
        TextView name;
        TextView type;
        ImageView image;
    }
}
