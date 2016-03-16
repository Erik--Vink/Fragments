package com.example.erik.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.erik.fragments.old.PokemonModel;

import java.io.InputStream;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Intent intent = getIntent();
//        String link = intent.getStringExtra("SelectedPokemon");

        Intent intent = getIntent();
        Pokemon pokemon = (Pokemon) intent.getParcelableExtra("SelectedPokemon");

        DetailFragment fragment = (DetailFragment) getFragmentManager().findFragmentById(R.id.detailFragment);
        if (fragment != null && fragment.isInLayout())
        {
////            fragment.setNewText(link);
//            Bundle bundle = getIntent().getExtras();
//            Pokemon pokemon = bundle.getParcelable("SelectedPokemon");
//
            fragment.setDisplayData(pokemon);

        }
    }

}
