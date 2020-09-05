package com.example.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import com.example.data.model.User
import com.example.databinding.ActivityLoginBinding
import com.example.base.BaseActivity
import com.example.ui.main.MainActivity
import com.example.util.DialogFactory
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Thu, 03 Sep 2020
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>(), LoginMvpView {

    @Inject lateinit var mPresenter: LoginPresenter

    private lateinit var mLoadingDialong: ProgressDialog

    override fun onCreateViewBinding() = ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)

        mBinding.btnSubmit.setOnClickListener {
            mPresenter.doLogin(mBinding.etEmail.text.toString(), mBinding.etPassword.text.toString())
        }

        mPresenter.attachView(this)
    }

    override fun showLoading() {
        if (!::mLoadingDialong.isInitialized) mLoadingDialong = DialogFactory.createProgressDialog(this, "Loading...")
        mLoadingDialong.show()
    }

    override fun hideLoading() {
        mLoadingDialong.dismiss()
    }

    override fun onSuccess(user: User) {
        mPresenter.saveUser(user)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showError(message: String?) {
        DialogFactory.createGenericErrorDialog(this, message).show()
    }
}