package com.example.ui.main.page

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.base.BaseFragment
import com.example.data.model.Reminder
import com.example.databinding.FragmentReminderBinding
import com.example.util.DialogFactory
import com.example.util.Extras
import com.example.util.gone
import com.example.util.visible
import javax.inject.Inject

/**
 * Created by fatahfadhlurrohman on Sat, 05 Sep 2020
 */
class ReminderFragment : BaseFragment<FragmentReminderBinding>(), ReminderMvpView {

    @Inject lateinit var mReminderPresenter: ReminderPresenter

    @Inject lateinit var mReminderListAdapter: ReminderListAdapter

    override fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentReminderBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent().inject(this)
        mReminderPresenter.attachView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(mBinding.rvReminder) {
            adapter = mReminderListAdapter
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)

            ViewCompat.setNestedScrollingEnabled(this, true)
        }

        arguments?.let { bundle ->
            mReminderPresenter.loadList(bundle.getString(Extras.TYPE, ""))
        }
    }

    override fun onDestroyView() {
        mReminderPresenter.detachView()
        super.onDestroyView()
    }

    override fun hideLoading() {
        mBinding.layoutProgress.container.gone()
    }

    override fun onSuccess(dataList: List<Reminder>) {
        mReminderListAdapter.setDataList(dataList)
    }

    override fun showError(message: String?) {
        DialogFactory.createGenericErrorDialog(mContext, message).show()
    }

    override fun showLoading() {
        mBinding.layoutProgress.container.visible()
    }
}