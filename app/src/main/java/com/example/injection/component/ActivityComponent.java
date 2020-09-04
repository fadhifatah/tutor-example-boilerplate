package com.example.injection.component;

import com.example.injection.PerActivity;
import com.example.injection.module.ActivityModule;
import com.example.ui.splash.SplashActivity;
import dagger.Subcomponent;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);

}
