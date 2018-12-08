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
    private static CustomPreset preset0 = new CustomPreset("Default", new short[]{100, 200, 300, 400, 500});
    private static CustomPreset preset1 = new CustomPreset("Bass+High Boost", new short[]{100, 0, -800, -500, 200});
    private static CustomPreset preset2 = new CustomPreset("Bass Boost", new short[]{100, 100, -0, -300, -300});
    private static CustomPreset preset3 = new CustomPreset("High Boost", new short[]{-300, -300, -300, 100, 500});
    private static CustomPreset preset4 = new CustomPreset("Classical", new short[]{100, 300, -700, -100, 300});
    private static CustomPreset preset5 = new CustomPreset("Dance", new short[]{500, 100, -300, -600, -200});
    private static CustomPreset preset6 = new CustomPreset("Rock", new short[]{200, -500, -300, 400, 200});
    private static CustomPreset preset7 = new CustomPreset("Perfect", new short[]{200, -200, -200, -100, -100});
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

    //onEvent create new
    public void setName(CustomPreset preset, String setName) {
        preset.name = setName;
    }
    public String getName(short index) {
        return getPresetList().get(index).name;
    }
    public ArrayList<Short> getLevels(short index) {
        ArrayList<Short> toReturn = new ArrayList<>();
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print("penis:   " + getPresetList().get(index).levels[index]);
            toReturn.add(getPresetList().get(index).levels[index]);
        }
        return toReturn;
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
