package com.mindtedtech.android_enigma.activities;

import android.annotation.SuppressLint;
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

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbars();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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
        // create reflector
        setupCustomSpinner(R.id.reflector_choice_spinner, Throwaway.REFLECTOR_NAMES);

        // create rotors
        int[] rotorIDs = {R.id.rotor_3_choice_spinner, R.id.rotor_2_choice_spinner, R.id.rotor_1_choice_spinner};
        for (int id : rotorIDs) {
            setupCustomSpinner(id, Throwaway.ROTOR_NAMES);
        }

        // create rotor settings
        int[] rotorSettings = {R.id.rotor_3_setting_spinner, R.id.rotor_2_setting_spinner, R.id.rotor_1_setting_spinner};
        for (int id : rotorSettings) {
            setupCustomSpinner(id, Throwaway.ALPHABET);
        }
    }

    public void setupCustomSpinner(int spinnerID, String[] contents)
    {
        // find the spinner from the xml
        Spinner spinner = findViewById(spinnerID);
        // create an adapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, contents);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // set the spinners adapter to the previously created one
        spinner.setAdapter(adapter);
    }
}