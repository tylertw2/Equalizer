package com.example.trtnw.equalizerv5;

import java.util.ArrayList;

public class CustomPreset {
    private String name ;
    private short[] levels;

    public CustomPreset(String setName, short[] setLevels) {
        this.name = setName;
        this.levels = setLevels;
    }
    //Hard-code the presets
    //private CustomPreset[] myPresets = new CustomPreset[10];, replace with ArrayList
    private static CustomPreset preset0 = new CustomPreset("Default", new short[]{0, 0, 0, 0, 0});
    private static CustomPreset preset1 = new CustomPreset("Bass+High Boost", new short[]{400, 200, -800, -200, 300});
    private static CustomPreset preset2 = new CustomPreset("Dance", new short[]{500, 100, -300, -600, -200});
    private static CustomPreset preset3 = new CustomPreset("Dad Rock", new short[]{200, -500, -300, 400, 200});
    private static CustomPreset preset4 = new CustomPreset("Bourgeois", new short[]{100, 300, -700, -100, 300}); //classical
    private static CustomPreset preset5 = new CustomPreset("Never Enough Bass", new short[]{1500, 1000, -0, -800, -1000});
    private static CustomPreset preset6 = new CustomPreset("Global Warming is a Hoax", new short[]{-1500, -1500, 0, 1500, 1500}); //it's "bassless"... heh
    private static CustomPreset preset7 = new CustomPreset("Very Quiet", new short[]{1500, 1500, 1500, 1500, 1500}); //i'm not sorry
    //private CustomPreset preset8 = new CustomPreset("Bass/High Boost", new short[]{100, 0, -80, -50, 20});
    //myPresets[0] = bhBoost;
    //myPresets[1] = new CustomPreset("Bass Boost", new short {});

    public static ArrayList<CustomPreset> getPresetList() {
        ArrayList<CustomPreset> presetList = new ArrayList<>();
        presetList.add(preset0);
        presetList.add(preset1);
        presetList.add(preset2);
        presetList.add(preset3);
        presetList.add(preset4);
        presetList.add(preset5);
        presetList.add(preset6);
        presetList.add(preset7);
        return presetList;
    }
    public static int getNumPresets() {
        return CustomPreset.getPresetList().size();
    }
    //onEvent create new
    public String getName(short index) {
        return getPresetList().get(index).name;
    }
    public static ArrayList<Short> getLevelsFromCP(CustomPreset cp) {
        ArrayList<Short> toReturn = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            toReturn.add(cp.levels[i]);
        }
        return toReturn;
    }
    /**
     public static ArrayList<ArrayList<Short>> getAllLevels() {

     }
     public short[] getLevels(CustomPreset preset) {
     return preset.levels;
     }
     */

}
