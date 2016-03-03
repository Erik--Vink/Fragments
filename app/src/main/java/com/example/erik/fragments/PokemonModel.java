package com.example.erik.fragments;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class PokemonModel implements Parcelable {

    private String pokedexNumber;
    private String name;
    private String type;
    private Bitmap image;
    private String imageUrl;

    public PokemonModel(String number, String name, String type, Bitmap image, String imageUrl){
        super();
        this.pokedexNumber = number;
        this.name = name;
        this.type = type;
        this.image = image;
        this.imageUrl = imageUrl;
    }


    public PokemonModel(Parcel in)
    {
        readFromParcel(in);
    }

    public String getPokedexNumber() {
        return pokedexNumber;
    }

    public void setPokedexNumber(String pokedexNumber) {
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

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void getImageByUrl(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pokedexNumber);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeParcelable(this.image, flags);
    }

    public void readFromParcel(Parcel in)
    {
        // We just need to read back each
        // field in the order that it was
        // written to the parcel

        this.pokedexNumber = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.image=in.readParcelable(null);
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

}
