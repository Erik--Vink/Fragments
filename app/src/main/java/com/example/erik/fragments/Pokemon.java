package com.example.erik.fragments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Erik on 11-3-2016.
 */
public class Pokemon implements Parcelable {

    private int pokedexNumber;
    private String name;
    private String type;
    private String opinion;
    private String image;

    public Pokemon() {
    }

    public Pokemon(Parcel in)
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

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        dest.writeString(this.opinion);
        dest.writeString(this.getImage());
    }

    public void readFromParcel(Parcel in)
    {
        this.pokedexNumber = in.readInt();
        this.name = in.readString();
        this.type = in.readString();
        this.opinion = in.readString();
        this.image = in.readString();
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        @Override
        public Pokemon createFromParcel(Parcel in)
        {
            return new Pokemon(in);
        }

        @Override
        public Object[] newArray(int size)
        {
            return new Pokemon[size];
        }
    };
}
