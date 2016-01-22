package io.simi.utils;

import android.util.Log;

/**
 * Code: 4.0.3(min-sdk) - 6.0(target-sdk)
 * Creator: chimis
 * Created time: 16/1/20 21:24
 * Updated time: 16/1/20 21:24
 */
public final class Debug {

    private static final String TAG = "SimiDebug";

    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;

    private static boolean isDebugMode = false;

    private Debug() {
    }

    public static void initialize(boolean debugMode) {
        isDebugMode = debugMode;
    }

    public static void i(String msg) {
        println(INFO, TAG, msg);
    }

    public static void v(String msg) {
        println(VERBOSE, TAG, msg);
    }

    public static void d(String msg) {
        println(DEBUG, TAG, msg);
    }

    public static void w(String msg) {
        println(WARN, TAG, msg);
    }

    public static void e(String msg) {
        println(ERROR, TAG, msg);
    }

    public static void i(String tag, String msg) {
        println(INFO, tag, msg);
    }

    public static void v(String tag, String msg) {
        println(VERBOSE, tag, msg);
    }

    public static void d(String tag, String msg) {
        println(DEBUG, tag, msg);
    }

    public static void w(String tag, String msg) {
        println(WARN, tag, msg);
    }

    public static void e(String tag, String msg) {
        println(ERROR, tag, msg);
    }

    private static void println(int priority, String tag, String msg) {
        if (!isDebugMode) {
            return;
        }
        Log.println(priority, tag, "================================================================");
        Log.println(priority, tag, msg);
        Log.println(priority, tag, "================================================================");
    }
}
