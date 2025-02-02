package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginData {

    private static final HashMap<String, ArrayList<String>> hash = new HashMap<>();

    public static void add(String email, String password) {
        hash.computeIfAbsent(email, String -> new ArrayList<String>()).add(password);
    }

    public static String getKey(int i) {
        Object[] objects = hash.keySet().toArray();
        return (String) objects[i];
    }

    public static int getLengthHash() {
        return hash.size();
    }

    public static String getPassword(String email, int i) {
        Object[] objects = hash.get(email).toArray();
        return (String) objects[i];
    }

    public static int getLengthPass(String email) {
        return hash.get(email).toArray().length;
    }

}