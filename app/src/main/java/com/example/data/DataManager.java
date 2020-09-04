package com.example.data;

import com.example.data.local.PreferencesHelper;
import com.example.data.remote.SimpleService;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jetbrains.annotations.Nullable;

@Singleton
public class DataManager {

    private final SimpleService mSimpleService;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(SimpleService simpleService, PreferencesHelper preferencesHelper) {
        mSimpleService = simpleService;
        mPreferencesHelper = preferencesHelper;
    }

    /*public Observable<ReminderResponse> getDataList(String type) {
        return mSimpleService.getDataList(type);
    }

    public Observable<LoginResponse> doLogin(LoginRequest loginRequest) {
        return mSimpleService.doLogin(loginRequest);
    }*/

    public void setIsLogged(boolean isLogged) {
        mPreferencesHelper.setLogged(isLogged);
    }

    public boolean isLogged() {
        return mPreferencesHelper.isLogged();
    }

    public void setSavedUser(@Nullable final String userJson) {
        mPreferencesHelper.setUser(userJson);
    }

    public String getSavedUser() {
        return mPreferencesHelper.getUser();
    }
}
