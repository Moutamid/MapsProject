package com.sisterhood.mapsproject;

public class Constants {

    public static String MY_PREFS_NAME = "rasoulallah";
    public static String appLanguage = "ar";
    public static String[] langaugaes = {"عربي", "English", "French", "Indonesian", "Spain", "Hindi", "Italian", "Portuguese", "Urdu"};
    public static String[] langaugaes_short = {"ar", "en", "fr", "in", "es", "hi", "it", "pt", "ur"};
    public static int langaugeApp = 0;
    public static String langaugeType = "ar";

    public interface ACTION {
        public static final String MAIN_ACTION = "com.rasoulallah.foregroundservice.action.main";
        public static final String NEXT_ACTION = "com.rasoulallah.foregroundservice.action.next";
        public static final String PLAY_ACTION = "com.rasoulallah.foregroundservice.action.play";
        public static final String PREV_ACTION = "com.rasoulallah.foregroundservice.action.prev";
        public static final String STARTFOREGROUND_ACTION = "com.rasoulallah.foregroundservice.action.startforeground";
        public static final String STOPFOREGROUND_ACTION = "com.rasoulallah.foregroundservice.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static final int FOREGROUND_SERVICE = 12414;
    }
}

