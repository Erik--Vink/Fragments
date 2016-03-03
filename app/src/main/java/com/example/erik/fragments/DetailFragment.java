package com.example.erik.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class DetailFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_view,
                container, false);
        return view;
    }

    public void setNewText(String url) {
        Log.v("text", url);
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(url);
    }
}



