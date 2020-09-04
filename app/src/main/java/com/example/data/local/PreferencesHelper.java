package com.example.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.example.injection.ApplicationContext;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_boilerplate_pref_file";

    private static final String IS_LOGGED = "isLogged";

    private static final String USER_JSON = "userJson";

    private final SharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public boolean isLogged() {
        return mPref.getBoolean(IS_LOGGED, false);
    }

    public void setLogged(boolean isLogged) {
        mPref.edit().putBoolean(IS_LOGGED, isLogged).apply();
    }

    public String getUser() {
        return mPref.getString(USER_JSON, "");
    }

    public void setUser(String userJson) {
        mPref.edit().putString(USER_JSON, userJson).apply();
    }
}
