package com.example.injection.module;

import android.app.Application;
import android.content.Context;

import com.example.BuildConfig;
import com.icapps.niddler.core.AndroidNiddler;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.example.data.remote.SimpleService;
import com.example.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {

    protected final Application mApplication;

    protected final AndroidNiddler mAndroidNiddler;

    public ApplicationModule(Application application, AndroidNiddler androidNiddler) {
        mApplication = application;
        mAndroidNiddler = androidNiddler;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    SimpleService provideSimpleService() {
        if (BuildConfig.DEBUG) {
            return SimpleService.Creator.newSimpleService(mAndroidNiddler);
        }
        return SimpleService.Creator.newSimpleService();
    }

}
