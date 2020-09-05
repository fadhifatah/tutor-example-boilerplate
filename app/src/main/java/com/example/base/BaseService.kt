package com.example.base

import android.app.Service
import android.content.Context
import android.content.Intent
import com.example.injection.ApplicationContext

/**
 * Created by fatahfadhlurrohman on Sat, 05 Sep 2020
 */
abstract class BaseService : Service() {

    companion object {

        @JvmStatic
        fun <T> getStartIntent(@ApplicationContext context: Context, clazz: Class<T>): Intent = Intent(context, clazz)
    }
}