package com.util;

public class UILApplication {
    private static final UILApplication ourInstance = new UILApplication();
    public static final String APIKEY="OkAMTgTAh4lCOuAy3APFM9JUNW6iUo32";

    public static UILApplication getInstance() {
        return ourInstance;
    }

    public UILApplication() {
    }
}
