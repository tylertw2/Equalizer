package com.example.trtnw.equalizerv5;

import java.util.Arrays;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import android.support.v7.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Switch;

import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer myMediaPlayer;
    private Equalizer myEqualizer;
    private LinearLayout myLinearLayout;

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
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        //Initiate switch
        Switch simpleSwitch = findViewById(R.id.switch1);
        simpleSwitch.setTextOn("On"); // displayed text of the Switch whenever it is in checked or on state
        simpleSwitch.setTextOff("Off");
        //show_spinner_presets();
        //Create equalizer with default settings of 0dB
        // Create the MediaPlayer
//        you need to put your audio file in the res/raw folder
//        - the filename must be test_audio_file or
//        change it below to match your filename
        myMediaPlayer = MediaPlayer.create(this, R.raw.breathe);
        myMediaPlayer.start();
        //myEqualizer = new Equalizer(0, getTaskId());
        myEqualizer = new Equalizer(0, myMediaPlayer.getAudioSessionId());
        myEqualizer.setEnabled(true);
        /** PRINT OUT EQUALIZER PRESETS */
        String[] music_styles = new String[myEqualizer.getNumberOfPresets()];
        for(int k=0; k <myEqualizer.getNumberOfPresets() ; k++) {
            music_styles[k] = myEqualizer.getPresetName((short) k);
            myEqualizer.usePreset((short) k);
            for (short j = 0; j < 5; j++) {
                System.out.println("band " + j + ": " + myEqualizer.getBandLevel(j));
            }
            System.out.println(k + "  " + music_styles[k]);
        }
        //loadPresets();
        //TEST SHIT DELETE LATER
        //myMediaPlayer = MediaPlayer.create(this, R.raw.test_audio_file);
        //myMediaPlayer.start();
        setUI();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (myMediaPlayer != null) {
            myEqualizer.release();
            myMediaPlayer.release();
            myMediaPlayer = null;
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
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "    " + CustomPreset.getLevelsFromCP(CustomPreset.getPresetList().get(i)));
        }
        ArrayList<String> presetNames = new ArrayList<>();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presetNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner presetSpinner = findViewById(R.id.spinner1);
        // get list of all preset's levels as ArrayList of ArrayLists
        ArrayList<ArrayList<Short>> presetLevels = new ArrayList<>();;
        //get list of presetNames
        for (short i = 0; i < 8; i++) {
            presetLevels.add(CustomPreset.getLevelsFromCP(CustomPreset.getPresetList().get(i)));
            //presetLevels.set(i, Arrays.asList(CustomPreset.getLevelsFromCP(CustomPreset.getPresetList().get(i)))))
            presetNames.add(presetList.get(i).getName(i));
            //presetLevels.set(i, presetList.get(i).getLevels(i));
        }
        //final ArrayList<Short> presetLevelsInnerClass = presetLevels;
        final ArrayList<ArrayList<Short>> allPresetLevelsInnerClass = presetLevels;

        //get list of presets in mEqualizer class
        //short is required by getPresetName(i)
        presetSpinner.setAdapter(spinnerAdapter);
        //set preset selected!!!!!!!
        presetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //first preset listed is set as default current preset
                    //myEqualizer.usePreset((short) position);
                //get lowest setting for each band
                useSettings(allPresetLevelsInnerClass.get(position));
                short bottomBandLevel = myEqualizer.getBandLevelRange()[0];
                VerticalSeekBar bar1 = findViewById(R.id.seekBar1);
                bar1.setProgress(allPresetLevelsInnerClass.get(position).get(0));
                TextView tv1 = findViewById(R.id.textView1);
                tv1.setText(allPresetLevelsInnerClass.get(position).get(0) + "dB");
                VerticalSeekBar bar2 = findViewById(R.id.seekBar2);
                bar2.setProgress(allPresetLevelsInnerClass.get(position).get(1));
                TextView tv2 = findViewById(R.id.textView2);
                tv2.setText(allPresetLevelsInnerClass.get(position).get(1) + "dB");
                VerticalSeekBar bar3 = findViewById(R.id.seekBar3);
                bar3.setProgress(allPresetLevelsInnerClass.get(position).get(2));
                TextView tv3 = findViewById(R.id.textView3);
                tv3.setText(allPresetLevelsInnerClass.get(position).get(2) + "dB");
                VerticalSeekBar bar4 = findViewById(R.id.seekBar4);
                bar4.setProgress(allPresetLevelsInnerClass.get(position).get(3));
                TextView tv4 = findViewById(R.id.textView4);
                tv4.setText(allPresetLevelsInnerClass.get(position).get(3) + "dB");
                VerticalSeekBar bar5 = findViewById(R.id.seekBar5);
                bar5.setProgress(allPresetLevelsInnerClass.get(position).get(4));
                TextView tv5 = findViewById(R.id.textView5);
                tv5.setText(allPresetLevelsInnerClass.get(position).get(4) + "dB");
                /**
                for (int i = 0; i < 5; i++) {
                    //VerticalSeekBar bar = (VerticalSeekBar) (Object) ("seekBar" + Integer.toString(i));
                    VerticalSeekBar bar = findViewById(R.id.seekBar1);
                    bar.setProgress(allPresetLevelsInnerClass.get(position).get(i));
                    //bar.setProgress(myEqualizer.getBandLevel((short) (i - bottomBandLevel)));
                        // //https://stackoverflow.com/questions/35831900/equalizer-getbandleveli-returns-value-0
                } */
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void setUI() {
        for (short i = 0; i < 5; i++) {
            //            set up linear layout to contain each seekBar
            LinearLayout seekBarRowLayout = new LinearLayout(this);
            seekBarRowLayout.setOrientation(LinearLayout.HORIZONTAL);
            //            **********  the seekBar  **************
//            set the layout parameters for the seekbar
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;

//            create a new seekBar
            VerticalSeekBar seekBar = new VerticalSeekBar(this);
//            give the seekBar an ID
            seekBar.setId(i);

            seekBar.setLayoutParams(layoutParams);
            seekBar.setMax(30);
//            set the progress for this seekBar
            seekBar.setProgress(myEqualizer.getBandLevel(i));

//            change progress as its changed by moving the sliders
            final short thisI = i;
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    myEqualizer.setBandLevel(thisI, (short) (progress - 15));
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                    //not used
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    //not used
                }
            });
           // myLinearLayout.addView(seekBarRowLayout);

            //        show the spinner
            show_spinner_presets();
        }
    }

    public void useSettings(ArrayList<Short> levels) {
        for (short i = 0; i < 5; i++) {
            myEqualizer.setBandLevel(i, levels.get((int) i));
            System.out.println("band " + i + ":  " + myEqualizer.getBandLevel(i));
        }
    }

    /**public void loadPresets() {
        names[0] =
    }
     */
}


