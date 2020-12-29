package com.mindtedtech.android_enigma.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

import static com.mindtedtech.android_enigma.lib.Utils.showInfoDialog;

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

        setupFAB();
    }

    private void setupFAB() {
        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> FABClickAction());
    }

    // testing the input and output
    private void FABClickAction(){
        EditText inputText = findViewById(R.id.input_text);
        String saveText = inputText.getText().toString();
        String scrambled = scrambleWord(saveText);
        TextView outputText = (TextView) findViewById(R.id.output_text);
        outputText.setText(scrambled);
    }

    // Simple method as part of just quickly scrambling the input text and displaying the output
    private String scrambleWord(String word){
        ArrayList<Character> chars = new ArrayList<Character>(word.length());
        for (char c : word.toCharArray()){
            chars.add(c);
        }
        Collections.shuffle(chars);
        char[] scrambled = new char[chars.size()];
        for (int i = 0; i < scrambled.length; i++){
            scrambled[i] = chars.get(i);
        }
        return new String(scrambled);
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

        switch (item.getItemId()){
            case R.id.action_previous_options: {
                showPreviousOptions();
                return true;
            }
            case R.id.enigma_I: {
                toggleMenuItem(item);
                setEnigmaVersion("Enigma1");
                return true;
            }
            case R.id.enigma_m3: {
                toggleMenuItem(item);
                setEnigmaVersion("EnigmaM3");
                return true;
            }
            case R.id.action_settings: {
                showSettings();
                return true;
            }
            case R.id.action_about: {
                showAbout();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showPreviousOptions(){

    }
    private void setEnigmaVersion(String version){

    }
    private void showSettings(){

    }
    private void showAbout(){
        String msg = "The Enigma Machine is used for encryption.\n First developed in the the early " +
                "to mid 20th century, it was used by the Germans to send secure communication and " +
                "gave them a huge advantage during WWII.\n\n This app simulates the machine used by the " +
                "Germans";
        showInfoDialog(MainActivity.this, "About the Enigma Machine",
                msg);
    }

    private void toggleMenuItem(MenuItem item) {
        item.setChecked(!item.isChecked());
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