package com.example.ui.login

import com.example.data.model.User
import com.example.base.MvpView

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
interface LoginMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun onSuccess(user: User)

    fun showError(message: String?)
}