package com.mindtedtech.android_enigma.model;

import com.mindtedtech.android_enigma.R;

public class ListIDs
{
    public final static String[] ALPHABET = new String[]{ "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    public final static int[] plugboardIDs = {   R.id.plugboard_1, R.id.plugboard_2, R.id.plugboard_3, R.id.plugboard_4,
                                            R.id.plugboard_5, R.id.plugboard_6, R.id.plugboard_7, R.id.plugboard_8,
                                            R.id.plugboard_9, R.id.plugboard_10, R.id.plugboard_11, R.id.plugboard_12,
                                            R.id.plugboard_13 };
    public final static int[] rotorIDs = {   R.id.rotor_3_choice_spinner, R.id.rotor_2_choice_spinner,
                                        R.id.rotor_1_choice_spinner }; // arranged backwards for when adding rotors
    public final static int[] rotorInitialPositionIDs = { R.id.rotor_3_initial_position_spinner, R.id.rotor_2_initial_position_spinner,
            R.id.rotor_1_initial_position_spinner };
    public final static int[] rotorRingSettingIDs = { R.id.rotor_3_ring_setting_spinner, R.id.rotor_2_ring_setting_spinner,
            R.id.rotor_1_ring_setting_spinner};
    public final static int reflectorID = R.id.reflector_choice_spinner;
}
