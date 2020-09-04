package com.example.injection.module

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.injection.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
@Module
class FragmentModule constructor(private val fragment: Fragment) {

    @Provides
    fun provideActivity(): Fragment {
        return fragment
    }

    @Provides
    @ActivityContext
    fun providesContext(): Context {
        return fragment.requireActivity()
    }
}