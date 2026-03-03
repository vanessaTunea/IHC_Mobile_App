package com.home.ihc_androidv2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;

public class Globals {
    public static boolean systemConfigured = true;
    public static String GET_DEVICES_STATUS="http://109.101.228.231/STATUS";
    public static String RELEU_1_ON="http://109.101.228.231/BEC11";
    public static String RELEU_1_OFF="http://109.101.228.231/BEC10";
    public static char[] charArray = new char[255];
//    public static boolean KEY_SYS_CONFIGURED = false;
//    public static boolean aux = false;
//    public static String SERIAL_NUMBER = "";
//    public static String TOKEN="";
//
//    SharedPreferences prefs= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//    boolean sysConfigured = prefs.getBoolean(KEY_SYS_CONFIGURED, false);
//
//
//    public static void setKeySysConfigured(boolean keySysConfigured){
//        KEY_SYS_CONFIGURED = keySysConfigured;
//
//        SharedPreferences prefs= getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//        boolean sysConfigured = prefs.getBoolean(KEY_SYS_CONFIGURED, false);
//
//        //            // Setează sysConfigured = true
////            SharedPreferences.Editor editor = prefs.edit();
////            editor.putBoolean(KEY_SYS_CONFIGURED, true);
////            editor.apply(); // salvează modificările
//
//    }
//
//    public static boolean getKeySysConfigured(){
//        return KEY_SYS_CONFIGURED;
//    }


}
