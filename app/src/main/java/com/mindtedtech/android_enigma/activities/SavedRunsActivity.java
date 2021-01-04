package com.mindtedtech.android_enigma.activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mindtedtech.android_enigma.R;
import com.mindtedtech.android_enigma.memory.MessageInfo;
import com.mindtedtech.enigmamachine.utilities.WiringData;

import org.w3c.dom.Text;

public class SavedRunsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_runs);
        setupToolbar();
        setupFAB();

        String text = prepareDataFromPreviousRuns();
        TextView textView = (TextView) findViewById(R.id.saved_runs_textview);
        textView.setText(text);

        textView.setMovementMethod(new ScrollingMovementMethod()); // enable scrolling
    }

    private void setupToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupFAB()
    {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) // looks like back arrow
        {
            onBackPressed();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    public String prepareDataFromPreviousRuns()
    {
        if (MainActivity.memoryBank.sentMessages.size() == 0)
            return "There are no saved messages.  Please ensure that saving messages is turned on, and run the machine."; // TODO fix

        // TODO tidy
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < MainActivity.memoryBank.sentMessages.size(); i++)
        {
            MessageInfo message = MainActivity.memoryBank.sentMessages.get(i);
            sb.append(constructMessageInfoText(message));

            if (i != MainActivity.memoryBank.sentMessages.size() - 1)
                sb.append("\n");
        }

        return sb.toString();
    }

    private String constructMessageInfoText(MessageInfo message)
    {
        StringBuilder sb = new StringBuilder();

        // version
        sb.append("Enigma version: ");
        if (message.enigmaVersion == WiringData.enimgaVersionsEnum.ENIGMA_1)
            sb.append("Enigma 1");
        else
            sb.append("Enigma M3");
        sb.append("\n");

        // rotors
        sb.append("Rotors: ")
                .append(message.rotors.get(0)).append(", ")
                .append(message.rotors.get(1)).append(", ")
                .append(message.rotors.get(2)).append("\n");
        // initial positions
        sb.append("Initial Positions: ")
                .append(message.rotorInitialPositions.get(0)).append(", ")
                .append(message.rotorInitialPositions.get(1)).append(", ")
                .append(message.rotorInitialPositions.get(2)).append("\n");
        // ring settings
        sb.append("Ring Settings: ")
                .append(message.rotorRingSettings.get(0)).append(", ")
                .append(message.rotorRingSettings.get(1)).append(", ")
                .append(message.rotorRingSettings.get(2)).append("\n");

        // reflector
        sb.append("Reflector: ").append(message.reflector).append("\n");

        // plugboard
        sb.append("Plugboard: ");
        if (!message.plugboardConnections.isEmpty())
        {
            for (String conn : message.plugboardConnections) {
                sb.append(conn);

                if (message.plugboardConnections.indexOf(conn) != message.plugboardConnections.size() - 1) // not last element
                    sb.append(", ");
            }
        }
        sb.append("\n");

        // plaintext
        sb.append("Plaintext: ").append(message.plaintext).append("\n");
        // ciphertext
        sb.append("Ciphertext: ").append(message.ciphertext).append("\n");

        return sb.toString();
    }

    public static void deleteRuns() {
        MainActivity.memoryBank.deleteMessage();
    }
}