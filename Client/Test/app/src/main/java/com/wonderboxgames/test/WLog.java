package com.wonderboxgames.test;

import android.util.Log;

public class WLog {

    public static String Key = "WBG";

    public static void Log(String info) {
        Log.i(Key, info);
    }

    public static void Warning(String info) {
        Log.w(Key, info);
    }

    public static void Error(String info) {
        Log.e(Key, info);
    }
}
