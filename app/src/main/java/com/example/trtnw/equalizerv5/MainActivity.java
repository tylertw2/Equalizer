package com.example.trtnw.equalizerv5;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;

import android.os.Bundle;
import android.view.View;

import android.widget.TextView;
import android.widget.Spinner;
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
        //Allow app to control volume of device
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //Create MediaPlayer with default settings of 0dB for all bands
        //Play mp3 file from res/raw/
        //To use with other music, disable next 2 lines, and use android musicplayer to play mp3s saved to the device
        myMediaPlayer = MediaPlayer.create(this, R.raw.vulf);
        myMediaPlayer.start();
        //Enable equalizer object
        myEqualizer = new Equalizer(0, myMediaPlayer.getAudioSessionId());
        myEqualizer.setEnabled(true);
        //Setup spinner UI
        show_spinner_presets();
        /** Print out built-in equalizer settings (only used as guidelines to make our own presets)
         String[] music_styles = new String[myEqualizer.getNumberOfPresets()];
         for(int k=0; k <myEqualizer.getNumberOfPresets() ; k++) {
         music_styles[k] = myEqualizer.getPresetName((short) k);
         myEqualizer.usePreset((short) k);
         for (short j = 0; j < 5; j++) {
         System.out.println("freq: " + myEqualizer.getBandFreqRange(j).toString());
         System.out.println("band " + j + ": " + myEqualizer.getBandLevel(j));
         }
         System.out.println(k + "  " + music_styles[k]);
         }
         */
    }
    @Override
    protected void onPause() {
        super.onPause();
        //basically do nothing
        /** Disable to pause app when tabbed out
         if (myMediaPlayer != null) {
         myEqualizer.release();
         myMediaPlayer.release();
         myMediaPlayer = null;
         } */
    }

    /**
     * Shows spinner with presets to choose from
     */
    public void show_spinner_presets() {
        ArrayList<CustomPreset> presetList = CustomPreset.getPresetList();
        /** Print out band levels from all presets
         for (int i = 0; i < CustomPreset.getNumPresets(); i++) {
         System.out.println(i + "    " + CustomPreset.getLevelsFromCP(CustomPreset.getPresetList().get(i)));
         } */
        ArrayList<String> presetNames = new ArrayList<>();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presetNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner presetSpinner = findViewById(R.id.spinner1);
        // get list of all preset's levels as ArrayList of ArrayLists
        ArrayList<ArrayList<Short>> presetLevels = new ArrayList<>();;
        //get list of presetNames and load it into the spinner
        //get list of presetsLevels for onItemSelected
        for (short i = 0; i < CustomPreset.getNumPresets(); i++) {
            presetLevels.add(CustomPreset.getLevelsFromCP(CustomPreset.getPresetList().get(i)));
            presetNames.add(presetList.get(i).getName(i));
        }
        final ArrayList<ArrayList<Short>> allPresetLevels = presetLevels;
        //set adapter for the spinner
        presetSpinner.setAdapter(spinnerAdapter);
        //When a preset is selected from the spinner, use it's settings and change UI elements
        presetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //first preset listed is set as default current preset
                useSettings(allPresetLevels.get(position));
                //set progress for each seekBar
                VerticalSeekBar bar1 = findViewById(R.id.seekBar1);
                bar1.setProgress((int)((double) allPresetLevels.get(position).get(0)*0.0333 + 50));
                TextView tv1 = findViewById(R.id.textView1);
                tv1.setText((allPresetLevels.get(position).get(0)/100) + "dB");
                VerticalSeekBar bar2 = findViewById(R.id.seekBar2);
                bar2.setProgress((int)((double) allPresetLevels.get(position).get(1)*0.0333 + 50));
                TextView tv2 = findViewById(R.id.textView2);
                tv2.setText(allPresetLevels.get(position).get(1)/100 + "dB");
                VerticalSeekBar bar3 = findViewById(R.id.seekBar3);
                bar3.setProgress((int)((double) allPresetLevels.get(position).get(2)*0.0333 + 50));
                TextView tv3 = findViewById(R.id.textView3);
                tv3.setText(allPresetLevels.get(position).get(2)/100 + "dB");
                VerticalSeekBar bar4 = findViewById(R.id.seekBar4);
                bar4.setProgress((int)((double) allPresetLevels.get(position).get(3)*0.0333 + 50));
                TextView tv4 = findViewById(R.id.textView4);
                tv4.setText(allPresetLevels.get(position).get(3)/100 + "dB");
                VerticalSeekBar bar5 = findViewById(R.id.seekBar5);
                bar5.setProgress((int)((double) allPresetLevels.get(position).get(4)*0.0333 + 50));
                TextView tv5 = findViewById(R.id.textView5);
                tv5.setText(allPresetLevels.get(position).get(4)/100 + "dB");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Not used
            }
        });
    }

    public void useSettings(ArrayList<Short> levels) {
        //Most android phones only have 5 frequency bands: 60, 230, 910, 3600 and 14000Hz
        for (short i = 0; i < 5; i++) {
            myEqualizer.setBandLevel(i, levels.get((int) i));
            //System.out.println("band " + i + ":  " + myEqualizer.getBandLevel(i));
        }
    }
}


