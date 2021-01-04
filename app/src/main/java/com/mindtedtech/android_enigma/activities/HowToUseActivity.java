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

public class HowToUseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);
        setupToolbar();
        // no fab

        String text = prepareText();
        TextView textview = (TextView) findViewById(R.id.how_to_use_textview);
        textview.setText(text);

        textview.setMovementMethod(new ScrollingMovementMethod()); // enable scrolling
    }

    private void setupToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    private String prepareText()
    {
        // TODO make multiple fragments

        StringBuilder sb = new StringBuilder();
        sb.append("Intro").append("\n-----------------").append("\n");
        sb.append(intro).append("\n\n\n");
        sb.append("General Information").append("\n-----------------").append("\n");
        sb.append(general).append("\n\n\n");
        sb.append("Rotors").append("\n-----------------").append("\n");
        sb.append(rotors).append("\n\n\n");
        sb.append("Reflector").append("\n-----------------").append("\n");
        sb.append(reflector).append("\n\n\n");
        sb.append("Plugboard").append("\n-----------------").append("\n");
        sb.append(plugboard);

        return sb.toString();
    }

    // Strings
    private String intro = "The R allows selection of the various reflectors, with designations starting " +
            "with UKW (from the German word Umkehrwalze, meaning 'reversal rotor').  The " +
            "descending Roman numerals III, II, and I represent the slots for the rotors.  Each " +
            "machine had a selection of one of each rotor, which could be inserted into any rotor " +
            "slot of the machine.  The plugboard was able to contain wire connections for up to 13 " +
            "connections, each one swapping two letters.  Text would then be typed into the keyboard " +
            "(only letters, no numbers or punctuation) and returned via the lampboard (in this case, " +
            "just the output box).";
    private String general = "The Enigma machine is an encryption device developed and used in the " +
            "early- to mid-20th century to protect commercial, diplomatic and military communication. " +
            "It was employed extensively by Nazi Germany during World War II, in all branches of the " +
            "German military. Germany's use of the Enigma machine gave them the unique ability to " +
            "communicate securely and a huge advantage in World War II. The Enigma machine was " +
            "considered to be so secure that even the most top secret messages were sent via its " +
            "electrical circuits.\n" +
            "Enigma has an electromechanical rotor mechanism that scrambles the 26 letters of the " +
            "alphabet into a polyalphabetic cipher.  In the military version of the Enigma, keypad " +
            "inputs were transformed into electrical signals that were transferred through the " +
            "plugboard, through the three rotors, bounced back by the reflector, and returned through the " +
            "rotors and plugboard to illuminate a letter lamp showing the output.";
    private String rotors = "The rotors (alternatively wheels or drums, Walzen in German) form the " +
            "heart of an Enigma machine.  By itself, a rotor performs only a very simple type of " +
            "encryption, a simple substitution cipher.  Enigma's security comes from using several " +
            "rotors in series (usually three or four) and the regular stepping movement of the rotors, " +
            "thus implementing a polyalphabetic substitution cipher.\n" +
            "Each rotor can be set to one of 26 possible starting positions when placed in an Enigma " +
            "machine. After insertion, a rotor can be turned to the correct position by hand, using " +
            "the grooved finger-wheel which protrudes from the internal Enigma cover when closed. " +
            "In order for the operator to know the rotor's position, each has an alphabet tyre " +
            "(or letter ring) attached to the outside of the rotor disc, with 26 characters (typically " +
            "letters); one of these is visible through the window for that slot in the cover, thus " +
            "indicating the rotational position of the rotor. In early models, the alphabet ring was " +
            "fixed to the rotor disc. A later improvement was the ability to adjust the alphabet ring " +
            "relative to the rotor disc. The position of the ring was known as the Ringstellung " +
            "(\"ring setting\"), and that setting was a part of the initial setup needed prior to an " +
            "operating session.\n" +
            "\n" +
            "Stepping and Turnover\n" +
            "To avoid merely implementing a simple (solvable) substitution cipher, every key press " +
            "caused one or more rotors to step by one twenty-sixth of a full rotation, before the " +
            "electrical connections were made. This changed the substitution alphabet used for " +
            "encryption, ensuring that the cryptographic substitution was different at each new rotor " +
            "position, producing a more formidable polyalphabetic substitution cipher. The stepping " +
            "mechanism varied slightly from model to model. The right-hand rotor stepped once with " +
            "each keystroke, and other rotors stepped less frequently.\n" +
            "When any rotor aside from (in certain cases) the leftmost one stepped enough to engage " +
            "its ratchet-and-pawl mechanism, it would trigger a \"turnover\" event where the next " +
            "rotor along the line would step.  Certain rotors would contain more than one turnover point.";
    private String reflector = "The reflector (in German, Umkehrwalze, meaning \"reverse rotor\") " +
            "contained similar internal mechanics to a normal rotor, except it connected its letters " +
            "in pairs.  In many versions of the Enigma, the reflector could only be inserted one way.  " +
            "In very few versions, the reflector would also step during encryption.  A certain version, " +
            "known as Umkehrwalze D, was in fact rewirable.\n" +
            "The reflector connected outputs of the last rotor in pairs, redirecting current back " +
            "through the rotors by a different route. The reflector ensured that Enigma would be " +
            "self-reciprocal; thus, with two identically configured machines, a message could be " +
            "encrypted on one and decrypted on the other, without the need for a bulky mechanism to " +
            "switch between encryption and decryption modes. The reflector allowed a more compact " +
            "design, but it also gave Enigma the property that no letter ever encrypted to itself. " +
            "This was a severe cryptological flaw that was subsequently exploited by codebreakers.";
    private String plugboard = "The plugboard (Steckerbrett in German) permitted variable wiring that " +
            "could be reconfigured by the operator.  The plugboard contributed more cryptographic " +
            "strength than an extra rotor. Enigma without a plugboard (known as unsteckered Enigma) " +
            "could be solved relatively straightforwardly using hand methods; these techniques were " +
            "generally defeated by the plugboard, driving Allied cryptanalysts to develop special " +
            "machines to solve it.\n" +
            "A cable placed onto the plugboard connected letters in pairs; for example, E and Q might " +
            "be a steckered pair. The effect was to swap those letters before and after the main " +
            "rotor scrambling unit. For example, when an operator pressed E, the signal was diverted " +
            "to Q before entering the rotors. Up to 13 steckered pairs might be used at one time, " +
            "although only 10 were normally used.\n" +
            "Current flowed from the keyboard through the plugboard, and proceeded to the entry-rotor " +
            "or Eintrittswalze. Each letter on the plugboard had two jacks. Inserting a plug " +
            "disconnected the upper jack (from the keyboard) and the lower jack (to the entry-rotor) " +
            "of that letter. The plug at the other end of the crosswired cable was inserted into " +
            "another letter's jacks, thus switching the connections of the two letters.";
}