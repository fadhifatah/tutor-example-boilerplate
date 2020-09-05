package com.example.ui.main;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.example.data.SyncService;
import com.example.data.model.Reminder;
import com.example.data.model.User;
import com.example.databinding.ActivityMainBinding;
import com.example.ui.base.BaseActivity;
import com.example.util.DialogFactory;
import com.example.util.ViewExtensionKt;
import java.util.List;
import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainMvpView {

    @Inject
    MainPresenter mMainPresenter;

    @Inject
    OngoingAdapter mOngoingAdapter;

    @Override
    protected void setDataBinding() {
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);

        mBinding.rvOngoing.setAdapter(mOngoingAdapter);
        mBinding.rvOngoing.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mMainPresenter.attachView(this);
        mMainPresenter.loadMain();

        startService(SyncService.getStartIntent(this));
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void hideLoading() {
        ViewExtensionKt.gone(mBinding.layoutProgress.container);
    }

    @Override
    public void onSuccess(final List<Reminder> dataList) {
        if (dataList != null) {
            mOngoingAdapter.setDataList(dataList);
        }
    }

    @Override
    public void showError(final String message) {
        DialogFactory.createGenericErrorDialog(this, message).show();
    }

    @Override
    public void showLoading() {
        ViewExtensionKt.visible(mBinding.layoutProgress.container);
    }

    @Override
    public void onSetUser(final User user) {
        mBinding.tvName.setText(user.getName());
        mBinding.tvStatus.setText(user.getMessage());

        Glide.with(this).load(user.getPicture()).circleCrop().into(mBinding.ivProfile);
    }
}
