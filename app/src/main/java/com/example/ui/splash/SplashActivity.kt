package com.example.ui.splash

import android.os.Bundle
import com.example.databinding.ActivitySplashBinding
import com.example.ui.base.BaseActivity
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Fri, 04 Sep 2020
 */
class SplashActivity : BaseActivity(), SplashMvpView {

    @Inject lateinit var mPresenter: SplashPresenter

    private lateinit var mBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if (mPresenter.isLogged()) {
            // TODO: open MainActivity
        } else {
            // TODO: do login first
        }
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }
}