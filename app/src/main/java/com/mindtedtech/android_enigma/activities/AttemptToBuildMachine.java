package com.mindtedtech.android_enigma.activities;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Spinner;

import com.mindtedtech.android_enigma.R;
import com.mindtedtech.enigmamachine.machine_pieces.ConstructedMilitaryModel;
import com.mindtedtech.enigmamachine.machines.MachineBuilder;
import com.mindtedtech.enigmamachine.utilities.WiringData;

import java.util.ArrayList;

public class AttemptToBuildMachine
{
    private Activity activity;
    private WiringData.enimgaVersionsEnum enigmaVersion;

    private ArrayList<String> validPlugboardConnections = new ArrayList<>();
    private ArrayList<String> plugboardProblems = new ArrayList<>();
    private ArrayList<Integer> rotors = new ArrayList<>();
    private boolean rotorProblems;
    private ArrayList<Character> rotorSettings = new ArrayList<>();
    private int reflectorNumber;

    private int[] plugboardIDs = {R.id.plugboard_1, R.id.plugboard_2, R.id.plugboard_3, R.id.plugboard_4,
                                    R.id.plugboard_5, R.id.plugboard_6, R.id.plugboard_7, R.id.plugboard_8,
                                    R.id.plugboard_9, R.id.plugboard_10, R.id.plugboard_11, R.id.plugboard_12,
                                    R.id.plugboard_13 };
    private int[] rotorIDs = {R.id.rotor_3_choice_spinner, R.id.rotor_2_choice_spinner, R.id.rotor_1_choice_spinner}; // arranged backwards for when adding rotors
    private int[] rotorInitialPositionIDs = { R.id.rotor_3_initial_position_spinner, R.id.rotor_2_initial_position_spinner, R.id.rotor_1_initial_position_spinner};
    private int reflectorID = R.id.reflector_choice_spinner;

    public AttemptToBuildMachine(Activity activity)
    {
        this.activity = activity;
        enigmaVersion = MainActivity.getEnigmaVersion();
        preparePlugboard();
        prepareRotors();
        prepareReflector();
    }

    public void preparePlugboard()
    {
        ArrayList<String> connections = new ArrayList<>();
        ArrayList<Character> letters = new ArrayList<>();
        ArrayList<Character> problems = new ArrayList<>();

        // add to connections arraylists
        for (int id : plugboardIDs)
        {
            String text = ((EditText)activity.findViewById(id)).getText().toString().toUpperCase();
            if (text.length() == 2)
            {
                // check if a letter produces an error
                for (char let : text.toCharArray())
                {
                    if (letters.contains(let))
                        problems.add(let);
                    else
                        letters.add(let);
                }

                // add to connections
                connections.add(text);
            }
        }

        if (problems.size() == 0)
            validPlugboardConnections = connections;
        else
            getPlugboardProblems(connections, problems);
    }

    private void getPlugboardProblems(ArrayList<String> connections, ArrayList<Character> problems)
    {
        // if there are problems, find them
        if (problems.size() > 0) {
            for (char let : problems) // for each letter in problems
            {
                for (String conn : connections) // for connection in connection
                {
                    if (plugboardProblems.contains(conn)) // already know this is a problem
                    {
                    }
                    else if (conn.charAt(0) == let)
                        plugboardProblems.add(conn);
                    else if (conn.charAt(1) == let)
                        plugboardProblems.add(conn);
                }
            }
        }
    }

    public void prepareRotors()
    {
        for (int id : rotorIDs)
        {
            String selected = ((Spinner) activity.findViewById(id)).getSelectedItem().toString();
            int toAdd = -1;
            switch (selected)
            {
                case "I":
                    toAdd = 1; break;
                case "II":
                    toAdd = 2; break;
                case "III":
                    toAdd = 3; break;
                case "IV":
                    toAdd = 4; break;
                case "V":
                    toAdd = 5; break;
                case "VI":
                    toAdd = 6; break;
                case "VII":
                    toAdd = 7; break;
                case "VIII":
                    toAdd = 8; break;
            }

            if (rotors.contains((Integer) toAdd))
                rotorProblems = true;
            rotors.add(toAdd);
        }

        prepareRotorSettings();
    }

    private void prepareRotorSettings()
    {
        for (int id : rotorInitialPositionIDs)
        {
            rotorSettings.add(((Spinner) activity.findViewById(id)).getSelectedItem().toString().charAt(0));
        }
    }

    public void prepareReflector()
    {
        String ref = ((Spinner) activity.findViewById(reflectorID)).getSelectedItem().toString();

        switch (ref)
        {
            case "UKW-A":
                reflectorNumber = 0; break;
            case "UKW-B":
                reflectorNumber = 1; break;
            case "UKW-C":
                reflectorNumber = 2; break;
        }

        if (enigmaVersion == WiringData.enimgaVersionsEnum.ENIGMA_M3)
            reflectorNumber--; // to account for 1 less rotor
    }

    public boolean isValidConfiguration()
    {
        return plugboardProblems.size() == 0 && !rotorProblems;
    }

    public String getErrorMessage()
    {
        if (isValidConfiguration())
        {
            return "Unexpected error thrown."; // TODO
        }

        StringBuilder sb = new StringBuilder();

        if (plugboardProblems.size() > 0)
        {
            sb.append("There is a problem with the plugboard.  Each letter may only be connected to one other letter. ");
            sb.append("Please fix the error between: ");
            for (String conn : plugboardProblems)
                sb.append(conn).append(", ");
            String text = sb.toString();
            sb.setLength(0);
            sb.append(text.substring(0, text.length() - 2)); // remove the last ", "
            sb.append(".\n");
        }
        if (rotorProblems)
        {
            sb.append("Each rotor option may only be assigned once.  Please change the problematic rotor(s).");
        }

        return sb.toString();
    }

    public ConstructedMilitaryModel buildMachine()
    {
        if (!isValidConfiguration())
            return null;

        MachineBuilder builder = MachineBuilder.builder(enigmaVersion); // create builder and set version
        if (validPlugboardConnections.size() > 0)
            for (String conn : validPlugboardConnections)
                builder.addPlugboardConnection(conn); // add plugboard connections
        // add rotors and initial positions // TODO add another row of spinners for initial position; using those for setting
        builder.setRotor1(rotors.get(0));
        builder.setRotor1InitialPosition(rotorSettings.get(0));
        builder.setRotor2(rotors.get(1));
        builder.setRotor2InitialPosition(rotorSettings.get(1));
        builder.setRotor3(rotors.get(2));
        builder.setRotor3InitialPosition(rotorSettings.get(2));
        // set reflector
        builder.setReflectorSelected(reflectorNumber);

        return builder.build();
    }
}
