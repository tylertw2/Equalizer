package com.example.trtnw.equalizerv5;

import java.util.ArrayList;

public class CustomPreset {
    private String name;
    private short[] levels;

    public CustomPreset(String setName, short[] setLevels) {
        this.name = setName;
        this.levels = setLevels;
    }
    //Hard-code the presets
    //private CustomPreset[] myPresets = new CustomPreset[10];
    private static CustomPreset preset0 = new CustomPreset("Default", new short[]{0, 0, 0, 0, 0});
    private static CustomPreset preset1 = new CustomPreset("Bass+High Boost", new short[]{100, 0, -80, -50, 20});
    private static CustomPreset preset2 = new CustomPreset("Bass Boost", new short[]{100, 10, -30, -30, -30});
    private static CustomPreset preset3 = new CustomPreset("High Boost", new short[]{-30, -30, -30, 10, 50});
    private static CustomPreset preset4 = new CustomPreset("Classical", new short[]{10, 30, -70, -10, 25});
    private static CustomPreset preset5 = new CustomPreset("Dance", new short[]{50, 10, -30, -55, -23});
    private static CustomPreset preset6 = new CustomPreset("Rock", new short[]{20, -50, -28, 40, 25});
    private static CustomPreset preset7 = new CustomPreset("Perfect", new short[]{60, 60, 10, 70, 70});
    //private CustomPreset preset8 = new CustomPreset("Bass/High Boost", new short[]{100, 0, -80, -50, 20});
    //myPresets[0] = bhBoost;
    //myPresets[1] = new CustomPreset("Bass Boost", new short {});

    public static ArrayList<CustomPreset> getPresetList() {
        ArrayList<CustomPreset> presetList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            CustomPreset a = (CustomPreset) (Object) ("preset" + Integer.toString(i));
            presetList.add(a);
        }
        return presetList;
    }

    //onEvent create new
    public void setName(CustomPreset preset, String setName) {
        preset.name = setName;
    }
    public String getName(short index) {
        return getPresetList().get(index).name;
    }
    public short[] getLevels(short index) {
        return getPresetList().get(index).levels;
    }
    /**
    public short[] getLevels(CustomPreset preset) {
        return preset.levels;
    }
     */

}
