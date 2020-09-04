package com.example

import android.app.Application
import android.content.Context
import com.example.injection.component.ApplicationComponent
import com.example.injection.component.DaggerApplicationComponent
import com.example.injection.module.ApplicationModule
import com.icapps.niddler.core.AndroidNiddler
import timber.log.Timber
import timber.log.Timber.DebugTree

class BoilerplateApplication : Application() {

    private var mApplicationComponent: ApplicationComponent? = null

    private var mAndroidNiddler: AndroidNiddler? = null

    override fun onCreate() {
        super.onCreate()

        mAndroidNiddler = AndroidNiddler.Builder()
            .setPort(0)
            .setNiddlerInformation(AndroidNiddler.fromApplication(this))
            .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            mAndroidNiddler?.attachToApplication(this)
        }
    }

    // Needed to replace the component with a test specific one
    var component: ApplicationComponent
        get() {
            if (mApplicationComponent == null) {
                mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(this, mAndroidNiddler))
                    .build()
            }
            return mApplicationComponent!! // exception to assert !!
        }
        set(applicationComponent) {
            mApplicationComponent = applicationComponent
        }

    companion object {
        @JvmStatic
        operator fun get(context: Context): BoilerplateApplication {
            return context.applicationContext as BoilerplateApplication
        }
    }
}