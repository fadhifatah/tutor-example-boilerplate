package com.example.ui.main

import com.example.base.BasePresenter
import com.example.data.DataManager
import com.example.data.model.User
import com.example.injection.ConfigPersistent
import com.google.gson.Gson
import javax.inject.Inject

class MainPresenter @Inject constructor(private val mDataManager: DataManager) : BasePresenter<MainMvpView?>() {

    fun loadUser() {
        Gson().fromJson(mDataManager.savedUser, User::class.java)?.run {
            mvpView?.onSetUser(this)
        }
    }

    /*fun loadMain() {
        mvpView?.showLoading()
        mDataManager.getDataList("ongoing")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ReminderResponse> {
                override fun onSubscribe(d: Disposable) {
                    disposableContainer.add(d)
                }

                override fun onNext(response: ReminderResponse) {
                    if (response.status != null) {
                        if (response.status == "success") {
                            mvpView?.onSuccess(response.reminderList)
                            val user = Gson().fromJson(mDataManager.savedUser, User::class.java)
                            mvpView?.onSetUser(user)
                        } else {
                            mvpView?.showError(response.message)
                        }
                    }
                    mvpView?.hideLoading()
                }

                override fun onError(e: Throwable) {
                    mvpView?.showError(e.message)
                    mvpView?.hideLoading()
                }

                override fun onComplete() {}
            })
    }*/
}