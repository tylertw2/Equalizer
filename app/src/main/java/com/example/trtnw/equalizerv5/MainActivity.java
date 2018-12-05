package com.example.trtnw.equalizerv5;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import android.support.v7.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;

import android.os.Bundle;
import android.view.View;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Switch;

import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer myMediaPlayer;
    private Equalizer myEqualizer;

    public String[] names = new String[11];
    public short[][] presetLevels = new short[11][0];

    private CustomPreset cp;
    /**
     * Executed when app is loaded.
     * @param savedInstanceState Data supplied to onCreate if activity is restarted or reoriented
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initiate switch
        Switch simpleSwitch = findViewById(R.id.switch1);
        simpleSwitch.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("Off");
        //show_spinner_presets();
        //Create equalizer with default settings of 0dB
        myEqualizer = new Equalizer(0, getTaskId());
        myEqualizer.setEnabled(true);
        //loadPresets();

    }
    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && myMediaPlayer != null) {
            myEqualizer.release();
        }
    }

    /**
     * Shows spinner with presets to choose from
     */
    public void show_spinner_presets() {
        //spinner1 setup
        // REPLACE FOR LOOP WITH CustomPreset.java STUFF!!!!!!!!
        //load CustomPreset class
        ArrayList<CustomPreset> presetList = CustomPreset.getPresetList();
        ArrayList<String> presetNames = new ArrayList<>();
        for (short i = 0; i <= 7; i++) {
            presetNames.add(presetList.get(i).getName(i));
        }
        ArrayList<ArrayList<Short>> allPresetLevels = new ArrayList<>();
        for (short i = 0; i <= 7; i++) {
            ArrayList<Short> presetLevels = new ArrayList<>();
            for (short j = i; j <= 7; j++) {
                presetLevels.add(presetList.get((int) i).getLevels(i)[(int) i]);
            }
            allPresetLevels.add(presetLevels);
        }
        //final ArrayList<Short> presetLevelsInnerClass = presetLevels;
        final ArrayList<ArrayList<Short>> allPresetLevelsInnerClass = allPresetLevels;
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presetNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner dropdown1 = findViewById(R.id.spinner1);
        //get list of presets in mEqualizer class
        //short is required by getPresetName(i)
        dropdown1.setAdapter(spinnerAdapter);
        //set preset selected!!!!!!!
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //first preset listed is set as default current preset
                    //myEqualizer.usePreset((short) position);
                //get lowest setting for each band
                useSettings(allPresetLevelsInnerClass.get(position));
                short bottomBandLevel = myEqualizer.getBandLevelRange()[0];
                for (int i = 0; i <= 5; i++) {
                    VerticalSeekBar bar = (VerticalSeekBar) (Object) ("seekBar" + Integer.toString(i));
                    bar = findViewById(i);
                    bar.setProgress(myEqualizer.getBandLevel((short) (i - bottomBandLevel)));
                        // //https://stackoverflow.com/questions/35831900/equalizer-getbandleveli-returns-value-0
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void useSettings(ArrayList<Short> levels) {
        for (short i = 0; i <=5; i++) {
            myEqualizer.setBandLevel(i, levels.get((int) i));
        }
    }

    /**public void loadPresets() {
        names[0] =
    }
     */
}


