package com.example.ui.main.page

import com.example.base.BasePresenter
import com.example.data.DataManager
import com.example.data.response.ReminderResponse
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Sat, 05 Sep 2020
 */
class ReminderPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<ReminderMvpView>() {

    fun loadList(type: String) {
        mvpView?.showLoading()
        dataManager.getDataList(type).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
            object : Observer<ReminderResponse> {
                override fun onSubscribe(d: Disposable) {
                    disposableContainer.add(d)
                }

                override fun onNext(response: ReminderResponse) {
                    if (response.status != null) {
                        if (response.status == "success") {
                            mvpView?.onSuccess(response.reminderList)
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
    }
}