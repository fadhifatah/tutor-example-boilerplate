package com.example.injection.component;

import android.app.Application;
import android.content.Context;

import com.example.data.remote.SimpleService;
import javax.inject.Singleton;

import dagger.Component;
import com.example.data.DataManager;
import com.example.data.service.SyncService;
import com.example.data.local.PreferencesHelper;
import com.example.injection.ApplicationContext;
import com.example.injection.module.ApplicationModule;
import com.example.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();

    Application application();

    SimpleService ribotsService();

    PreferencesHelper preferencesHelper();

    DataManager dataManager();

    RxEventBus eventBus();
}
