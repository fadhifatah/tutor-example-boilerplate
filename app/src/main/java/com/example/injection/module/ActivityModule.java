package com.example.injection.module;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.example.injection.ActivityContext;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private FragmentActivity mActivity;

    public ActivityModule(FragmentActivity activity) {
        mActivity = activity;
    }

    @Provides
    FragmentActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
