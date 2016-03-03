package com.example.erik.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private boolean mHasOnePane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mHasOnePane = findViewById(R.id.container) != null;
//
//        if (mHasOnePane) {
//            FragmentManager fm = getFragmentManager();
//            if (fm.findFragmentByTag("list") == null) {
//                // add list fragment
//                FragmentTransaction trx = fm.beginTransaction();
//                trx.add(R.id.container, new ListFragment(), "list");
//                trx.commit();
//            }
//        } // else, layout handles it

//        if (savedInstanceState != null) {
//            mLastSelectedLink = savedInstanceState.getString("selectedLink", null);
//            onRssItemSelected(mLastSelectedLink);
//        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);

        PokemonModel pokemon = (PokemonModel) parent.getItemAtPosition(position);

//        TextView number = (TextView) view.findViewById(R.id.pokedex_number);
//        String strNumber = number.getText().toString();
//        TextView textView = (TextView) view;

        if (fragment != null && fragment.isInLayout())
        {
//            fragment.setNewText((String) textView.getText());
            fragment.setDisplayData(pokemon);
        }
        else
        {
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("SelectedPokemon", pokemon);
            startActivity(intent);
        }
    }


//    @Override
//    public void onSportItemSelected(String link) {
//
////        if (mHasOnePane) {
////
//            FragmentManager fm = getFragmentManager();
//            DetailFragment detailFragment = (DetailFragment) fm.findFragmentByTag("detail");
////
//            if (detailFragment == null) {
//                // create and initialize fragment
//                detailFragment = new DetailFragment();
//
//                // configure link
//                Bundle bundle = new Bundle();
//                bundle.putString("link", link);
//                detailFragment.setArguments(bundle);
//
//                // add fragment
//                FragmentTransaction trx = fm.beginTransaction();
//                trx.replace(R.id.container, detailFragment, "detail");
//                trx.addToBackStack(null);
//                trx.commit();
//
//            } else {
////
////                detailFragment.getArguments().putString("link", link);
////            }
////
////        } else {
////
////            DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
////            fragment.setNewText(link);
////        }
//
//    }

}
