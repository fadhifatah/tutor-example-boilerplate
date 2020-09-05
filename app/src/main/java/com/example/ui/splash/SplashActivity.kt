package com.example.ui.splash

import android.content.Intent
import android.os.Bundle
import com.example.databinding.ActivitySplashBinding
import com.example.base.BaseActivity
import com.example.ui.login.LoginActivity
import com.example.ui.main.MainActivity
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Fri, 04 Sep 2020
 */
class SplashActivity : BaseActivity<ActivitySplashBinding>(), SplashMvpView {

    @Inject lateinit var mPresenter: SplashPresenter

    override fun onCreateViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)

        if (mPresenter.isLogged()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }
}