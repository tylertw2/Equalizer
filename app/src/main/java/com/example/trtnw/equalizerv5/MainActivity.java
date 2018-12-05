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


    }
    /**
     * Shows spinner with presets to choose from
     */
    public void show_spinner_presets() {
        //spinner1 setup
        ArrayList<String> presetNames = new ArrayList<>();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presetNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner dropdown1 = findViewById(R.id.spinner1);
        //get list of presets in mEqualizer class
        //short is required by getPresetName(i)
        for (short i = 0; i < myEqualizer.getNumberOfPresets(); i++) {
            presetNames.add(myEqualizer.getPresetName(i));
        }
        dropdown1.setAdapter(spinnerAdapter);
        //set preset selected!!!!!!!
        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //first preset listed is set as default current preset
                myEqualizer.usePreset((short) position);
                //get number of bands from equalizer engine
                short numberFrequencyBands = myEqualizer.getNumberOfBands();
                //get lowest setting for each band
                short bottomBandLevel = myEqualizer.getBandLevelRange()[0];
                //set seekBar indicator based on each band
                for (short i = 0; i < numberFrequencyBands; i++) {
                    VerticalSeekBar seekBar = findViewById(i);
                    //indicate current gain or loss in level
                    seekBar.setProgress(myEqualizer.getBandLevel((short) (i - bottomBandLevel)));
                    //https://stackoverflow.com/questions/35831900/equalizer-getbandleveli-returns-value-0
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}


