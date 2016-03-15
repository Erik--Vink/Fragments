package com.example.erik.fragments.old;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erik.fragments.R;

import java.util.ArrayList;

public class PokemonListAdapter extends BaseAdapter {
    private ArrayList<PokemonModel> listData;
    private LayoutInflater layoutInflater;

    public PokemonListAdapter(Context context, ArrayList<PokemonModel> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItem = convertView;
        ViewHolder holder;

//        if(listItem == null) {
//            listItem = layoutInflater.inflate(R.layout.pokemon_list_item, null);
//            holder = new ViewHolder();
//            holder.pokedexNumber = (TextView) listItem.findViewById(R.id.pokedex_number);
//            holder.name = (TextView) listItem.findViewById(R.id.name);
//            holder.type = (TextView) listItem.findViewById(R.id.type);
//            holder.imageView = (ImageView) listItem.findViewById(R.id.image);
//            listItem.setTag(holder);
//        }else{
//            holder = (ViewHolder) listItem.getTag();
//        }

//        PokemonModel pokemon = listData.get(position);
//        holder.pokedexNumber.setText(String.valueOf(pokemon.getPokedexNumber()));
//        holder.name.setText(pokemon.getName());
//        holder.type.setText(pokemon.getType());
//
//        if(holder.imageView != null) {
//            new DownloadPokeImageTask(holder.imageView).execute(pokemon.getImageLink());
//        }

        //Here starts the Async magic!
//        DownloadPokeImageTask task = new DownloadPokeImageTask((ImageView) listItem.findViewById(R.id.image));
//        task.execute(pokemon);
        //Here endeth the demonstration.
        //Volgens mij kan je async gebruiken voor meer dan alleen images :)

        return listItem;
    }

    static class ViewHolder {
        TextView pokedexNumber;
        TextView name;
        TextView type;
        ImageView imageView;
    }
}
