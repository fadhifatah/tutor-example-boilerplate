package com.example.ui.splash

import com.example.data.DataManager
import com.example.injection.ConfigPersistent
import com.example.base.BasePresenter
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Fri, 04 Sep 2020
 */
class SplashPresenter @Inject constructor(private val mDataManager: DataManager) : BasePresenter<SplashMvpView>() {

    fun isLogged() : Boolean = mDataManager.isLogged
}