package com.mindtedtech.android_enigma.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mindtedtech.android_enigma.R;
import com.mindtedtech.android_enigma.model.Throwaway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbars();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupToolbars()
    {
        // find the spinner from the xml
        Spinner spinner = findViewById(R.id.reflector_choice_spinner);
        // create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, Throwaway.REFLECTOR_NAMES);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // set the spinners adapter to the previously created one
        spinner.setAdapter(adapter);

        spinner = findViewById(R.id.rotor_3_choice_spinner);
        adapter = new ArrayAdapter<>(this, R.layout.spinner_item, Throwaway.ROTOR_NAMES);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = findViewById(R.id.rotor_2_choice_spinner);
        adapter = new ArrayAdapter<>(this, R.layout.spinner_item, Throwaway.ROTOR_NAMES);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner = findViewById(R.id.rotor_1_choice_spinner);
        adapter = new ArrayAdapter<>(this, R.layout.spinner_item, Throwaway.ROTOR_NAMES);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}