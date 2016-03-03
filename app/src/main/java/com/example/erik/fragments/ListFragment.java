package com.example.erik.fragments;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
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

    private AdapterView.OnItemSelectedListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListView listview = (ListView) getView().findViewById(R.id.sportsList);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener) getActivity());
    }
//
//    public interface OnItemSelectedListener {
//        public void onSportItemSelected(String link);
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AdapterView.OnItemSelectedListener) {
            listener = (AdapterView.OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}