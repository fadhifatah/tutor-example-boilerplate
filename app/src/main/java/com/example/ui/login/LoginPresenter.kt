package com.example.ui.login

import com.example.data.DataManager
import com.example.data.model.User
import com.example.data.request.LoginRequest
import com.example.data.response.LoginResponse
import com.example.injection.ConfigPersistent
import com.example.ui.base.BasePresenter
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
@ConfigPersistent
class LoginPresenter @Inject constructor(private val mDataManager: DataManager) : BasePresenter<LoginMvpView>() {

    fun doLogin(email: String, password: String) {
        mvpView.showLoading()
        mDataManager.doLogin(LoginRequest(email, password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<LoginResponse> {
                override fun onComplete() {}

                override fun onSubscribe(d: Disposable) {
                    disposableContainer.add(d)
                }

                override fun onNext(response: LoginResponse) {
                    response.user?.let {
                        mvpView.onSuccess(it)
                    } ?: run {
                        mvpView.showError(response.message)
                    }

                    mvpView.hideLoading()
                }

                override fun onError(e: Throwable) {
                    mvpView.hideLoading()
                    mvpView.showError(e.message)
                }
            })
    }

    fun saveUser(user: User) {
        mDataManager.setIsLogged(true)
        mDataManager.savedUser = Gson().toJson(user)
    }
}