package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class RegData {

    private static final HashMap<String, ArrayList<String>> hash = new HashMap<>();

    public static void add(String element, String value) {
        hash.computeIfAbsent(element, String -> new ArrayList<String>()).add(value);
    }

    public static String getValue(String element, int i) {
        Object[] objects = hash.get(element).toArray();
        return (String) objects[i];
    }

    public static String getKey(int i) {
        Object[] objects = hash.keySet().toArray();
        return (String) objects[i];
    }

    public static int getLengthHash() {
        return hash.size();
    }

    public static int getLengthMaxValue() {
        int max = 0;
        for (int i = 0; i < hash.size(); i++) {
            if (max < hash.get(getKey(i)).size()) {
                max = hash.get(getKey(i)).size();
            }
        }
        return max;
    }

}