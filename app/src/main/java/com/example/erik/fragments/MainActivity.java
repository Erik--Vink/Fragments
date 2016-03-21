package com.example.erik.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.erik.fragments.old.PokemonModel;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int SETTINGS_REQUEST = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        Pokemon pokemon = (Pokemon) parent.getItemAtPosition(position);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), PreferencesActivity.class);
            startActivity(intent);
//            SettingsActivity.startThisActivityForResult(this, SETTINGS_REQUEST);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
