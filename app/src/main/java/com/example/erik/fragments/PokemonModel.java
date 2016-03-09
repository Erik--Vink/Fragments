package com.example.erik.fragments;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class PokemonModel implements Parcelable {

    private int pokedexNumber;
    private String name;
    private String type;
    private String imgLink;

    public PokemonModel(int pokedexNumber, String name, String type, String imgLink){
        super();
        this.pokedexNumber = pokedexNumber;
        this.name = name;
        this.type = type;
        this.imgLink = imgLink;
    }


    public PokemonModel(Parcel in)
    {
        readFromParcel(in);
    }

    public int getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(int pokedexNumber) {
        this.pokedexNumber = pokedexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pokedexNumber);
        dest.writeString(this.name);
        dest.writeString(this.type);
    }

    public void readFromParcel(Parcel in)
    {
        // We just need to read back each
        // field in the order that it was
        // written to the parcel

        this.pokedexNumber = in.readInt();
        this.name = in.readString();
        this.type = in.readString();
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        @Override
        public PokemonModel createFromParcel(Parcel in)
        {
            return new PokemonModel(in);
        }

        @Override
        public Object[] newArray(int size)
        {
            return new PokemonModel[size];
        }
    };

    public String getImageLink() {
        return imgLink;
    }
}
